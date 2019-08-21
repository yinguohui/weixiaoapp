package com.xihua.wx.weixiao.achieve.discuss;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.base.view.BaseFragment;
import com.xihua.wx.weixiao.achieve.discuss.adapter.MsgAdapter;
import com.xihua.wx.weixiao.achieve.discuss.adapter.RecyclerViewAdapter;
import com.xihua.wx.weixiao.achieve.main.publish.activity.BelongMeActivity;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.TimeAdapter;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.bean.MsgContentBean;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.RecyclerUtils;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.vo.response.ChatAllResponse;
import com.xihua.wx.weixiao.vo.response.TopicTimeLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiscussFragment extends BaseFragment implements RecyclerViewAdapter.onSlidingViewClickListener{

    private RecyclerView recycler;              //在xml 中 RecyclerView 布局
    private RecyclerViewAdapter rvAdapter;      //item_recycler 布局的 适配器
    private Gson gson = new Gson();
    private List<ChatAllResponse> list = new ArrayList<>();
    @Override
    public int getContentView() {
        return R.layout.fragment_discuss;
    }

    @Override
    public void initContentView(View viewContent) {
        //初始化RecyclerView
        recycler = viewContent.findViewById(R.id.recycler);
        //将 RecyclerView 布局设置为线性布局
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
       
    }


    //通过 position 区分点击了哪个 item
    @Override
    public void onItemClick(View view, int position) {
        //在这里可以做出一些反应（跳转界面、弹出弹框之类）
        Toast.makeText(getContext(),"点击了：" + position,Toast.LENGTH_SHORT).show();
    }

    //点击删除按钮时，根据传入的 position 调用 RecyclerAdapter 中的 removeData() 方法
    @Override
    public void onDeleteBtnCilck(View view, int position) {
        rvAdapter.removeData(position);
    }

    private void initDate(){
        String id = SpUtil.getString(getContext(), "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(getContext(),"请登录");
            return;
        }
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/chat/getallchatbyid",gson.toJson(idRequest) ,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<ChatAllResponse>>(){}.getType());
                        handler.sendEmptyMessage(1);
                    }else {
                        handler.sendEmptyMessage(0);
                    }
                }
            }
        });
    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    ToastUtil.showToast(getContext(),"网络错误");
                    break;
                case 0:
                    ToastUtil.showToast(getContext(),"暂无数据");
                    break;
                case 1:
                    updateInterface();
                    break;
            }
        }
    };
    public void updateInterface(){
        if (rvAdapter == null) {
            //实例化 RecyclerViewAdapter 并设置数据
            rvAdapter = new RecyclerViewAdapter(getContext(),
                    list);
            //将适配的内容放入 mRecyclerView
            recycler.setAdapter(rvAdapter);
            //控制Item增删的动画，需要通过ItemAnimator  DefaultItemAnimator -- 实现自定义动画
            recycler.setItemAnimator(new DefaultItemAnimator());
        }else {
            //强调通过 getView() 刷新每个Item的内容
            rvAdapter.notifyDataSetChanged();
        }
        //设置滑动监听器 （侧滑）
        rvAdapter.setOnSlidListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initDate();
    }
}
