package com.xihua.wx.weixiao.achieve.main.publish.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeAdapter;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeComparator;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.bean.TimeData;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.TopicTimeLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//我发表的
public class BelongMeActivity extends AppCompatActivity {
    //存储列表数据
    List<TopicTimeLine> list = new ArrayList<>();
    TimeAdapter adapter;
    Gson gson = new Gson();
    RecyclerView rlView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belongme);
        init();
    }
    private void init(){
        // 将数据按照时间排序
        TimeComparator comparator = new TimeComparator();
        Collections.sort(list, comparator);
        // recyclerview绑定适配器
        rlView =  findViewById(R.id.activity_rlview);
        rlView.setLayoutManager(new LinearLayoutManager(this));


    }
    private void initData(){

        String id = SpUtil.getString(BelongMeActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(BelongMeActivity.this,"请登录");
            return;
        }
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/topic/quaryTimeTopic",gson.toJson(idRequest) ,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
               handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<TopicTimeLine>>(){}.getType());
                        handler.sendEmptyMessage(1);
                    }else {
                        handler.sendEmptyMessage(0);
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
                    ToastUtil.showToast(BelongMeActivity.this,"网络错误");
                    break;
                case 0:
                    ToastUtil.showToast(BelongMeActivity.this,"暂无数据");
                    break;
                case 1:
                    adapter = new TimeAdapter(BelongMeActivity.this, list);
                    rlView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
