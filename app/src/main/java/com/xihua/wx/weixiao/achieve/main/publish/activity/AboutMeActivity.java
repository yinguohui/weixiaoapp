package com.xihua.wx.weixiao.achieve.main.publish.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.AboutMeAdapter;
import com.xihua.wx.weixiao.achieve.main.sell.SellBuyActivity;
import com.xihua.wx.weixiao.achieve.main.sell.adapter.GoodsAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.bean.LikeMessageList;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.MineReviewResponse;
import com.xihua.wx.weixiao.vo.response.TopicTimeLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//关于我的

public class AboutMeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    XRecyclerView xw_about;
    List<MineReviewResponse> list = new ArrayList<>();
    AboutMeAdapter aboutMeAdapter;
    LinearLayoutManager manager;
    Gson gson = new Gson();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    aboutMeAdapter = new AboutMeAdapter(list,AboutMeActivity.this);
                    xw_about.setAdapter(aboutMeAdapter);
                    xw_about.refreshComplete();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        init();
    }
    private void init(){
        iv_back =findViewById(R.id.iv_back);
        xw_about =findViewById(R.id.xw_about);

        iv_back.setOnClickListener(this);

        manager = new LinearLayoutManager(this);
        xw_about.setLayoutManager(manager);
        //允许刷新，加载更多
        xw_about.setPullRefreshEnabled(true);
        xw_about.setLoadingMoreEnabled(true);
        xw_about.setRefreshProgressStyle(ProgressStyle.SysProgress);
        xw_about.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        xw_about.setArrowImageView(R.drawable.icon_down_fresh);

        //加载刷新组件
        xw_about.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                initData();
            }
            //加载更多
            @Override
            public void onLoadMore() {
            xw_about.refreshComplete();
            }
        });
        xw_about.refresh();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void initData(){
        String id = SpUtil.getString(AboutMeActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(AboutMeActivity.this,"请登录");
            return;
        }
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/review/getinemreview",gson.toJson(idRequest) ,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<MineReviewResponse>>(){}.getType());
                        handler.sendEmptyMessage(1);
                    }else {
                        handler.sendEmptyMessage(0);
                    }
                }
            }
        });
    }
}
