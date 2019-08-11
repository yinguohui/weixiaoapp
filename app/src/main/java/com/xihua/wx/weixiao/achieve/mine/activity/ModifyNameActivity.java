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
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.UserResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModifyNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_modifyname;
    private EditText et_name;
    private String et_newname;
    private UserResponse userResult;
    Gson gson = new Gson();
    private Map<String,String> map = new HashMap<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    et_name.setText(userResult.getUserName());
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_modifyname);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        //tv_modifyname = findViewById(R.id.modifyname);
        //et_name = findViewById(R.id.et_name);

        iv_back.setOnClickListener(this);
        tv_modifyname.setOnClickListener(this);
        map.put("userId",SpUtil.getString(ModifyNameActivity.this,"userid",""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
//            case R.id.modifyname:
//                map.put("userName",et_name.getText().toString());
//                if (map.get("userName").equals("")||map.get("userName").length()>10){
//                    ToastUtil.showToast(ModifyNameActivity.this,"用户名不能为空或长度大于10");
//                    return;
//                }
//                modifyname(map);
//                break;
        }
    }
    private void modifyname(Map<String,String> data){
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/updateuserinfo", data , new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    fromresult(response.body().string());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        OkHttpUtil.doGet("http://192.168.43.240:8080/user/userinfo?user_id="+map.get("userId"), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    fromdata(response.body().string());
                }
            }
        });
    }

    private void fromdata(String data){
        ApiResult apiResult = gson.fromJson(data,ApiResult.class);
        if (apiResult.getCode()==200){
            userResult = gson.fromJson(gson.toJson(apiResult),UserResponse.class);
            handler.sendEmptyMessage(1);
        }else {
            handler.sendEmptyMessage(-1);
        }
    }
    private void  fromresult(String data){
        ApiResult apiResult = gson.fromJson(data,ApiResult.class);
        if (apiResult.getCode()==200){
            ToastUtil.showToast(ModifyNameActivity.this,"修改成功");
        }else {
            ToastUtil.showToast(ModifyNameActivity.this,apiResult.getMessage());
        }
    }
}
