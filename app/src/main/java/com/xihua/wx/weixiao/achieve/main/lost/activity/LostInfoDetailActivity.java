package com.xihua.wx.weixiao.achieve.main.lost.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.DiscussActivity;
import com.xihua.wx.weixiao.achieve.main.lost.adapter.LostInfoAdapter;
import com.xihua.wx.weixiao.achieve.main.sell.SellBuyDetailActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.bean.LostinfoDetailBean;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.LostinfoResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LostInfoDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,bt_delete;
    LinearLayout ll_user;
    CircleNetworkImageImage iv_header;
    NineGridImageView iv_img;
    LostinfoResponse bean = new LostinfoResponse();
    Gson gson = new Gson();
    TextView tv_name,tv_publish_time,tv_infoname,tv_type,tv_infolosttime,tv_infolostplace,tv_infolostdesciption;
    //九宫格图片
    private NineGridImageViewAdapter nineGridImageViewAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String o) {
            Glide.with(LostInfoDetailActivity.this).load(o).into(imageView);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostdetail);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_header = findViewById(R.id.iv_header);
        tv_name = findViewById(R.id.tv_name);
        tv_publish_time = findViewById(R.id.tv_publish_time);
        tv_infoname = findViewById(R.id.tv_infoname);
        tv_type = findViewById(R.id.tv_type);
        tv_infolostplace = findViewById(R.id.tv_infolostplace);
        tv_infolostdesciption = findViewById(R.id.tv_infolostdesciption);
        iv_img = findViewById(R.id.iv_img);
        ll_user = findViewById(R.id.ll_user);

        ll_user.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_user:
                Intent intent = new Intent(LostInfoDetailActivity.this,DiscussActivity.class);
                intent.putExtra("user_id",bean.getLostinfoUserId());
                startActivity(intent);
                break;
            case R.id.bt_delete:
                delete();
                break;


        }
    }

    private void delete() {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(getIntent().getIntExtra("lost_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/lostinfo/delete", gson.toJson(idRequest),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        handler.sendEmptyMessage(4);
                    }
                }
            }
        });
    }

    private void initData(){
        IdRequest idRequest = new IdRequest();
        idRequest.setId(getIntent().getIntExtra("lost_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/lostinfo/querydetailbyid", gson.toJson(idRequest),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        bean = gson.fromJson(gson.toJson(apiResult.getData()),LostinfoResponse.class);
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        });
    }
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(LostInfoDetailActivity.this,"服务器开小差了");
                    break;
                case 0:
                    break;
                case 1:
                    getresult(bean);
                    break;
                case 4:
                    ToastUtil.showToast(LostInfoDetailActivity.this,"删除成功");
                    finish();
                    break;
            }
        }
    };
    private void getresult(LostinfoResponse response){
        String id = SpUtil.getString(LostInfoDetailActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(LostInfoDetailActivity.this,"请登录");
            return;
        }
        if (bean.getUser().getUserId()==Integer.parseInt(id)){
            bt_delete = findViewById(R.id.bt_delete);
            bt_delete.setVisibility(View.VISIBLE);
            bt_delete.setOnClickListener(this);
        }else {
            ll_user = findViewById(R.id.ll_user);
            ll_user.setOnClickListener(this);
            Intent intent = new Intent(LostInfoDetailActivity.this,DiscussActivity.class);
            intent.putExtra("user_id",bean.getUser().getUserId());
            startActivity(intent);
        }
        VolleyUtils.loadImage(LostInfoDetailActivity.this,iv_header,response.getUser().getUserImg());
        tv_name.setText(response.getUser().getUserName());
        tv_publish_time.setText(new Date(response.getLostinfoCreateTime()).toString());
        tv_infoname.setText("名称："+response.getLostinfoName());
        if (1==response.getLostinfoType()){
            tv_type.setText("类型：找寻失物");
        }else {
            tv_type.setText("类型：失物招领");
        }
        tv_infolostplace.setText("地点："+response.getLostinfoPlace());
        tv_infolostdesciption.setText("描述："+response.getLostinfoDescription());
        List<String> stringList = new ArrayList<>();
        String[] strings = response.getLostinfoImg().split("$%");
        for (String s : strings) {
            stringList.add(s);
        }
        iv_img.setAdapter(nineGridImageViewAdapter);
        iv_img.setImagesData(stringList);
    }
}
