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
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.lost.adapter.LostInfoAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.query.LostInfoQuery;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.vo.response.LostinfoResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LostQueryActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,iv_search;
    EditText et_infofind;
    XRecyclerView xc_lost;
    private LinearLayoutManager manager;
    LostInfoAdapter adapter;
    private Gson gson = new Gson();
    List<LostinfoResponse> list = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    adapter = new LostInfoAdapter(list,LostQueryActivity.this);
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
            public void onRefresh() {initData();xc_lost.refreshComplete();
            }
            //加载更多
            @Override
            public void onLoadMore() {
                xc_lost.refreshComplete();
            }
        });
        xc_lost.refresh();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                search();
                break;
        }
    }
    private void initData(){
        IdRequest idRequest = new IdRequest();
        idRequest.setId(1);
        OkHttpUtil.doPost("http://192.168.43.240:8080/lostinfo/queryAllLost", gson.toJson(idRequest),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<LostinfoResponse>>(){}.getType());
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        });
    }
    private void search(){
        if (et_infofind.getText().equals("")){
            handler.sendEmptyMessage(0);
            return;
        }
        LostInfoQuery lostInfoQuery = new LostInfoQuery();
        lostInfoQuery.setLostType(1);
        lostInfoQuery.setKey(et_infofind.getText().toString());
        OkHttpUtil.doPost("http://192.168.43.240:8080/lostinfo/queryAllFound", gson.toJson(lostInfoQuery),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<LostinfoResponse>>(){}.getType());
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        });
    }
}
