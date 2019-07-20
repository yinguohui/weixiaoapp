package com.xihua.wx.weixiao.achieve.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.user.UserLoginRequestBean;
import com.xihua.wx.weixiao.bean.user.UserLoginResponseBean;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VerificationUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Logger logger = Logger.getLogger("LoginActivity");
    Gson gson = new Gson();
    private EditText et_phone,et_password;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(LoginActivity.this,"网络错误");
                    break;
                case 1:
                    ToastUtil.showToast(LoginActivity.this,"登录成功");
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

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_forget).setOnClickListener(this);
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //退出
            case R.id.iv_back:
                finish();
                break;
            //点击登陆
            case R.id.bt_login:
                if (VerificationUtils.judge(et_phone,et_password,LoginActivity.this))
                login();
                break;
            //忘记密码
            case  R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
                break;
            //注册
            case  R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    private void login(){
        UserLoginRequestBean bean = new UserLoginRequestBean();
        bean.setTel(et_phone.getText().toString());
        bean.setPassword(et_password.getText().toString());
        String s = gson.toJson(bean);
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/login", gson.toJson(bean), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               logger.log(Level.INFO,e.getMessage());
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    success(response.body().string());
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    private void success(String data) {
        UserLoginResponseBean userResult = gson.fromJson(data, UserLoginResponseBean.class);
        SpUtil.putString(LoginActivity.this, "userid", "");
        handler.sendEmptyMessage(1);
    }
}
