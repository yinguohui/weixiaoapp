package com.xihua.wx.weixiao.achieve.main.lost.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.LostinfoDetailBean;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LostInfoDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    CircleNetworkImageImage iv_header;
    NineGridImageView iv_img;
    TextView tv_name,tv_publish_time,tv_infoname,tv_type,tv_infolosttime,tv_infolostplace,tv_infolostdesciption;
    //九宫格图片
    private NineGridImageViewAdapter nineGridImageViewAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String o) {
            Glide.with(LostInfoDetailActivity.this).load(o).into(imageView);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostdetail);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_header = findViewById(R.id.iv_header);
        tv_name = findViewById(R.id.tv_name);
        tv_publish_time = findViewById(R.id.tv_publish_time);
        tv_infoname = findViewById(R.id.tv_infoname);
        tv_type = findViewById(R.id.tv_type);
        tv_infolosttime = findViewById(R.id.tv_infolosttime);
        tv_infolostplace = findViewById(R.id.tv_infolostplace);
        tv_infolostdesciption = findViewById(R.id.tv_infolostdesciption);
        iv_img = findViewById(R.id.iv_img);
        iv_back.setOnClickListener(this);
        String test ="{\n" +
                "\t\"userId\": 1111,\n" +
                "\t\"userImg\": \"http://tx.haiqq.com/uploads/allimg/170807/0P002OH-3.jpg\",\n" +
                "\t\"userName\": \"微笑\",\n" +
                "\t\"lostinfoId\": 1111,\n" +
                "\t\"lostinfoCreateTime\": 1512121,\n" +
                "\t\"lostinfoTime\": 1512121,\n" +
                "\t\"lostinfoName\": \"身份证\",\n" +
                "\t\"lostinfoType\": \"丢失\",\n" +
                "\t\"lostinfoPlace\": \"三教\",\n" +
                "\t\"lostinfoDescription\": \"必有重谢\",\n" +
                "\t\"lostinfoImg\": \"http://tx.haiqq.com/uploads/allimg/170807/0P002OH-3.jpg@&&@http://tx.haiqq.com/uploads/allimg/170807/0P002OH-3.jpg\"\n" +
                "}";
        Gson gson = new Gson();
        LostinfoDetailBean bean = gson.fromJson(test,LostinfoDetailBean.class);
        VolleyUtils.loadImage(LostInfoDetailActivity.this,iv_header,bean.getUserImg());
        tv_name.setText(bean.getUserName());
        tv_publish_time.setText(new Date(bean.getLostinfoCreateTime()).toString());
        tv_infoname.setText(bean.getLostinfoName());
        tv_type.setText(bean.getLostinfoType());
        tv_infolosttime.setText(new Date(bean.getLostinfoTime()).toString());
        tv_infolostplace.setText(bean.getLostinfoPlace());
        tv_infolostdesciption.setText(bean.getLostinfoDescription());

        List<String> stringList = new ArrayList<>();
        String[] strings = bean.getLostinfoImg().split("@&&@");
        for (String s : strings) {
            stringList.add(s);
        }
        iv_img.setAdapter(nineGridImageViewAdapter);
        iv_img.setImagesData(stringList);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
