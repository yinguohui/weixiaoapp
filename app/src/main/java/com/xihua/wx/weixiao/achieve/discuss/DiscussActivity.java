package com.xihua.wx.weixiao.achieve.discuss;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.adapter.MsgAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.MsgContentBean;
import com.xihua.wx.weixiao.query.ChatQuery;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.request.ChatRequest;
import com.xihua.wx.weixiao.vo.response.ChatAllResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiscussActivity extends AppCompatActivity {
    private ArrayList<MsgContentBean> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private MsgAdapter adapter;
    private EditText etInput;
    private TextView send;
    List<ChatAllResponse> list = new ArrayList<>();
    private Gson gson = new Gson();
    Runnable  runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        initContentView();
    }

    public void initContentView() {
        etInput = findViewById(R.id.et_input);
        recyclerView = findViewById(R.id.recyclerview);
        send = findViewById(R.id.send);
        recyclerView.setLayoutManager(new LinearLayoutManager(DiscussActivity.this));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

    }
    public void add(ChatRequest request){
        OkHttpUtil.doPost("http://192.168.43.240:8080/chat/add",gson.toJson(request), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(DiscussActivity.this,"网络错误");
                    break;
                case 0:
                    ToastUtil.showToast(DiscussActivity.this,"发送成功");
                    break;
                case 1:
                    ToastUtil.showToast(DiscussActivity.this,"重置成功，返回登陆");
                    break;
                case 2:
                    ToastUtil.showToast(DiscussActivity.this,"验证码不正确");
                    break;
                case 5:
                    exedata();
                    break;
            }
        }
    };
    private void initdata(){
        ChatQuery query = new ChatQuery();
        String id = SpUtil.getString(DiscussActivity.this, "userid", "-1");
        query.setSendId(getIntent().getIntExtra("send_id",-1));
        query.setReceiveId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/chat/getchatcontent",gson.toJson(query), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<ChatAllResponse>>(){}.getType());
                        handler.sendEmptyMessage(5);
                    }else {
                        handler.sendEmptyMessage(-1);
                    }
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    private void exedata(){
        adapter = new MsgAdapter(DiscussActivity.this,list);
        recyclerView.setAdapter(adapter);
    }
    private void add(){
        String content = etInput.getText().toString();
        if (!"".equals(content)) {
            ChatRequest request = new ChatRequest();
            request.setChatContent(content);
//            MsgContentBean msg = new MsgContentBean(1,content);
//            datas.add(msg);
//            adapter.notifyItemInserted(datas.size()-1);
//            recyclerView.scrollToPosition(datas.size()-1);
            etInput.setText("");
            String id = SpUtil.getString(DiscussActivity.this, "userid", "-1");
            request.setChatReviceId(list.get(0).getUser().getUserId());
            if(list.get(0).getUser().getUserId()!=Integer.parseInt(id))
            request.setChatSendId(Integer.parseInt(id));
            add(request);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        addText();
    }
    private void addText(){
       runnable = new Runnable(){
            @Override
            public void run(){
                initdata();
                //延迟1秒执行
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
