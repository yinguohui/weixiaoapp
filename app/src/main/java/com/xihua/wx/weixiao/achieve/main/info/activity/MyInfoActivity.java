package com.xihua.wx.weixiao.achieve.main.info.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.donation.activity.DonationDetailActivity;
import com.xihua.wx.weixiao.achieve.main.lost.activity.LostInfoDetailActivity;
import com.xihua.wx.weixiao.achieve.main.publish.activity.ChangYanDetailActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.DonationDetailResponse;
import com.xihua.wx.weixiao.vo.response.LostinfoResponse;
import com.xihua.wx.weixiao.vo.response.SuggestionDetailResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,comment_delete;
    CircleNetworkImageImage head;
    SuggestionDetailResponse bean = new SuggestionDetailResponse();
    TextView username,createtime,description;
    NineGridImageView img;
    Gson gson = new Gson();
    //九宫格图片
    private NineGridImageViewAdapter nineGridImageViewAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String o) {
            Glide.with(MyInfoActivity.this).load(o).into(imageView);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_detail);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        head = findViewById(R.id.head);
        username = findViewById(R.id.username);
        createtime = findViewById(R.id.createtime);
        description = findViewById(R.id.description);
        img =findViewById(R.id.img);

        iv_back.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.comment_delete:
               delete();
                break;
        }
    }

    private void delete() {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(getIntent().getIntExtra("suggestion_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/suggestion/delete", gson.toJson(idRequest),new Callback(){
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
        idRequest.setId(getIntent().getIntExtra("suggestion_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/suggestion/querysuggestionbyid", gson.toJson(idRequest),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        bean = gson.fromJson(gson.toJson(apiResult.getData()),SuggestionDetailResponse.class);
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
                    ToastUtil.showToast(MyInfoActivity.this,"服务器开小差了");
                    break;
                case 0:
                    break;
                case 1:
                    getresult(bean);
                    break;
                case 4:
                    ToastUtil.showToast(MyInfoActivity.this,"删除成功");
                    finish();
                    break;
            }
        }
    };
    private void getresult(SuggestionDetailResponse response){
        String id = SpUtil.getString(MyInfoActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(MyInfoActivity.this,"请登录");
            return;
        }
        if (bean.getUser().getUserId()==Integer.parseInt(id)){
            comment_delete = findViewById(R.id.comment_delete);
            comment_delete.setVisibility(View.VISIBLE);
            comment_delete.setOnClickListener(this);
        }
        VolleyUtils.loadImage(MyInfoActivity.this,head,response.getUser().getUserImg());
        username.setText(response.getUser().getUserName());
        description.setText(response.getSuggestionContent());
        List<String> stringList = new ArrayList<>();
        if (null!=response.getSuggestionImg()){
            String[] strings = response.getSuggestionImg().split("$%");
            for (String s : strings) {
                stringList.add(s);
            }
            img.setAdapter(nineGridImageViewAdapter);
            img.setImagesData(stringList);
        }
    }
}
