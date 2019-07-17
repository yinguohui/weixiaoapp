package com.xihua.wx.weixiao.achieve.main.lost.adapter;

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
import com.xihua.wx.weixiao.bean.GoodsResponseBean;
import com.xihua.wx.weixiao.bean.LostinfoList;

import java.util.List;

public class LostInfoAdapter extends XRecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<LostinfoList.LostinfoBean> list;


    public LostInfoAdapter(List<LostinfoList.LostinfoBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lostinfo,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            LostinfoList.LostinfoBean bean = list.get(position);
            Glide.with(context).load(bean.getLostinfoImg().split("$")[0]).into(((ViewHolder) holder).iv_lostinfo_img);
            ((ViewHolder) holder).tv_name.setText(bean.getLostinfoImg());
            ((ViewHolder) holder).tv_time.setText(bean.getLostinfoTime()+"");
            ((ViewHolder) holder).tv_description.setText(bean.getLostinfoDescription());


        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_lostinfo_img;
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_description;

        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_lostinfo_img = itemView .findViewById(R.id.iv_lostinfo_img);
            tv_name =  itemView.findViewById(R.id.tv_name);
            tv_time =  itemView.findViewById(R.id.tv_time);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}
