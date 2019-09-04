package com.xihua.wx.weixiao.achieve.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.xihua.wx.weixiao.MainActivity;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.user.UserLoginRequestBean;
import com.xihua.wx.weixiao.bean.user.UserLoginResponseBean;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VerificationUtils;
import com.xihua.wx.weixiao.vo.request.LoginRequest;
import com.xihua.wx.weixiao.vo.response.IdrResponse;
import com.xihua.wx.weixiao.vo.response.UserResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Logger logger = Logger.getLogger("LoginActivity");
    Gson gson = new Gson();
    private EditText et_phone, et_password;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(LoginActivity.this, "网络错误");
                    break;
                case 1:
                    ToastUtil.showToast(LoginActivity.this, "登录成功");
                    break;
                case 2:
                    ToastUtil.showToast(LoginActivity.this, "账号或密码错误！");
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


    private void initView() {
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);

        findViewById(R.id.tv_forget).setOnClickListener(this);
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 点击登陆
            case R.id.bt_login:
                if (VerificationUtils.judge(et_phone, et_password, LoginActivity.this))
                    login();
                break;
            // 忘记密码
            case R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
                break;
            // 注册
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void login() {
        LoginRequest request = new LoginRequest();
        request.setUserTel(et_phone.getText().toString());
        request.setUserPassword(et_password.getText().toString());
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/login", MapUtil.objectToMap(request), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.log(Level.INFO, e.getMessage());
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    success(response.body().string());
                } else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }

    private void success(String data) {
        ApiResult<IdrResponse> userResult = gson.fromJson(data, ApiResult.class);
        UserResponse idrResponse =  gson.fromJson(gson.toJson(userResult.getData()),UserResponse.class);
        if (userResult.getCode()==200){
            EMClient.getInstance().login("15196622412",et_password.getText().toString(),new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                }

                @Override
                public void onProgress(int progress, String status) {

                }

                @Override
                public void onError(int code, String message) {
                }
            });
            handler.sendEmptyMessage(1);
            SpUtil.putString(LoginActivity.this, "userid", String.valueOf(idrResponse.getUserId()));
            SpUtil.putString(LoginActivity.this, "userimg", String.valueOf(idrResponse.getUserImg()));
            SpUtil.putString(LoginActivity.this, "username", String.valueOf(idrResponse.getUserName()));
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else {
            handler.sendEmptyMessage(2);
        }

    }
}
