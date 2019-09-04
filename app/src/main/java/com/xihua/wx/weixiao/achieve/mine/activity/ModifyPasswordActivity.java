package com.xihua.wx.weixiao.achieve.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.HuanXinTokenBean;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.UpPwdUtil;
import com.xihua.wx.weixiao.vo.request.PasswordRequest;
import com.xihua.wx.weixiao.vo.request.UserRequest;
import com.xihua.wx.weixiao.vo.response.UserResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private TextView tv_modifypassword;
    private EditText et_password,oldpassword,sure_password;
    String data = "";
    Gson gson = new Gson();
    private Map<String,String> map = new HashMap<>();
    private HuanXinTokenBean tokenBean;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (null != msg) {
                String result = msg.obj.toString();
                Log.e("xxx", result);
                tokenBean = gson.fromJson(result, HuanXinTokenBean.class);
            }
        }
    };
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
        oldpassword = findViewById(R.id.oldpassword);
        sure_password = findViewById(R.id.sure_password);

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
        if (!sure_password.getText().toString().equals(et_password.getText().toString())){
            ToastUtil.showToast(ModifyPasswordActivity.this,"两次密码不一致");
            return;
        }
        String id= SpUtil.getString(ModifyPasswordActivity.this, "userid", "-1");
        PasswordRequest request = new PasswordRequest();
        request.setNewPassword(et_password.getText().toString());
        request.setUserId(Integer.parseInt(id));
        request.setOldPasswrod(oldpassword.getText().toString());
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/updatepassword", gson.toJson(request) , new Callback() {
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
            if ((Double) apiResult.getData()>=1.0){
                String id= SpUtil.getString(ModifyPasswordActivity.this, "userid", "-1");
                ToastUtil.showToast(ModifyPasswordActivity.this,"修改成功");
                updatePass("15196622412",sure_password.getText().toString());
            }else {
                ToastUtil.showToast(ModifyPasswordActivity.this,"修改失败");
            }
        }else {
            ToastUtil.showToast(ModifyPasswordActivity.this,apiResult.getMessage());
        }
    }


    private void getToken() {
        String baseUrl = "https://a1.easemob.com/1105190823010579/weixiao/token";
        String string = "{\"grant_type\":\"client_credentials\",\"client_id\":\"YXA6gR-g7xedTHysNbvdtH6inA\",\"client_secret\":\"YXA6E-ue_eihYtrsQfindKD-BCAQ-jg\"}";
        OkHttpUtil.doPost(baseUrl, string, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = mHandler.obtainMessage();
                message.obj = response.body().string();
                message.sendToTarget();
            }
        });
    }

    public  void updatePass(String user,String password) {
        getToken();
        if (null==tokenBean){
            return;
        }
        OkHttpClient client = new OkHttpClient();
        String baseUrl = "https://a1.easemob.com/1105190823010579/weixiao/users/" +
                user + "/password";
        String s = "{\"newpassword\":"+password+"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        String access_token = tokenBean.getAccess_token();
        Log.e("xxx", access_token);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + access_token)
                .url(baseUrl)
                .put(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("xxx", "fail");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e("xxx", response.body().string());
            }
        });
    }

}
