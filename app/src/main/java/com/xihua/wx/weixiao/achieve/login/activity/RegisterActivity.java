package com.xihua.wx.weixiao.achieve.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_phone,et_password,et_config_password,et_code;
    private CheckBox check;
    private RelativeLayout rl_renzheng;
    private CheckCode checkCode;
    private TextView xieyi;
    private String shoujihao="";
    private String newmima = "";
    private String code ="";
    private Gson gson = new Gson();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(RegisterActivity.this,"网络错误");
                    break;
                case 0:
                    ToastUtil.showToast(RegisterActivity.this,"验证码已发送");
                case 1:
                    ToastUtil.showToast(RegisterActivity.this,"注册成功，返回登陆");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    private void initView(){
        et_phone =  findViewById(R.id.et_phone);
        et_code = findViewById(R.id.et_code);
        checkCode = findViewById(R.id.bt_code);
        rl_renzheng = findViewById(R.id.rl_renzheng);
        check = findViewById(R.id.check);
        xieyi = findViewById(R.id.xieyi);

        rl_renzheng.setOnClickListener(this);
        findViewById(R.id.bt_code).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.xieyi).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_register:
                if (!VerificationUtils.judge(et_phone,et_password,et_config_password,RegisterActivity.this))return;
                register();
                break;
            case R.id.bt_code:
                if (checkCode.isFinish()){
                    getcode();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_renzheng:
                startActivity(new Intent(RegisterActivity.this,IdentifyActivity.class));
                break;
            case R.id.xieyi:
                startActivity(new Intent(RegisterActivity.this,StatementActivity.class));
                break;
        }

    }
    //获取后台验证码
    private void getcode(){
        if (!VerificationUtils.judge(et_phone,RegisterActivity.this))return;
        checkCode.start();
        Random r = new Random();
        //产生随机验证码
        code= "";
        for (int i=0;i<6;i++){
            code +=r.nextInt(10);
        }

        OkHttpUtil.doGet("http://v.juhe.cn/sms/send?mobile="+et_phone.getText().toString()+"&tpl_id=157950&tpl_value=%23code%23%3D"+code+"&key=3ce6ab5eaa050dcc5780aba49b1c0363", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    handler.sendEmptyMessage(0);
                }
            }
        });
    }

    private void register(){
        LoginRequest request = new LoginRequest();
        request.setUserTel(shoujihao);
        request.setUserPassword(newmima);
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/register", gson.toJson(request), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    judgeResult(response.body().string());
                }else {
                    handler.sendEmptyMessage(-1);
                }

            }
        });
    }

    private void judgeResult(String data){
        ApiResult apiResult = gson.fromJson(data,ApiResult.class);
        if (apiResult.getCode()==200){
            handler.sendEmptyMessage(1);
            finish();
        }
        else {
            ToastUtil.showToast(RegisterActivity.this,apiResult.getData().toString());
        }
    }
}
