package com.xihua.wx.weixiao.achieve.main.util;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.adapter.GridImageAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PhotoSelectorActivity  extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = "";
    private List<LocalMedia> selectList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    private ImageView left_back;
    private int themeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoselector);
        themeId = R.style.picture_default_style;
        recyclerView = findViewById(R.id.recycler);
        left_back = findViewById(R.id.left_back);
        left_back.setOnClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(PhotoSelectorActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(PhotoSelectorActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
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
                            //PictureSelector.create(MainMessageActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(PhotoSelectorActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(PhotoSelectorActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(PhotoSelectorActivity.this).externalPictureAudio(media.getPath());
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
                    PictureFileUtils.deleteCacheDirFile(PhotoSelectorActivity.this);
                } else {
                    Toast.makeText(PhotoSelectorActivity.this,
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
                PictureSelector.create(PhotoSelectorActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        //PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .selectionMode(PictureConfig.MULTIPLE )// 多选 or 单选
                        // 是否可预览图片
                        .previewImage(true)// 是否可预览图片
                        //.previewVideo(false)// 是否可预览视频
                        //.enablePreviewAudio(false) // 是否可播放音频
                        //是否显示拍照按钮
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        // 是否显示gif图片
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(true) // 裁剪是否可旋转图片
                        //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(PhotoSelectorActivity.this)
                        .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(themeId)// 主题样式设置 具体参考 values/styles
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
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
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
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
        switch (v.getId()) {
            case R.id.left_back:
                finish();
                break;
        }
    }

    /**
     * 自定义压缩存储地址
     *
     * @return
     */
    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }
}
