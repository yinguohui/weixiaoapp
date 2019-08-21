package com.xihua.wx.weixiao.achieve.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeAdapter;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeComparator;
import com.xihua.wx.weixiao.achieve.mine.adapter.DonationComparator;
import com.xihua.wx.weixiao.achieve.mine.adapter.DonationTimeAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.DonationTimeLine;
import com.xihua.wx.weixiao.vo.response.TopicTimeLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//我发表的
public class DonationTiemLineActivity extends AppCompatActivity implements View.OnClickListener {
    //存储列表数据
    List<DonationTimeLine> list = new ArrayList<>();
    DonationTimeAdapter adapter;
    Gson gson = new Gson();
    RecyclerView rlView;
    ImageView iv_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_donation);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        // 将数据按照时间排序
        DonationComparator comparator = new DonationComparator();
        Collections.sort(list, comparator);
        // recyclerview绑定适配器
        rlView =  findViewById(R.id.activity_rlview);
        rlView.setLayoutManager(new LinearLayoutManager(this));


    }
    private void initData(){

        String id = SpUtil.getString(DonationTiemLineActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(DonationTiemLineActivity.this,"请登录");
            return;
        }
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/donation/quarytimedanotion",gson.toJson(idRequest) ,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
               handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<DonationTimeLine>>(){}.getType());
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
                    ToastUtil.showToast(DonationTiemLineActivity.this,"网络错误");
                    break;
                case 0:
                    ToastUtil.showToast(DonationTiemLineActivity.this,"暂无数据");
                    break;
                case 1:
                    adapter = new DonationTimeAdapter(DonationTiemLineActivity.this, list);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
