package com.xihua.wx.weixiao.achieve.main.sell;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import com.xihua.wx.weixiao.vo.request.GoodsRequestBean;
import com.xihua.wx.weixiao.utils.GsonTypeAdapter;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.StringUtils;
import com.xihua.wx.weixiao.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SellSellActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    EditText sell_name,sell_area,sell_price,sell_description;
    RecyclerView recyclerView;
    Button sell_submit;
    Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>(){
    }.getType(), new GsonTypeAdapter()).create();

    private List<String> stringList = new ArrayList<>();
    GridImageAdapter adapter;
    List<LocalMedia> selectList = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtil.showToast(SellSellActivity.this,"网络错误");
                    break;
                case 1:
                    ToastUtil.showToast(SellSellActivity.this,"请输入正确的信息");
                    break;
                case 2:
                    ToastUtil.showToast(SellSellActivity.this,"提交成功");
                    break;
                case 3:
                    ToastUtil.showToast(SellSellActivity.this,"提交失败");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_sell);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        sell_name = findViewById(R.id.sell_name);
        sell_area = findViewById(R.id.sell_area);
        sell_price = findViewById(R.id.sell_price);
        sell_description = findViewById(R.id.sell_description);
        recyclerView = findViewById(R.id.recyclerView);
        sell_submit = findViewById(R.id.sell_submit);

        iv_back.setOnClickListener(this);
        sell_submit.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager manager = new GridLayoutManager(SellSellActivity.this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        TestActivity testActivity = new TestActivity(SellSellActivity.this);
        adapter = new GridImageAdapter(SellSellActivity.this, testActivity.onAddPicClickListener);
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
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
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
            case R.id.sell_submit:
                sendsell();
                break;
        }
    }
    private Boolean check(){
        if (StringUtils.judgeIsBlack(sell_name)
                &&StringUtils.judgeIsBlack(sell_area)
                &&StringUtils.judgeIsBlack(sell_price)
                &&StringUtils.judgeIsBlack(sell_description)){
            return true;
        }
        return false;
    }
    private void sendsell(){
        if (check()){
            String id = SpUtil.getString(SellSellActivity.this, "userid", "-1");
            if ("-1".equals(id)){
                ToastUtil.showToast(SellSellActivity.this,"请登录");
                return;
            }
            GoodsRequestBean requestBean = new GoodsRequestBean();
            requestBean.setGoodsName(StringUtils.getEidtContent(sell_name));
            requestBean.setGoodsPlace(StringUtils.getEidtContent(sell_area));
            requestBean.setGoodsPrice(StringUtils.getEidtContentDouble(sell_price));
            requestBean.setGoodsDesciption(StringUtils.getEidtContent(sell_description));
            requestBean.setGoodsType(0);
            requestBean.setGoodsUserNo(Integer.parseInt(id));
            OkHttpUtil.uploadmany("http://192.168.43.240:8080/goods/add", MapUtil.objectToMap(requestBean), stringList, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(0);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        ApiResult<Double> apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                        if (apiResult.getCode()==200){
                            if (apiResult.getData().intValue()>=1){
                                handler.sendEmptyMessage(2);
                                finish();
                            }else {
                                handler.sendEmptyMessage(3);
                            }
                        }else {
                            handler.sendEmptyMessage(3);
                        }
                    }
                }
            });
        }else {
            handler.sendEmptyMessage(1);
        }
    }
}
