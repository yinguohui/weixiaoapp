package com.xihua.wx.weixiao.achieve.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.request.UserRequest;
import com.xihua.wx.weixiao.vo.response.UserResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModifySignActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView modifysign;
    private EditText et_sign;
    String data = "";
    String data1 = "";
    private Gson gson;
    private UserResponse userResult;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    et_sign.setText(userResult.getUserSign());
                    break;
                case 2:
                    fromresult(data);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifysign);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        modifysign = findViewById(R.id.modifysign);
        et_sign = findViewById(R.id.et_sign);
        gson = new Gson();

        iv_back.setOnClickListener(this);
        modifysign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.modifysign:
                modifysign();
                break;
        }
    }
    private void modifysign(){
        String id= SpUtil.getString(ModifySignActivity.this, "userid", "-1");
        UserRequest userResponse = new UserRequest();
        userResponse.setUserSign(et_sign.getText().toString());
        userResponse.setUserId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/updateuserinfo", gson.toJson(userResponse) , new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    data = response.body().string();
                    handler.sendEmptyMessage(2);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        IdRequest idRequest = new IdRequest();
        String id  =SpUtil.getString(ModifySignActivity.this, "userid", "-1");
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/getuserinfobyid",gson.toJson(idRequest) ,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    fromdata(response.body().string());
                }
            }
        });
    }

    //
    private void fromdata(String data){
        ApiResult result = gson.fromJson(data,ApiResult.class);
        userResult = gson.fromJson(gson.toJson(result),UserResponse.class);
        if (result.getCode()!=200){
            handler.sendEmptyMessage(-1);
        }else {
            handler.sendEmptyMessage(1);
        }
    }

    private void  fromresult(String data){
        ApiResult result = gson.fromJson(data,ApiResult.class);
        if (result.getCode()==200){
            ToastUtil.showToast(ModifySignActivity.this,"修改成功");
        }else {
            ToastUtil.showToast(ModifySignActivity.this,result.getMessage());
        }
    }

}