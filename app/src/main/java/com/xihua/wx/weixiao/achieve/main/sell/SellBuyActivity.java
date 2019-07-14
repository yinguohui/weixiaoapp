package com.xihua.wx.weixiao.achieve.main.sell;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.sell.adapter.GoodsAdapter;
import com.xihua.wx.weixiao.bean.GoodsRequestBean;
import com.xihua.wx.weixiao.bean.GoodsResponseBean;
import com.xihua.wx.weixiao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SellBuyActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,iv_search;
    XRecyclerView xc_goods;
    private LinearLayoutManager manager;
    GoodsAdapter goodsAdapter;
    EditText et_goodsbuy;
    List<GoodsResponseBean.GoodsResponse>  list = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    goodsAdapter = new GoodsAdapter(list,SellBuyActivity.this);
                    xc_goods.setAdapter(goodsAdapter);
                    xc_goods.refreshComplete();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_buy);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        iv_search = findViewById(R.id.iv_search);
        xc_goods = findViewById(R.id.xc_goods);
        et_goodsbuy = findViewById(R.id.et_goodsbuy);

        manager = new LinearLayoutManager(this);
        xc_goods.setLayoutManager(manager);
        //允许刷新，加载更多
        xc_goods.setPullRefreshEnabled(true);
        xc_goods.setLoadingMoreEnabled(true);
        xc_goods.setRefreshProgressStyle(ProgressStyle.SysProgress);
        xc_goods.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        xc_goods.setArrowImageView(R.drawable.icon_down_fresh);

        //加载刷新组件
        xc_goods.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                initData();
            }
            //加载更多
            @Override
            public void onLoadMore() {
                xc_goods.refreshComplete();
            }
        });
        xc_goods.refresh();

        iv_back.setOnClickListener(this);
        iv_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
            case R.id.iv_search:
                search();
                break;
        }
    }
    private void initData(){
        String test = "{\n" +
                "\t\"code\": 200,\n" +
                "\t\"list\": [{\n" +
                "\t\t\"goodsName\": \"物品1\",\n" +
                "\t\t\"goodsPlace\": \"的信院\",\n" +
                "\t\t\"goodsPrice\": 12.2,\n" +
                "\t\t\"goodsImg\": \"http://img0.imgtn.bdimg.com/it/u=2604711864,1859130880&fm=26&gp=0.jpg\"\n" +
                "\t}]\n" +
                "}";
        Gson gson = new Gson();
        GoodsResponseBean bean = gson.fromJson(test,GoodsResponseBean.class);
        list = bean.getList();
        handler.sendEmptyMessage(1);
    }

    private void search(){
        ToastUtil.showToast(SellBuyActivity.this,"寻早");
    }
}
