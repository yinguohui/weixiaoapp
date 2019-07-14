package com.xihua.wx.weixiao.achieve.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xihua.wx.weixiao.R;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    EditText et_phone,et_password,et_config_password,et_code;
    Button bt_forgetsure,bt_code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_config_password = findViewById(R.id.et_config_password);
        et_password = findViewById(R.id.et_password);
        et_code = findViewById(R.id.et_code);
        bt_forgetsure = findViewById(R.id.bt_forgetsure);
        bt_code = findViewById(R.id.bt_code);

        iv_back.setOnClickListener(this);
        bt_forgetsure.setOnClickListener(this);
        bt_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_forgetsure:
                forgetsure();
                break;
            case R.id.bt_code:
                getcode();
                break;
        }
    }
    //忘记密码
    private void forgetsure(){

    }
    //获取验证码
    private void getcode(){

    }
}