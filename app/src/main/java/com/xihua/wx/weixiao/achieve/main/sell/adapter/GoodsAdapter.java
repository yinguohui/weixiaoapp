package com.xihua.wx.weixiao.achieve.main.sell.adapter;

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
import com.xihua.wx.weixiao.vo.response.GoodsResponse;

import java.util.List;

public class GoodsAdapter extends XRecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<GoodsResponse> list;


    public GoodsAdapter(List<GoodsResponse> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_goods,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            GoodsResponse bean = list.get(position);
            String[] imgs = bean.getGoodsImg().split("$%");
            if (imgs.length==1){
                Glide.with(context).load(imgs[0]).into(((ViewHolder) holder).iv_goods_img);
            }
            ((ViewHolder) holder).tv_name.setText(bean.getGoodsName());
            ((ViewHolder) holder).tv_price.setText(bean.getGoodsPrice()+"");
            ((ViewHolder) holder).tv_space.setText(bean.getGoodsPlace());


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

        public ImageView iv_goods_img;
        public TextView tv_name;
        public TextView tv_space;
        public TextView tv_price;

        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_goods_img = itemView .findViewById(R.id.iv_goods_img);
            tv_name =  itemView.findViewById(R.id.tv_name);
            tv_price =  itemView.findViewById(R.id.tv_price);
            tv_space = itemView.findViewById(R.id.tv_space);
        }
    }
}
