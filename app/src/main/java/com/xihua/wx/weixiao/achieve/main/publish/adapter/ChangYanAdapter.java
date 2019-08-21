package com.xihua.wx.weixiao.achieve.main.publish.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.activity.ChangYanDetailActivity;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.TopicResponse;

import java.util.ArrayList;
import java.util.List;

public class ChangYanAdapter extends XRecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<TopicResponse> list;
    //九宫格图片
    private NineGridImageViewAdapter nineGridImageViewAdapter = new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String o) {
            Glide.with(context).load(o).into(imageView);
        }
    };

    public ChangYanAdapter(List<TopicResponse> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_changyan,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(XRecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final TopicResponse bean = list.get(position);
            if (!bean.getUser().getUserImg().equals("")) {
                VolleyUtils.loadImage(context, ((ViewHolder) holder).iv_header, bean.getUser().getUserImg());
            }
            ((ViewHolder) holder).tv_name.setText(bean.getUser().getUserName());
            ((ViewHolder) holder).tv_time.setText(bean.getTopicCreateTime()+"");
            ((ViewHolder) holder).tv_content.setText(bean.getTopicContent());
            ((ViewHolder) holder).iv_video.setAdapter(nineGridImageViewAdapter);
            List<String> stringList = new ArrayList<>();
            String[] strings = bean.getTopicImg().split("$%");
            for (String s : strings) {
                stringList.add(s);
            }
            ((ViewHolder) holder).iv_video.setImagesData(stringList);
            ((ViewHolder) holder).tv_like.setText(bean.getTopicLike()+"");
            ((ViewHolder) holder).tv_comment.setText(bean.getTopicComment()+"");
            ((ViewHolder) holder).ll_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,ChangYanDetailActivity.class));
                }
            });
            ((ViewHolder) holder).ll_like.setOnClickListener(this);
            ((ViewHolder) holder).ll_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = SpUtil.getString(context, "userid", "-1");
                    if ("-1".equals(id)){
                        ToastUtil.showToast(context,"请登录");
                        return;
                    }
                    Intent intent = new Intent(context,ChangYanDetailActivity.class);
                    intent.putExtra("topic_id",bean.getTopicId());
                    context.startActivity(intent);
                }
            });

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
                ToastUtil.showToast(context,"点赞加+1");
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleNetworkImageImage iv_header;
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_content;
        public NineGridImageView iv_video;

        public LinearLayout ll_like;
        public TextView tv_like;

        public LinearLayout ll_comment;
        public TextView tv_comment;

        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_header = itemView .findViewById(R.id.iv_header);
            tv_name =  itemView.findViewById(R.id.tv_name);
            tv_time =  itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_video =  itemView.findViewById(R.id.iv_video);

            ll_like = itemView.findViewById(R.id.ll_like);
            tv_like =  itemView.findViewById(R.id.tv_like);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }
    }
}
