package com.xihua.wx.weixiao.utils;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.HuanXinTokenBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpPwdUtil {

    private Gson gson = new Gson();
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

public static void main(String[] args) {
//    final UpPwdUtil upPwdUtil = new UpPwdUtil();
    final Gson gson = new Gson();
    HuanXinTokenBean tokenBean = new HuanXinTokenBean();
    OkHttpClient client = new OkHttpClient();
    String string = "{\"grant_type\":\"client_credentials\",\"client_id\":\"YXA6gR-g7xedTHysNbvdtH6inA\",\"client_secret\":\"YXA6E-ue_eihYtrsQfindKD-BCAQ-jg\"}";
    String baseUrl = "https://a1.easemob.com/1105190823010579/weixiao/token";
    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), string);
    Request request = new Request.Builder()
            .header("content-type","application/json")
            .post(requestBody)
            .url(baseUrl)
            .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {

        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

        }
    });
}
    public void getdate(String data){

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
