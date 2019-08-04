package com.xihua.wx.weixiao.achieve.main.sell;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.adapter.GridImageAdapter;
import com.xihua.wx.weixiao.achieve.main.sell.adapter.GoodsAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.GoodsRequestBean;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.StringUtils;
import com.xihua.wx.weixiao.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    Gson gson = new Gson();
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
        adapter = new GridImageAdapter(SellSellActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(3);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(SellSellActivity.this).themeStyle(R.style.picture_QQ_style).openExternalPreview(position, selectList);
                            break;
                    }
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(SellSellActivity.this);
                } else {
                    Toast.makeText(SellSellActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            //相册or单独拍照
            if (true) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(SellSellActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(3)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(3)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE )// 多选 or 单选
                        // 是否可预览图片
                        .previewImage(true)// 是否可预览图片
                        //是否显示拍照按钮
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(SellSellActivity.this)
                        .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles
                        .maxSelectNum(3)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }

    };

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
                ||StringUtils.judgeIsBlack(sell_area)
                ||StringUtils.judgeIsBlack(sell_price)
                ||StringUtils.judgeIsBlack(sell_description)){
            return true;
        }
        return false;
    }
    private void sendsell(){
        if (check()){
            final GoodsRequestBean requestBean = new GoodsRequestBean();
            requestBean.setGoodsName(StringUtils.getEidtContent(sell_name));
            requestBean.setGoodsPlace(StringUtils.getEidtContent(sell_area));
            requestBean.setGoodsPrice(StringUtils.getEidtContentDouble(sell_price));
            requestBean.setGoodsDesciption(StringUtils.getEidtContent(sell_description));
            requestBean.setGoodsType(0);
            requestBean.setGoodsUserNo(1512);
            OkHttpUtil.uploadmany("http://192.168.43.240:8080/goods/add", MapUtil.objectToMap(requestBean), stringList, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(0);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        ApiResult<Integer> apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                        if (apiResult.getCode()==200){
                            if (apiResult.getData()>=1){
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
