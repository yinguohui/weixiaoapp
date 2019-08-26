package com.xihua.wx.weixiao.achieve.main.publish.adapter;


import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.CommentDetailBean;
import com.xihua.wx.weixiao.bean.ReplyDetailBean;
import com.xihua.wx.weixiao.bean.Review;
import com.xihua.wx.weixiao.utils.DateUtils;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.ReviewResponse;

import java.util.ArrayList;
import java.util.List;

//评论和回复的适配器
public class CommentExpandAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "CommentExpandAdapter";
    private List<ReviewResponse> reviews;
    private Context context;
    private int pageIndex = 1;

    public CommentExpandAdapter(Context context, List<ReviewResponse> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public int getGroupCount() {
        return reviews.size();
    }


    //得到回复条数
    @Override
    public int getChildrenCount(int i) {
        return 0;

    }

    //得到某一个评论
    @Override
    public Object getGroup(int i) {
        return reviews.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //加载评论的数据
    @Override
    public View getGroupView(final int groupPosition, boolean isExpand, View convertView, ViewGroup viewGroup) {
        final GroupHolder groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, viewGroup, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        VolleyUtils.loadImage(context,groupHolder.logo,reviews.get(groupPosition).getUser().getUserImg());
        groupHolder.tv_name.setText(reviews.get(groupPosition).getUser().getUserName());
        groupHolder.tv_time.setText(DateUtils.parseDate(reviews.get(groupPosition).getReviewCreateTime()));
        groupHolder.tv_content.setText(reviews.get(groupPosition).getReviewContent());
        return convertView;
    }
   //加载回复的信息
    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
//        final ChildHolder childHolder;
//        if(convertView == null){
//            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item_layout,viewGroup, false);
//            childHolder = new ChildHolder(convertView);
//            convertView.setTag(childHolder);
//        }
//        else {
//            childHolder = (ChildHolder) convertView.getTag();
//        }
//
//        String replyUser = commentBeanList.get(groupPosition).getReplyList().get(childPosition).getUserName();
//        if(!TextUtils.isEmpty(replyUser)){
//            childHolder.tv_name.setText(replyUser + ":");
//        }else {
//            childHolder.tv_name.setText("无名"+":");
//        }
//        Log.e(TAG,replyUser);
//        childHolder.tv_content.setText(commentBeanList.get(groupPosition).getReplyList().get(childPosition).getRereviewContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder{
        private CircleNetworkImageImage logo;
        private TextView tv_name, tv_content, tv_time;
        private ImageView iv_like;
        public GroupHolder(View view) {
            logo = view.findViewById(R.id.comment_item_logo);
            tv_content =  view.findViewById(R.id.comment_item_content);
            tv_name =  view.findViewById(R.id.comment_item_userName);
            tv_time =  view.findViewById(R.id.comment_item_time);
        }
    }

    private class ChildHolder{
        private TextView tv_name, tv_content;
        public ChildHolder(View view) {
            tv_name =  view.findViewById(R.id.reply_item_user);
            tv_content =  view.findViewById(R.id.reply_item_content);
        }
    }


    /**
     * func:评论成功后插入一条数据
     * @param response 新的评论数据
     */
    public void addTheCommentData(ReviewResponse response){
        if(response!=null){

            reviews.add(response);
            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("评论数据为空!");
        }

    }

    /**
     * func:回复成功后插入一条数据
     * @param replyDetailBean 新的回复数据
     */
    public void addTheReplyData(ReplyDetailBean replyDetailBean, int groupPosition){
//        if(replyDetailBean!=null){
//            Log.e(TAG, "addTheReplyData: >>>>该刷新回复列表了:"+replyDetailBean.toString() );
//            if(commentBeanList.get(groupPosition).getReplyList() != null ){
//                commentBeanList.get(groupPosition).getReplyList().add(replyDetailBean);
//            }else {
//                List<ReplyDetailBean> replyList = new ArrayList<>();
//                replyList.add(replyDetailBean);
//                commentBeanList.get(groupPosition).setReplyList(replyList);
//            }
//
//          //  notifyDataSetChanged();
//        }else {
//            throw new IllegalArgumentException("回复数据为空!");
//        }

    }
    /**
     * by moos on 2018/04/20
     * func:添加和展示所有回复
     * @param replyBeanList 所有回复数据
     * @param groupPosition 当前的评论
     */
    private void addReplyList(List<ReplyDetailBean> replyBeanList, int groupPosition){
//        if(commentBeanList.get(groupPosition).getReplyList() != null ){
//            commentBeanList.get(groupPosition).getReplyList().clear();
//            commentBeanList.get(groupPosition).getReplyList().addAll(replyBeanList);
//        }else {
//
//            commentBeanList.get(groupPosition).setReplyList(replyBeanList);
//        }
//
//        notifyDataSetChanged();
    }
}
