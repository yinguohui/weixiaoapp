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
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.sell.adapter.GoodsAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.query.GoodsQuery;
import com.xihua.wx.weixiao.query.PageResult;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.GoodsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SellBuyActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,iv_search;
    XRecyclerView xc_goods;
    private LinearLayoutManager manager;
    GoodsQuery query = new GoodsQuery();
    GoodsAdapter goodsAdapter;
    EditText et_goodsbuy;
    List<GoodsResponse> bean = new ArrayList<GoodsResponse>();
    Gson gson = new Gson();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtil.showToast(SellBuyActivity.this,"网络错误");
                    break;
                case 1:
                    goodsAdapter = new GoodsAdapter(bean,SellBuyActivity.this);
                    xc_goods.setAdapter(goodsAdapter);
                    goodsAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    ToastUtil.showToast(SellBuyActivity.this,"暂无数据");
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
                query.setCurrentPage(1);
                query.setPageSize(10);
                initData(query);
                xc_goods.refreshComplete();
            }
            //加载更多
            @Override
            public void onLoadMore() {
                if (bean.size()==10){
                    query.setCurrentPage(query.getCurrentPage()+1);
                    query.setPageSize(10);
                    initData(query);
                    xc_goods.refreshComplete();
                }

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
    private void initData(GoodsQuery query){
        OkHttpUtil.doPost("http://192.168.43.240:8080/goods/queryAll", gson.toJson(query), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.fillInStackTrace();
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    PageResult pageResult = gson.fromJson(gson.toJson(apiResult.getData()), new TypeToken<PageResult<GoodsResponse>>() {
                    }.getType());
                    if (apiResult.getCode()==200){
                        if (pageResult.getItems().size()>0){
                            bean = pageResult.getItems();
                            handler.sendEmptyMessage(1);
                        }else {
                            handler.sendEmptyMessage(2);
                        }
                    }else {
                        handler.sendEmptyMessage(-1);
                    }
                }
            }
        });

        handler.sendEmptyMessage(1);
    }

    private void search(){
        ToastUtil.showToast(SellBuyActivity.this,"寻早");
    }
}
