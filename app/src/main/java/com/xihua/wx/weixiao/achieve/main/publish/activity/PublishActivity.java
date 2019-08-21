package com.xihua.wx.weixiao.achieve.main.publish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.ChangYanAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.query.TopicQuery;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.TopicResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    TextView tv_publish;
    RelativeLayout rl_belongme,rl_aboutme;
    XRecyclerView xc_publish;
    LinearLayoutManager manager;
    ChangYanAdapter changYanAdapter;
    Gson gson = new Gson();
    List<TopicResponse> list = new ArrayList<>();
    TopicQuery topicQuery = new TopicQuery();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(PublishActivity.this,"服务器开小差了");
                    break;
                case 0:
                    break;
                case 1:
                    changYanAdapter = new ChangYanAdapter(list,PublishActivity.this);
                    xc_publish.setAdapter(changYanAdapter);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        tv_publish = findViewById(R.id.tv_publish);
        rl_belongme = findViewById(R.id.rl_belongme);
        rl_aboutme = findViewById(R.id.rl_aboutme);
        xc_publish = findViewById(R.id.xc_publish);

        iv_back.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
        rl_belongme.setOnClickListener(this);
        rl_aboutme.setOnClickListener(this);

        xc_publish = findViewById(R.id.xc_publish);
        manager = new LinearLayoutManager(PublishActivity.this);
        xc_publish.setLayoutManager(manager);
        //允许刷新，加载更多
        xc_publish.setPullRefreshEnabled(true);
        xc_publish.setLoadingMoreEnabled(true);
        xc_publish.setRefreshProgressStyle(ProgressStyle.SysProgress);
        xc_publish.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        xc_publish.setArrowImageView(R.drawable.icon_down_fresh);

        //加载刷新组件
        xc_publish.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                topicQuery.setCurrentPage(1);
                topicQuery.setPageSize(10);
                initData(topicQuery);
                xc_publish.refreshComplete();
            }
            //加载更多
            @Override
            public void onLoadMore() {
                if (list.size()==10){
                    topicQuery.setCurrentPage(topicQuery.getCurrentPage());
                    initData(topicQuery);
                    xc_publish.refreshComplete();
                }else {
                    xc_publish.refreshComplete();
                }
            }
        });
        xc_publish.refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_publish:
                startActivity(new Intent(PublishActivity.this,PublishContentActivity.class));
                break;
            case R.id.rl_belongme:
                startActivity(new Intent(PublishActivity.this,BelongMeActivity.class));
                break;
            case R.id.rl_aboutme:
                startActivity(new Intent(PublishActivity.this,AboutMeActivity.class));
                break;
        }
    }

    private void initData(TopicQuery topicQuery){
        //okhttp获取数据--
        OkHttpUtil.doPost("http://192.168.43.240:8080/topic/quaryAllTopic",gson.toJson(topicQuery) ,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<TopicResponse>>() {
                        }.getType());
                    }
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }
}
