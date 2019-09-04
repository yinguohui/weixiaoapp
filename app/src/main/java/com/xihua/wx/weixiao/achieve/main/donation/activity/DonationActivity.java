package com.xihua.wx.weixiao.achieve.main.donation.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.adapter.GridImageAdapter;
import com.xihua.wx.weixiao.achieve.main.util.TestActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.DonationBean;
import com.xihua.wx.weixiao.bean.DonationRequest;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.StringUtils;
import com.xihua.wx.weixiao.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DonationActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_donation_name,et_donation_num,et_donation_location,et_donation_time,et_donation_info;
    Button bt_donation_sure;
    DonationRequest request = new DonationRequest();
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GridImageAdapter adapter;
    Gson gson = new Gson();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtil.showToast(DonationActivity.this,"网络错误");
                    break;
                case 1:
                    ToastUtil.showToast(DonationActivity.this,"提交成功");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        init();
    }
    private void init(){
       findViewById(R.id.iv_back).setOnClickListener(this);

        et_donation_name = findViewById(R.id.et_donation_name);
        et_donation_num = findViewById(R.id.et_donation_num);
        et_donation_location = findViewById(R.id.et_donation_location);
        et_donation_time = findViewById(R.id.et_donation_time);
        et_donation_info = findViewById(R.id.et_donation_info);
        bt_donation_sure = findViewById(R.id.bt_donation_sure);

        initimage();
        bt_donation_sure.setOnClickListener(this);
        //et_donation_time.setOnTouchListener(this);

    }
    private void initimage(){
        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager manager = new GridLayoutManager(DonationActivity.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        TestActivity testActivity = new TestActivity(DonationActivity.this);

        adapter = new GridImageAdapter(DonationActivity.this, testActivity.onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(3);
        recyclerView.setAdapter(adapter);
        testActivity.setAdapter(adapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia media : selectList) {
                        stringList.add(media.getCompressPath());
                        Log.i("图片-----》", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_donation_sure:
                commit();
                break;
        }
    }
    private Boolean check(){
        if (StringUtils.judgeIsBlack(et_donation_info)
                ||StringUtils.judgeIsBlack(et_donation_location)
                ||StringUtils.judgeIsBlack(et_donation_name)
                ||StringUtils.judgeIsBlack(et_donation_num)
                ||StringUtils.judgeIsBlack(et_donation_time)){
            return true;
        }
        return false;
    }


    private void commit(){
        if (check()){
            request.setDonationName(StringUtils.getEidtContent(et_donation_name));
            request.setDoantionPlace(StringUtils.getEidtContent(et_donation_location));
            request.setDoantionDescrption(StringUtils.getEidtContent(et_donation_info));
            request.setDonationNum(StringUtils.getEidtContentInteger(et_donation_num));
            request.setDoantionTime(System.currentTimeMillis());
            String id = SpUtil.getString(DonationActivity.this, "userid", "-1");
            if ("-1".equals(id)) {
                ToastUtil.showToast(DonationActivity.this, "请登录");
                return;
            }
            request.setDonationUserId(Integer.parseInt(id));
            send();
        }
        else {
            ToastUtil.showToast(DonationActivity.this,"请确认所有数据都已填写！");
        }
    }
    private void send(){
        OkHttpUtil.uploadmany("http:192.168.43.240:8080/donation/add", MapUtil.objectToMap(request), stringList, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        handler.sendEmptyMessage(1);
                        finish();
                    }else {
                        handler.sendEmptyMessage(-1);
                    }
                }
            }
        });
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = View.inflate(this, R.layout.date_time_dialog, null);
//        final DatePicker datePicker =  view.findViewById(R.id.date_picker);
//        final TimePicker timePicker =  view.findViewById(R.id.time_picker);
//        builder.setView(view);
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis());
//        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
//
//        timePicker.setIs24HourView(true);
//        timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
//        timePicker.setCurrentMinute(Calendar.MINUTE);
//
//        final int inType = et_donation_time.getInputType();
//        et_donation_time.setInputType(InputType.TYPE_NULL);
//        et_donation_time.onTouchEvent(event);
//        et_donation_time.setInputType(inType);
//        et_donation_time.setSelection(et_donation_time.getText().length());
//
//            builder.setTitle("选取时间");
//            builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    StringBuffer sb = new StringBuffer();
//                    sb.append(String.format("%d-%02d-%02d",
//                            datePicker.getYear(),
//                            datePicker.getMonth() + 1,
//                            datePicker.getDayOfMonth()));
//                    sb.append("  ");
//                    sb.append(timePicker.getCurrentHour())
//                            .append(":").append(timePicker.getCurrentMinute());
//
//                    et_donation_time.setText(sb);
//
//                    dialog.cancel();
//                }
//            });
//        Dialog dialog = builder.create();
//        dialog.show();
//
//        return true;
//    }
}
