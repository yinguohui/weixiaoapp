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

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_modifypassword;
    private EditText et_password;
    String data = "";
    Gson gson = new Gson();
    private Map<String,String> map = new HashMap<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ToastUtil.showToast(ModifyPasswordActivity.this,"修改成功");
                    break;
                case 2:
                    ToastUtil.showToast(ModifyPasswordActivity.this,"修改失败");
                    break;
                case 3:
                    fromresult(data);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypassword);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        tv_modifypassword = findViewById(R.id.tv_modifypassword);
        et_password = findViewById(R.id.modifypassword);

        iv_back.setOnClickListener(this);
        tv_modifypassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_modifypassword:
                modifypassword();
                break;
        }
    }
    private void modifypassword(){
        String id= SpUtil.getString(ModifyPasswordActivity.this, "userid", "-1");
        UserRequest userResponse = new UserRequest();
        userResponse.setUserName(et_password.getText().toString());
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
                   handler.sendEmptyMessage(3);
                }
            }
        });
    }

    private void  fromresult(String data){
        ApiResult apiResult = gson.fromJson(data,ApiResult.class);
        if (apiResult.getCode()==200){
            ToastUtil.showToast(ModifyPasswordActivity.this,"修改成功");
        }else {
            ToastUtil.showToast(ModifyPasswordActivity.this,apiResult.getMessage());
        }
    }

}
