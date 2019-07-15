package com.xihua.wx.weixiao.achieve.main.publish.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeAdapter;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeComparator;
import com.xihua.wx.weixiao.bean.TimeData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//我发表的
public class BelongMeActivity extends AppCompatActivity {
    //存储列表数据
    List<TimeData> list = new ArrayList<>();
    TimeAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belongme);
        init();
    }
    private void init(){
        //初始化数据
        initData();
        // 将数据按照时间排序
        TimeComparator comparator = new TimeComparator();
        Collections.sort(list, comparator);
        // recyclerview绑定适配器
        RecyclerView rlView = (RecyclerView) findViewById(R.id.activity_rlview);
        rlView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TimeAdapter(this, list);
        rlView.setAdapter(adapter);


    }

    private void initData() {
        list.add(new TimeData("20170710", "我是第一个数据"));
        list.add(new TimeData("20140709", "我是多数据模块第一个数据"));
        list.add(new TimeData("20140709", "我是多数据模块第二个数据"));
        list.add(new TimeData("20140708", "我是多数据模块第三个数据"));
        list.add(new TimeData("20140709", "我是多数据模块第一个数据"));
        list.add(new TimeData("20140708", "我是多数据模块第二个数据"));
        list.add(new TimeData("20140708", "我是多数据模块第三个数据"));
        list.add(new TimeData("20140706", "我是最后一个数据"));
    }
}
