package com.xihua.wx.weixiao.achieve.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xihua.wx.weixiao.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    TextView tv_register,tv_forget;
    EditText et_usernum,et_password;
    Button bt_login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        tv_register = findViewById(R.id.tv_register);
        tv_forget = findViewById(R.id.tv_forget);
        et_usernum = findViewById(R.id.et_usernum);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);

        iv_back.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            //注册
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            //忘记密码
            case R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this,ForgetPwdActivity.class));
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }
    //登陆验证
    private void login(){

    }
}
