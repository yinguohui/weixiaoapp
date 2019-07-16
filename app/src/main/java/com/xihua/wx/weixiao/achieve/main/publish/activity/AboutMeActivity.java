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
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.AboutMeAdapter;
import com.xihua.wx.weixiao.achieve.main.sell.SellBuyActivity;
import com.xihua.wx.weixiao.achieve.main.sell.adapter.GoodsAdapter;
import com.xihua.wx.weixiao.bean.LikeMessageList;

import java.util.List;

//关于我的

public class AboutMeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    XRecyclerView xw_about;
    List<LikeMessageList.LikeMessageBean> likeMessageBeans;
    AboutMeAdapter aboutMeAdapter;
    LinearLayoutManager manager;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    aboutMeAdapter = new AboutMeAdapter(likeMessageBeans,AboutMeActivity.this);
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
        String test ="{\n" +
                "\t\"code\": 200,\n" +
                "\t\"page\": 11,\n" +
                "\t\"list\": [{\n" +
                "\t\t\"likeDetailId\": 1111,\n" +
                "\t\t\"userId\": 11111,\n" +
                "\t\t\"userName\": \"画虎\",\n" +
                "\t\t\"userImg\": \"\",\n" +
                "\t\t\"createTime\": 11212,\n" +
                "\t\t\"topicId\": 1111,\n" +
                "\t\t\"topicImg\": \"\",\n" +
                "\t\t\"topicContent\": \"你啥的哈\"\n" +
                "\t}, {\n" +
                "\t\t\"likeDetailId\": 1111,\n" +
                "\t\t\"userId\": 11111,\n" +
                "\t\t\"userName\": \"画虎\",\n" +
                "\t\t\"userImg\": \"\",\n" +
                "\t\t\"createTime\": 11212,\n" +
                "\t\t\"topicId\": 1111,\n" +
                "\t\t\"topicImg\": \"\",\n" +
                "\t\t\"topicContent\": \"你啥的哈\"\n" +
                "\t}]\n" +
                "\n" +
                "}";

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
            public void onRefresh(){
            }
            //加载更多
            @Override
            public void onLoadMore() {
            xw_about.refreshComplete();
            }
        });
        xw_about.refresh();
        Gson gson = new Gson();
        LikeMessageList likeMessageList = gson.fromJson(test,LikeMessageList.class);
        likeMessageBeans = likeMessageList.getList();
        handler.sendEmptyMessage(1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
