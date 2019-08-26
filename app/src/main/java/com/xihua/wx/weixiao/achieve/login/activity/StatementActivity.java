package com.xihua.wx.weixiao.achieve.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.utils.CheckCode;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VerificationUtils;
import com.xihua.wx.weixiao.vo.request.LoginRequest;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StatementActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        init();
    }
    private void init(){
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.rl_user_agreement).setOnClickListener(this);
        findViewById(R.id.rl_privacy_policy).setOnClickListener(this);
        findViewById(R.id.rl_student_agreement).setOnClickListener(this);
        findViewById(R.id.rl_legal_statement).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_user_agreement:
                startActivity(new Intent(StatementActivity.this,UserStatementActivity.class));
                break;
            case R.id.rl_privacy_policy:
                startActivity(new Intent(StatementActivity.this,PrivacyPolicyActivity.class));
                break;
            case R.id.rl_student_agreement:
                startActivity(new Intent(StatementActivity.this,StudentAgreementActivity.class));
                break;
            case R.id.rl_legal_statement:
                startActivity(new Intent(StatementActivity.this,LegalStatementActivity.class));
                break;

        }
    }
}
