package com.xihua.wx.weixiao.achieve.main.publish.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.DiscussActivity;
import com.xihua.wx.weixiao.achieve.main.lost.activity.LostInfoDetailActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.CommentBean;
import com.xihua.wx.weixiao.bean.CommentDetailBean;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.CommentExpandAdapter;
import com.xihua.wx.weixiao.achieve.main.publish.views.CommentExpandableListView;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.bean.ReplyDetailBean;
import com.xihua.wx.weixiao.bean.Review;
import com.xihua.wx.weixiao.query.TopicQuery;
import com.xihua.wx.weixiao.utils.DateUtils;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.request.ReviewRequest;
import com.xihua.wx.weixiao.vo.response.ReviewResponse;
import com.xihua.wx.weixiao.vo.response.TopicDetailResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//评论详情
public class ChangYanDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private android.support.v7.widget.Toolbar toolbar;
    private TextView tv_comment;
    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private TopicDetailResponse topicDetailResponse = new TopicDetailResponse();
    private List<ReviewResponse> list =new ArrayList<>();
    private BottomSheetDialog dialog;
    private Gson gson=new Gson();
    private CircleNetworkImageImage userLogo;
    private TextView time,detail_page_content,detail_page_userName;
    private NineGridImageView detail_page_img;
    private ImageView comment_delete;
    private LinearLayout ll_user;
    //九宫格图片
    private NineGridImageViewAdapter nineGridImageViewAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String o) {
            Glide.with(ChangYanDetailActivity.this).load(o).into(imageView);
        }
    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initExpandableListView(list);
                    break;
                case 2:
                    ToastUtil.showToast(ChangYanDetailActivity.this,"评论成功");
                    finish();
                    break;
                case 3:
                    ToastUtil.showToast(ChangYanDetailActivity.this,"删除成功");
                    finish();
                    break;
                case 4:
                    inittitle();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        initView();
    }

    private void initView() {
        //初始化视图
        toolbar =  findViewById(R.id.toolbar);
        expandableListView = findViewById(R.id.detail_page_lv_comment);
        tv_comment = findViewById(R.id.detail_page_do_comment);
        userLogo = findViewById(R.id.detail_page_userLogo);
        detail_page_userName = findViewById(R.id.detail_page_userName);
        time = findViewById(R.id.detail_page_time);
        detail_page_content = findViewById(R.id.detail_page_content);
        detail_page_img =findViewById(R.id.detail_page_img);
        //初始化数据
        initReviewdata();
        initdata();

        //初始化动作
        tv_comment.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("详情");
    }
    //初始化主题
    private void initdata(){
        IdRequest idRequest = new IdRequest();
        int id = getIntent().getIntExtra("topic_id",-1);
        if (-1==id){
            return;
        }
        idRequest.setId(id);
        OkHttpUtil.doPost("http://192.168.43.240:8080/topic/getTopicById",gson.toJson(idRequest), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        topicDetailResponse = gson.fromJson(gson.toJson(apiResult.getData()),TopicDetailResponse.class);
                        handler.sendEmptyMessage(4);
                    }
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }
    //初始化评论
    private void initReviewdata(){
        IdRequest idRequest = new IdRequest();
        int id = getIntent().getIntExtra("topic_id",-1);
        if (-1==id){
            return;
        }
        idRequest.setId(id);
        OkHttpUtil.doPost("http://192.168.43.240:8080/review/querybytopicdd",gson.toJson(idRequest), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        list = gson.fromJson(gson.toJson(apiResult.getData()),new TypeToken<List<ReviewResponse>>(){}.getType());
                        handler.sendEmptyMessage(1);
                    }
                }else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }


    private void inittitle(){
        String id = SpUtil.getString(ChangYanDetailActivity.this, "userid", "-1");
        if ("-1".equals(id)){
            ToastUtil.showToast(ChangYanDetailActivity.this,"请登录");
            return;
        }
        if (topicDetailResponse.getTopicUserId() == Integer.parseInt(id)){
            comment_delete = findViewById(R.id.comment_delete);
            comment_delete.setOnClickListener(this);
            comment_delete.setVisibility(View.VISIBLE);
        }else {
            ll_user = findViewById(R.id.ll_user);
            ll_user.setOnClickListener(this);
        }
        VolleyUtils.loadImage(ChangYanDetailActivity.this,userLogo,topicDetailResponse.getUser().getUserImg());
        detail_page_userName.setText(topicDetailResponse.getUser().getUserName());
        time.setText(DateUtils.parseDate(topicDetailResponse.getTopicCreateTime()));
        detail_page_content.setText(topicDetailResponse.getTopicContent());
        List<String> stringList= new ArrayList<>();
        String[] strings = topicDetailResponse.getTopicImg().split("$%");
        for (String s:strings) {
            stringList.add(s);
        }
        detail_page_img.setAdapter(nineGridImageViewAdapter);
        detail_page_img.setImagesData(stringList);
    }


    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(List<ReviewResponse> reviews){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, reviews);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i < reviews.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                if(isExpanded){
                    expandableListView.collapseGroup(groupPosition);
                }else {
                    expandableListView.expandGroup(groupPosition, true);
                }
                return true;
            }
        });


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_page_do_comment:
                showCommentDialog();
                break;
            case R.id.ll_user:
                Intent intent = new Intent(ChangYanDetailActivity.this,DiscussActivity.class);
                intent.putExtra("send_id",list.get(0).getUser().getUserId());
                startActivity(intent);
                break;
            case R.id.comment_delete:
                delete();
                break;

        }
    }

    private void delete() {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(getIntent().getIntExtra("topic_id",-1));
        OkHttpUtil.doPost("http://192.168.43.240:8080/topic/deleteTopic", gson.toJson(idRequest),new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-1);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                    if (apiResult.getCode()==200){
                        handler.sendEmptyMessage(3);
                    }
                }
            }
        });
    }

    /**
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    dialog.dismiss();
                    ReviewRequest reviewRequest = new ReviewRequest();
                    String id = SpUtil.getString(ChangYanDetailActivity.this, "userid", "-1");
                    reviewRequest.setReviewUserId(Integer.parseInt(id));
                    reviewRequest.setReviewTopicId(getIntent().getIntExtra("topic_id",-1));
                    reviewRequest.setReviewContent(commentContent);
                   // adapter.addTheCommentData(reviewResponse);
                    OkHttpUtil.doPost("http://192.168.43.240:8080/review/add", gson.toJson(reviewRequest), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            handler.sendEmptyMessage(-1);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            ApiResult apiResult = gson.fromJson(response.body().string(),ApiResult.class);
                            if (apiResult.getCode()==200){
                                handler.sendEmptyMessage(2);
                            }
                        }
                    });
                    //Toast.makeText(CommentDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                   // Toast.makeText(CommentDetailActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
    /*
    * 添加回复结果
    * */

    private void commentresult(String data){
  //      try {
//            UtilResult utilResult = gson.fromJson(data,UtilResult.class);
//            if (utilResult.getStatus().equals("success")){
//                handler.sendEmptyMessage(1);
//            }else
//                handler.sendEmptyMessage(2);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
}