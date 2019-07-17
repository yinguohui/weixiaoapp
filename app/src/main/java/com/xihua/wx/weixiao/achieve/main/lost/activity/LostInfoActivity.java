package com.xihua.wx.weixiao.achieve.main.lost.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.lost.adapter.LostInfoAdapter;
import com.xihua.wx.weixiao.bean.LostinfoList;

import java.util.ArrayList;
import java.util.List;

public class LostInfoActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,iv_search;
    EditText et_infofind;
    XRecyclerView xc_lost;
    private LinearLayoutManager manager;
    LostInfoAdapter adapter;
    List<LostinfoList.LostinfoBean> list = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    adapter = new LostInfoAdapter(list,LostInfoActivity.this);
                    xc_lost.setAdapter(adapter);
                    xc_lost.refreshComplete();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_info);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_search = findViewById(R.id.iv_search);
        et_infofind = findViewById(R.id.et_infofind);
        xc_lost = findViewById(R.id.xc_lost);

        iv_back.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        manager = new LinearLayoutManager(this);
        xc_lost.setLayoutManager(manager);
        //允许刷新，加载更多
        xc_lost.setPullRefreshEnabled(true);
        xc_lost.setLoadingMoreEnabled(true);
        xc_lost.setRefreshProgressStyle(ProgressStyle.SysProgress);
        xc_lost.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        xc_lost.setArrowImageView(R.drawable.icon_down_fresh);

        //加载刷新组件
        xc_lost.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
            }
            //加载更多
            @Override
            public void onLoadMore() {
                xc_lost.refreshComplete();
            }
        });
        xc_lost.refresh();
        String test = "{\n" +
                "\t\"code\": 200,\n" +
                "\t\"page\": 100,\n" +
                "\t\"total\": 123,\n" +
                "\t\"list\": [{\n" +
                "\t\t\"lostinfoId\": 1151,\n" +
                "\t\t\"lostinfoName\": \"身份证\",\n" +
                "\t  \"lostinfoTime\":11111,\n" +
                "\t\t\"lostinfoDescription\": \"在三教附近拾取，联系电话110\",\n" +
                "\t\t\"lostinfoImg\": \"\"\n" +
                "\t}, {\n" +
                "\t\t\"lostinfoId\": 1151,\n" +
                "\t\t\"lostinfoName\": \"身份证\",\n" +
                "\t    \"lostinfoTime\":11111,\n" +
                "\t\t\"lostinfoDescription\": \"在三教附近拾取，联系电话110\",\n" +
                "\t\t\"lostinfoImg\": \"\"\n" +
                "\t}]\n" +
                "\n" +
                "}";
        Gson gson =new Gson();
        LostinfoList lostinfoList = gson.fromJson(test,LostinfoList.class);
        list = lostinfoList.getList();
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                //search();
                break;
        }
    }
}
