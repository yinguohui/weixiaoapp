package com.xihua.wx.weixiao.achieve.discuss;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.adapter.MsgAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.bean.User;
import com.xihua.wx.weixiao.query.ChatQuery;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.request.ChatRequest;
import com.xihua.wx.weixiao.vo.response.ChatAllResponse;
import com.xihua.wx.weixiao.vo.response.UserResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiscussActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MsgAdapter adapter;
    private EditText et_input;
    private TextView send,title;
    private User userResponse = new User();
    List<ChatAllResponse> list = new ArrayList<>();
    String friend = "";
    private Gson gson = new Gson();
    private EMMessageListener msgListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        initContentView();
    }

    public void initContentView() {
        friend = getIntent().getIntExtra("send_id",-1)+"";

        et_input = findViewById(R.id.et_input);
        title = findViewById(R.id.title);
        send = findViewById(R.id.send);
        recyclerView = findViewById(R.id.recyclerview);

        initdata();

        recyclerView.setLayoutManager(new LinearLayoutManager(DiscussActivity.this));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                send();
            }
        });


        //收到消息
        //收到透传消息
        //消息状态变动
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                String result = messages.get(0).getBody().toString();
                String msgReceived = result.substring(5, result.length() - 1);
                final ChatAllResponse response = new ChatAllResponse();
                String id = SpUtil.getString(DiscussActivity.this, "userid", "-1");
                response.setChatReceiveId(Integer.parseInt(id));
                response.setChatContent(msgReceived);
                response.setChatSendId(Integer.parseInt(friend));
                if (null!=response.getUser().getUserId())
                loadChaterInfo(friend);
                response.setUser(userResponse);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.add(response);
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(list.size() - 1);
                    }
                });

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }


            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    public void send(){
        String content = et_input.getText().toString().trim();
        if (!TextUtils.isEmpty(content)){
            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
            EMMessage message = EMMessage.createTxtSendMessage(content, friend);
            String id = SpUtil.getString(DiscussActivity.this, "userid", "-1");
            //发送消息
            EMClient.getInstance().chatManager().sendMessage(message);
            ChatAllResponse chatAllResponse = new ChatAllResponse();
            chatAllResponse.setChatContent(content);
            chatAllResponse.setChatReceiveId(Integer.parseInt(friend));
            chatAllResponse.setChatSendId(Integer.parseInt(id));
            list.add(chatAllResponse);
            adapter.notifyItemInserted(list.size() - 1);
            recyclerView.scrollToPosition(list.size() - 1);
            et_input.setText("");
        }
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
                    adapter = new MsgAdapter(DiscussActivity.this,list);
                    recyclerView.setAdapter(adapter);
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
        title.setText(list.get(0).getUser().getUserName());
    }
    private void add(){
        String content = et_input.getText().toString();
        if (!"".equals(content)) {
            ChatRequest request = new ChatRequest();
            request.setChatContent(content);
            String id = SpUtil.getString(DiscussActivity.this, "userid", "-1");
            request.setChatSendId(Integer.parseInt(id));
            request.setChatReviceId(getIntent().getIntExtra("send_id",-1));
            add(request);
        }
    }
    private void loadChaterInfo(String chater_id) {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(chater_id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/getuserinfobyid", gson.toJson(idRequest), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    getChaterInfo(response.body().string());
                } else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    private void  getChaterInfo(String info){
        ApiResult<UserResponse> apiResult = gson.fromJson(info, ApiResult.class);
        UserResponse response = gson.fromJson(gson.toJson(apiResult.getData()), UserResponse.class);
        userResponse.setUserId(response.getUserId());
        userResponse.setUserName(response.getUserName());
        userResponse.setUserImg(response.getUserImg());
    }
}
