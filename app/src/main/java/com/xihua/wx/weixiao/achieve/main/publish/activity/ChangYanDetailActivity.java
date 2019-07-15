package com.xihua.wx.weixiao.achieve.main.publish.activity;

import android.content.Context;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.CommentBean;
import com.xihua.wx.weixiao.bean.CommentDetailBean;
import com.xihua.wx.weixiao.achieve.main.publish.adapter.CommentExpandAdapter;
import com.xihua.wx.weixiao.achieve.main.publish.views.CommentExpandableListView;
import com.xihua.wx.weixiao.bean.ReplyDetailBean;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;

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
    private static final String TAG = "CommentDetailActivity";
    private android.support.v7.widget.Toolbar toolbar;
    private TextView tv_comment;
    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private CommentBean commentBean;
    private CommentDetailBean commentsList;
    private BottomSheetDialog dialog;
    private Gson gson=new Gson();
    private CircleNetworkImageImage userLogo;
    private TextView time;
    private TextView detail_page_content;
    private TextView detail_page_userName;
    private NineGridImageView detail_page_img;
    private Map<String,String> map = new HashMap<>();
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
        String test = "{\n" +
                "\t\"topicId\": 1152,\n" +
                "\t\"userName\": \"花花\",\n" +
                "\t\"userImg\": \"\",\n" +
                "\t\"topicImg\": \"\",\n" +
                "\t\"topicContent\": \"今天天气真诰\",\n" +
                "\t\"topicCreate\": 112121,\n" +
                "\t\"topicStatus\": 1,\n" +
                "\t\"topicComment\": 12,\n" +
                "\t\"replyList\": [{\n" +
                "\t\t\"reviewId\": 1212,\n" +
                "\t\t\"reviewUserName\": \"轩轩\",\n" +
                "\t\t\"reviewUserImg\": \"\",\n" +
                "\t\t\"reviewContent\": \"撒旦撒旦\",\n" +
                "\t\t\"reviewStatus\": 1,\n" +
                "\t\t\"reviewCreateTime\": 1121212\n" +
                "\t}, {\n" +
                "\t\t\"reviewId\": 1212,\n" +
                "\t\t\"reviewUserName\": \"轩轩\",\n" +
                "\t\t\"reviewUserImg\": \"\",\n" +
                "\t\t\"reviewContent\": \"撒旦撒旦\",\n" +
                "\t\t\"reviewStatus\": 1,\n" +
                "\t\t\"reviewCreateTime\": 1121212\n" +
                "\t}]\n" +
                "\n" +
                "}";
       // map.put("userid",SpUtil.getString(CommentDetailActivity.this,"userid",""));
        map.put("titleid",getIntent().getStringExtra("titleid"));
        //初始化视图
        toolbar =  findViewById(R.id.toolbar);
        expandableListView = findViewById(R.id.detail_page_lv_comment);
        tv_comment = findViewById(R.id.detail_page_do_comment);
        //
        userLogo = findViewById(R.id.detail_page_userLogo);
        detail_page_userName = findViewById(R.id.detail_page_userName);
        time = findViewById(R.id.detail_page_time);
        detail_page_content = findViewById(R.id.detail_page_content);
        detail_page_img =findViewById(R.id.detail_page_img);
        getCommentsList(map.get("titleid"));
        //initdata();
        tv_comment.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("详情");
        commentsList = gson.fromJson(test,CommentDetailBean.class);
        handler.sendEmptyMessage(4);
        initExpandableListView(commentsList.getReplyList());

    }
    //初始化发布者信息
    private void initdata(){
        OkHttpUtil.doGet("http://192.168.43.240:8080/title/gettitlebyid?title_id="+map.get("titleid"), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
               // ToastUtil.showToast(CommentDetailActivity.this,"访问错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    //title = gson.fromJson(response.body().string(),TitleList.class);
                    handler.sendEmptyMessage(4);
                }else {
                    //ToastUtil.showToast(CommentDetailActivity.this,"访问错误");
                }
            }
        });
    }
    private void inittitle(){
        VolleyUtils.loadImage(ChangYanDetailActivity.this,userLogo,commentsList.getUserImg());
        detail_page_userName.setText(commentsList.getUserName());
        time.setText(commentsList.getTopicCreate());
        detail_page_content.setText(commentsList.getTopicContent());
        List<String> stringList= new ArrayList<>();
        String[] strings = commentsList.getTopicImg().split("%");
        for (String s:strings) {
            stringList.add(s);
        }
        detail_page_img.setAdapter(nineGridImageViewAdapter);
        detail_page_img.setImagesData(stringList);


    }

    //初始化评论信息
    private void getCommentsList(String titleid){

        OkHttpUtil.doGet("http://192.168.43.240:8080/title/reply?title_id="+titleid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
               // ToastUtil.showToast(CommentDetailActivity.this,"加载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    commentBean = gson.fromJson(response.body().string(), CommentBean.class);
                    if (commentBean.getData().getList().size()>0){
                        //commentsList = commentBean.getData().getList();
                    }else {
                 //       ToastUtil.showToast(CommentDetailActivity.this,"暂无评论");
                    }
                   // initExpandableListView(commentsList);
                }
            }
        });
    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<ReplyDetailBean> replyDetailBeans){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, replyDetailBeans);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<replyDetailBeans.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+replyDetailBeans.get(groupPosition).getReviewId());
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

        }
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
                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    ReplyDetailBean detailBean = new ReplyDetailBean("", "刚刚");
                    HashMap<String,String> commentdata = new HashMap<>();
                    adapter.addTheCommentData(detailBean);
                    //本地文件取
                    commentdata.put("reviewUserId",map.get("userid"));
                    //评论
                    commentdata.put("reviewTitleId",map.get("titleid"));
                    commentdata.put("reviewContent",commentContent);
                    OkHttpUtil.doPost("http://192.168.43.240:8080/title/addcomment", commentdata, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            //ToastUtil.showToast(CommentDetailActivity.this,"评论失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()){
                                    commentresult(response.body().string());
                                }
                        }
                    });
                    //Toast.makeText(CommentDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                   // Toast.makeText(CommentDetailActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence)){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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