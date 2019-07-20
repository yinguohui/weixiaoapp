package com.xihua.wx.weixiao.achieve.login.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.utils.CheckCode;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VerificationUtils;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener {
    private Logger logger = Logger.getLogger("ForgetActivity");
    EditText et_phone,et_code,et_newpassword;
    CheckCode checkCode;
    String code = "";
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(ForgetActivity.this,"网络错误");
                    break;
                case 3:
                    ToastUtil.showToast(ForgetActivity.this,"验证码已发送");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        init();
    }
    private void init(){
        et_phone = findViewById(R.id.et_phone);
        et_code = findViewById(R.id.et_code);
        et_newpassword = findViewById(R.id.et_newpassword);
        checkCode = findViewById(R.id.bt_code);

        checkCode.setOnClickListener(this);
        findViewById(R.id.bt_code).setOnClickListener(this);
        findViewById(R.id.bt_forget).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_code:
                if (checkCode.isFinish()){
                    checkCode.start();
                    getcode();
                }
                break;
            case R.id.bt_forget:
                newpassword();
            case R.id.iv_back:
                finish();
                break;

        }
    }

    private void  newpassword(){
        if (code.equals(et_code.getText().toString())){
            //重置修改密码
            OkHttpUtil.doGet("http://192.168.43.240:8080/user/updateuserpassword?userTel="+et_phone.getText().toString()+"&userPassword="+et_newpassword.getText().toString(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(-1);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
//                    if (response.isSuccessful()){
//                        Gson gson = new Gson();
//                        Result result =gson.fromJson(response.body().string(),Result.class);
//                        if (result.getStatus().equals("success")){
//                            finish();
//                            ToastUtil.showToast(ForgetActivity.this,"重置成功，返回登陆");
//                        }else {
//                            ToastUtil.showToast(ForgetActivity.this,"手机号尚未注册");
//                        }
//                    }else {
//                        handler.sendEmptyMessage(-1);
//                    }
                }
            });
        }else {
            ToastUtil.showToast(ForgetActivity.this,"验证码不正确");
        }
    }


    //获取后台验证码
    private void getcode(){
        if (!VerificationUtils.judge(et_phone,et_newpassword,ForgetActivity.this))return;
        Random r = new Random();
        code = "";
        //产生随机验证码
        for (int i=0;i<6;i++){
            code +=r.nextInt(10);
        }

        OkHttpUtil.doGet("http://v.juhe.cn/sms/send?mobile="+et_phone.getText().toString()+"&tpl_id=154057&tpl_value=%23code%23%3D"+code+"&key=3ce6ab5eaa050dcc5780aba49b1c0363", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    logger.log(Level.INFO,response.body().string());
                    handler.sendEmptyMessage(3);
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }

}
