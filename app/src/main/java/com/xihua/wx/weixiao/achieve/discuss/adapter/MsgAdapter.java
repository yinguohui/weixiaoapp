package com.xihua.wx.weixiao.achieve.discuss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.discuss.DiscussActivity;
import com.xihua.wx.weixiao.bean.MsgContentBean;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.ChatAllResponse;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{
    private List<ChatAllResponse> list;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg,tv_nickname_recieve;
        CircleNetworkImageImage iv_avator_recieve;
        TextView rihgtMsg;
        CircleNetworkImageImage iv_avator_send;

        public ViewHolder(View view) {
            super(view);
            leftLayout =  view.findViewById(R.id.rl_receive);
            rightLayout = view.findViewById(R.id.ll_send);
            tv_nickname_recieve =view.findViewById(R.id.tv_nickname_recieve);
            leftMsg = view.findViewById(R.id.tv_msg_receive);
            rihgtMsg =  view.findViewById(R.id.tv_msg_send);
            iv_avator_recieve =view.findViewById(R.id.iv_avator_recieve);
            iv_avator_send =view.findViewById(R.id.iv_avator_send);
        }
    }

    public MsgAdapter(Context context,List<ChatAllResponse> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String id = SpUtil.getString(context, "userid", "-1");
        String userimg =SpUtil.getString(context, "userimg", "-1");
        ChatAllResponse response = list.get(position);
        if (null!=response.getChatSendId()){
            if (response.getChatSendId()!=Integer.parseInt(id)) {
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftMsg.setText(response.getChatContent());
                if (null!=response.getUser()&&null!=response.getUser().getUserImg()){
                    holder.tv_nickname_recieve.setText(response.getUser().getUserName());
                    VolleyUtils.loadImage(context, holder.iv_avator_recieve,response.getUser().getUserImg());
                }

            } else  {
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
                holder.rihgtMsg.setText(response.getChatContent());
                VolleyUtils.loadImage(context, holder.iv_avator_send,userimg);
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
