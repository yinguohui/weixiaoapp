package com.xihua.wx.weixiao.achieve.main.publish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.bean.ChangYanResponseBean;
import com.xihua.wx.weixiao.bean.LikeMessageList;
import com.xihua.wx.weixiao.utils.DateUtils;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.MineReviewResponse;

import java.util.List;

public class AboutMeAdapter extends XRecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<MineReviewResponse> list;

    public AboutMeAdapter(List<MineReviewResponse> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_aboutme,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(XRecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            MineReviewResponse bean = list.get(position);
            if (null!=bean.getUser().getUserImg()&&!bean.getUser().getUserImg().equals("")) {
                VolleyUtils.loadImage(context, ((ViewHolder) holder).iv_header, bean.getUser().getUserImg());
            }
            ((ViewHolder) holder).tv_name.setText(bean.getUser().getUserName());
            ((ViewHolder) holder).tv_time.setText(DateUtils.parseDate(bean.getReviewCreateTime()));

            ((ViewHolder) holder).tv_type.setText("评论了你:"+bean.getReviewContent());

            if (null!=bean.getTopic().getTopicImg()&&!bean.getTopic().getTopicImg().split("$%")[0].equals("")) {
                Glide.with(context).load(bean.getTopic().getTopicImg().split("$%")[0]).into(((ViewHolder) holder).iv_topic_img);
            }
            ((ViewHolder) holder).tv_topic_content.setText(bean.getTopic().getTopicContent());
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_like:
                //ToastUtil.showToast(context,"点赞加+1");
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleNetworkImageImage iv_header;
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_type;

        private ImageView iv_topic_img;
        public TextView tv_topic_content;

        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_header = itemView .findViewById(R.id.iv_header);
            tv_name =  itemView.findViewById(R.id.tv_name);
            tv_time =  itemView.findViewById(R.id.tv_time);
            tv_type = itemView.findViewById(R.id.tv_type);
            iv_topic_img = itemView.findViewById(R.id.iv_topic_img);
            tv_topic_content = itemView.findViewById(R.id.tv_topic_content);
        }
    }
}
