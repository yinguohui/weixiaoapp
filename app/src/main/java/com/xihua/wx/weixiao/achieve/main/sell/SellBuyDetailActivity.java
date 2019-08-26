package com.xihua.wx.weixiao.achieve.main.sell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.DiscussActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.DateUtils;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.GoodsDetailResponse;
import com.xihua.wx.weixiao.vo.response.GoodsResponse;
import com.xihua.wx.weixiao.vo.response.LostinfoResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SellBuyDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,bt_delete;
    CircleNetworkImageImage iv_header;
    LinearLayout ll_user;
    NineGridImageView iv_img;
    GoodsDetailResponse bean = new GoodsDetailResponse();
    Gson gson = new Gson();
    TextView tv_name,tv_publish_time,tv_goodsdesciption,tv_goodsname,tv_place,tv_price;
    //九宫格图片
    private NineGridImageViewAdapter nineGridImageViewAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String o) {
            Glide.with(SellBuyDetailActivity.this).load(o).into(imageView);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_header = findViewById(R.id.iv_header);
        tv_name = findViewById(R.id.tv_name);
        tv_publish_time = findViewById(R.id.tv_publish_time);

        tv_goodsname = findViewById(R.id.tv_goodsname);
        tv_place = findViewById(R.id.tv_place);
        tv_price = findViewById(R.id.tv_price);
        tv_goodsdesciption = findViewById(R.id.tv_goodsdesciption);
        iv_img = findViewById(R.id.iv_img);
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
                Intent intent = new Intent(SellBuyDetailActivity.this,DiscussActivity.class);
                intent.putExtra("send_id",bean.getUser().getUserId());
                startActivity(intent);
               break;
            case R.id.bt_delete:
                delete();
                break;
        }
    }

    private void delete() {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(getIntent().getIntExtra("goods_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/goods/delete", gson.toJson(idRequest),new Callback(){
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
                        finish();
                    }
                }
            }
        });
    }

    private void initData(){
        IdRequest idRequest = new IdRequest();
        idRequest.setId(getIntent().getIntExtra("goods_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/goods/querydetailbyid", gson.toJson(idRequest),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        bean = gson.fromJson(gson.toJson(apiResult.getData()),GoodsDetailResponse.class);
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
                    ToastUtil.showToast(SellBuyDetailActivity.this,"服务器开小差了");
                    break;
                case 0:
                    break;
                case 1:
                    getresult(bean);
                    break;
                case 2:
                    change();
                    break;
                case 4:
                    ToastUtil.showToast(SellBuyDetailActivity.this,"删除成功");
                    break;
            }
        }
    };
    private void getresult(GoodsDetailResponse response){
        VolleyUtils.loadImage(SellBuyDetailActivity.this,iv_header,response.getUser().getUserImg());
        tv_name.setText(response.getUser().getUserName());
        tv_publish_time.setText(DateUtils.parseDate(response.getGoodsCreateTime()));
        tv_goodsname.setText("物品名："+response.getGoodsName());
        tv_goodsdesciption.setText("描述："+response.getGoodsDesciption());
        tv_place.setText("地点："+response.getGoodsPlace());
        tv_price.setText("价格："+response.getGoodsPrice().toString());
        List<String> stringList = new ArrayList<>();
        String[] strings = response.getGoodsImg().split("$%");
        for (String s : strings) {
            stringList.add(s);
        }
        iv_img.setAdapter(nineGridImageViewAdapter);
        iv_img.setImagesData(stringList);
        change();
    }
    private void change(){
        String id = SpUtil.getString(SellBuyDetailActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(SellBuyDetailActivity.this,"请登录");
            return;
        }
        if (bean.getUser().getUserId()==Integer.parseInt(id)){
            bt_delete = findViewById(R.id.bt_delete);
            bt_delete.setVisibility(View.VISIBLE);
            bt_delete.setOnClickListener(this);
        }else {
            ll_user = findViewById(R.id.ll_user);
            ll_user.setOnClickListener(this);
        }
    }
}
