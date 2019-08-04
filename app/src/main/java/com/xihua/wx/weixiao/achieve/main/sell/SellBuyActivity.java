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
import com.xihua.wx.weixiao.achieve.main.info.activity.InfoActivity;
import com.xihua.wx.weixiao.achieve.main.sell.adapter.GoodsAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.GoodsRequestBean;
import com.xihua.wx.weixiao.bean.GoodsResponseBean;
import com.xihua.wx.weixiao.bean.IdQueryRequest;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;

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
    IdQueryRequest idQueryRequest = new IdQueryRequest();
    GoodsAdapter goodsAdapter;
    EditText et_goodsbuy;
    GoodsResponseBean bean = new GoodsResponseBean();
    Gson gson = new Gson();
    List<GoodsResponseBean.GoodsResponse>  list = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtil.showToast(SellBuyActivity.this,"网络错误");
                    break;
                case 1:
                    goodsAdapter = new GoodsAdapter(list,SellBuyActivity.this);
                    xc_goods.setAdapter(goodsAdapter);
                    xc_goods.refreshComplete();
                    goodsAdapter.notifyDataSetChanged();
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
                idQueryRequest.setSize(10);
                idQueryRequest.setCurrent(0);
                initData(idQueryRequest);
            }
            //加载更多
            @Override
            public void onLoadMore() {
                idQueryRequest.setCurrent(idQueryRequest.getSize()+idQueryRequest.getCurrent());
                idQueryRequest.setSize(10);
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
    private void initData(IdQueryRequest idQueryRequest){
        OkHttpUtil.doPost("http://192.168.43.240:8080/goods/queryAll", gson.toJson(idQueryRequest), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(0);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ApiResult<GoodsResponseBean> apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        if (apiResult.getData().getList().size()>=0){
                            bean = apiResult.getData();
                            list = bean.getList();
                            handler.sendEmptyMessage(2);
                        }else {
                            handler.sendEmptyMessage(3);
                        }
                    }else {
                        handler.sendEmptyMessage(3);
                    }
                }
            }
        });
//        String test = "{\n" +
//                "\t\"code\": 200,\n" +
//                "\t\"list\": [{\n" +
//                "\t\t\"goodsName\": \"物品1\",\n" +
//                "\t\t\"goodsPlace\": \"的信院\",\n" +
//                "\t\t\"goodsPrice\": 12.2,\n" +
//                "\t\t\"goodsImg\": \"http://img0.imgtn.bdimg.com/it/u=2604711864,1859130880&fm=26&gp=0.jpg\"\n" +
//                "\t}]\n" +
//                "}";
        handler.sendEmptyMessage(1);
    }

    private void search(){
        ToastUtil.showToast(SellBuyActivity.this,"寻早");
    }
}
