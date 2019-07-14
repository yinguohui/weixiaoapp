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
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.ChangYanAdapter;
import com.xihua.wx.weixiao.bean.ChangYanResponseBean;
import com.xihua.wx.weixiao.utils.OkHttpUtil;

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
    List<ChangYanResponseBean.ChangYanResponse> list = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    changYanAdapter = new ChangYanAdapter(list,PublishActivity.this);
                    xc_publish.setAdapter(changYanAdapter);
                    xc_publish.refreshComplete();
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
                initData();
            }
            //加载更多
            @Override
            public void onLoadMore() {
                xc_publish.refreshComplete();
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
    //初始化数据
    public void initData() {
        String test = "{\n" +
                "\t\"code\": 200,\n" +
                "\t\"page\": 200,\n" +
                "\t\"total\": 200,\n" +
                "\t\"list\": [{\n" +
                "\t\t\"changYanId\": 11212,\n" +
                "\t\t\"changYanUsername\": \"飒飒\",\n" +
                "\t\t\"changYanUserImg\": \"https://c-ssl.duitang.com/uploads/people/201708/03/20170803155935_j5Cmx.thumb.36_36_c.jpeg\",\n" +
                "\t\t\"chanYanTime\": 26111,\n" +
                "\t\t\"changYanContent\": \"大大反对大师傅\",\n" +
                "\t\t\"changYanImg\": \"https://c-ssl.duitang.com/uploads/people/201708/03/20170803155935_j5Cmx.thumb.36_36_c.jpeg%https://c-ssl.duitang.com/uploads/people/201708/03/20170803155935_j5Cmx.thumb.36_36_c.jpeg\",\n" +
                "\t\t\"changyanLike\": 122,\n" +
                "\t\t\"changYanComment\": 55\n" +
                "\t}]\n" +
                "}";
        Gson gson = new Gson();
        ChangYanResponseBean bean = gson.fromJson(test,ChangYanResponseBean.class);
        list = bean.getList();
        handler.sendEmptyMessage(1);
    }
    private void formatData(String result) {
        Gson gson = new Gson();
//        PostsListBean postsListBean = gson.fromJson(result,PostsListBean.class);
//        PostsListBean.Info info = postsListBean.getInfo();
//        postList = info.getPostList();
        handler.sendEmptyMessage(1);

    }
    private void loadmore(){
        //okhttp获取数据--
        //"http://yapi.demo.qunar.com/mock/11910/title?userid="+userid
        OkHttpUtil.doGet("http://192.168.43.240:8080/title/getalltitle", new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }
}
