package com.xihua.wx.weixiao.achieve.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean runningDownTimer;     //判断是否在倒计时

    private EditText et_phone;
    private EditText et_password;
    private String shoujihao ="";
    private String mima="";
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(LoginActivity.this,"网络错误");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }



    private void initView(){
        et_phone =  findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        findViewById(R.id.tv_forget).setOnClickListener(this);
        findViewById(R.id.bt_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击登陆
            case R.id.bt_login:
                if (!judge()){
                    return;
                }
                login();
                break;
            case  R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
                break;

        }
    }




    private boolean judge(){
        shoujihao = et_phone.getText().toString();
        mima = et_password.getText().toString();
        if (shoujihao.equals("")||mima.equals("")){
            ToastUtil.showToast(LoginActivity.this,"手机号或密码不能为空");
            return false;
        }
        if (shoujihao.length()!=11){
            ToastUtil.showToast(LoginActivity.this,"请输入正确的手机号");
            return false;
        }
       return true;
    }

    private void login(){
        Map<String,String> map = new HashMap();
        map.put("tel",shoujihao);
        map.put("password",mima);
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/login", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    formdata(response.body().string());
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    private void formdata(String data){
//        Gson gson = new Gson();
//        UserResult userResult = gson.fromJson(data,UserResult.class);
//        if (userResult.getStatus().equals("success")){
//            SpUtil.putBoolean(LoginActivity.this, "isLogin", true);
//            SpUtil.putString(LoginActivity.this, "userid", userResult.getList().getUserId());
//            SpUtil.putString(LoginActivity.this, "user_name", userResult.getList().getUserName());
//            SpUtil.putString(LoginActivity.this, "user_img", userResult.getList().getUserImg());
//            ToastUtil.showToast(LoginActivity.this, "登录成功!");
//            finish();
//        }else {
//            ToastUtil.showToast(LoginActivity.this, userResult.getMsg());
//        }
    }
}
