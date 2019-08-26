package com.xihua.wx.weixiao.achieve.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.UserResponse;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    CircleNetworkImageImage iv_image;
    RelativeLayout rl_name,rl_sex,rl_sign,rl_image,rl_password;
    TextView tv_name,tv_sex,tv_sign,tv_phone;
    Gson gson = new Gson();
    private UserResponse response;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(SettingActivity.this,"信息加载失败，重新试试");
                    break;
                case 0:
                    break;
                case 1:
                    VolleyUtils.loadImage(SettingActivity.this,iv_image,response.getUserImg());
                    tv_name.setText(response.getUserName());
                    tv_sign.setText(response.getUserSign());
                    tv_phone.setText(response.getUserTel());
                    break;
            }
        }
    };
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_image= findViewById(R.id.iv_image);

        tv_phone = findViewById(R.id.tv_phone);
        rl_name = findViewById(R.id.rl_name);
        rl_sign = findViewById(R.id.rl_sign);
        rl_image = findViewById(R.id.rl_image);
        rl_password =findViewById(R.id.rl_password);

        tv_name = findViewById(R.id.tv_name);
        tv_sign = findViewById(R.id.tv_sign);

        iv_back.setOnClickListener(this);
        rl_name.setOnClickListener(this);
        rl_sign.setOnClickListener(this);
        rl_image.setOnClickListener(this);
        rl_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_name:
                startActivity(new Intent(SettingActivity.this,ModifyNameActivity.class));
                break;
            case R.id.rl_sign:
                startActivity(new Intent(SettingActivity.this,ModifySignActivity.class));
                break;
            case R.id.rl_image:
                startActivity(new Intent(SettingActivity.this,ModifyHeafImageActivity.class));
                break;
            case R.id.rl_password:
                startActivity(new Intent(SettingActivity.this,ModifyPasswordActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String id  = SpUtil.getString(SettingActivity.this,"userid","");
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/getuserinfobyid",gson.toJson(idRequest), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    fromdata(response.body().string());
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    private void fromdata(String data){
        ApiResult apiResult = gson.fromJson(data,ApiResult.class);
        if (apiResult.getCode()==200){
            response = gson.fromJson(gson.toJson(apiResult.getData()),UserResponse.class);
            handler.sendEmptyMessage(1);
        }else {
            handler.sendEmptyMessage(-1);
        }

    }
}
