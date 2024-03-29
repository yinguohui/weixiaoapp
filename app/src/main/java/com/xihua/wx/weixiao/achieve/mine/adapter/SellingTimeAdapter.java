package com.xihua.wx.weixiao.achieve.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.main.publish.activity.ChangYanDetailActivity;
import com.xihua.wx.weixiao.achieve.main.sell.GoodsDetailActivity;
import com.xihua.wx.weixiao.achieve.main.sell.SellBuyDetailActivity;
import com.xihua.wx.weixiao.utils.DensityUtil;
import com.xihua.wx.weixiao.utils.TimeFormat;
import com.xihua.wx.weixiao.vo.response.DonationTimeLine;
import com.xihua.wx.weixiao.vo.response.GoodsTimeLine;

import java.util.List;

//适配器代码编写  最主要的就是逻辑的处理
public class SellingTimeAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<GoodsTimeLine> data;

    public SellingTimeAdapter(Context context, List<GoodsTimeLine> list) {
        this.context = context;
        this.data = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).setPosition(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDateTime;
        private TextView txt_date_title;
        private RelativeLayout rlTitle;
        private View vLine;
        private int position;
        private GoodsTimeLine timeData;

        public ViewHolder(View itemView) {
            super(itemView);
            rlTitle =  itemView.findViewById(R.id.rl_title);
            vLine = itemView.findViewById(R.id.v_line);
            txtDateTime =  itemView.findViewById(R.id.txt_date_time);
            txt_date_title =  itemView.findViewById(R.id.txt_date_title);
        }

        public void setPosition(int position) {
            this.position = position;
            timeData = data.get(position);
            //时间轴竖线的layoutParams,用来动态的添加竖线
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vLine.getLayoutParams();
            //position等于0的处理
            if (position == 0) {
                //数据个数必须大于等于2，要不对于时间的判断会有越界危险
                if (data.size() >= 2) {
                    if (!timeData.getGoodsCreateTime().equals(data.get(position + 1).getGoodsCreateTime())) {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                        layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 15), 0, 0);
                    } else {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgtop);
                        layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 15), 0, 0);
                    }
                } else {
                    txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                    //动态的代码设置距离上面的边距，只有第一条才有上边距
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 15), 0, 0);
                }
                rlTitle.setVisibility(View.VISIBLE);
                txtDateTime.setText(TimeFormat.format("yyyy-MM-dd hh:mm:ss", timeData.getGoodsCreateTime()));
                layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
            } else if (position < data.size() - 1) {

                if (timeData.getGoodsCreateTime().equals(data.get(position - 1).getGoodsCreateTime())) {
                    if (timeData.getGoodsCreateTime().equals(data.get(position + 1).getGoodsCreateTime())) {
                        rlTitle.setVisibility(View.GONE);
                        layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgcenter);
                        layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_title);
                        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                    } else {
                        rlTitle.setVisibility(View.GONE);
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgbot);
                        layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_title);
                        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                    }

                } else {
                    if (!timeData.getGoodsCreateTime().equals(data.get(position + 1).getGoodsCreateTime())) {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                    } else {
                        txt_date_title.setBackgroundResource(R.drawable.message_sys_bgtop);
                    }
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                    rlTitle.setVisibility(View.VISIBLE);
                    txtDateTime.setText(TimeFormat.format("yyyy-MM-dd hh:mm:ss", timeData.getGoodsCreateTime()));
                    layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                }

            } else {
                if (!timeData.getGoodsCreateTime().equals(data.get(position - 1).getGoodsCreateTime())) {
                    txt_date_title.setBackgroundResource(R.drawable.message_sys_bgfirst);
                    rlTitle.setVisibility(View.VISIBLE);
                    txtDateTime.setText(TimeFormat.format("yyyy-MM-dd hh:mm:ss", timeData.getGoodsCreateTime()));
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                    layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.rl_title);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                } else {
                    rlTitle.setVisibility(View.GONE);
                    txt_date_title.setBackgroundResource(R.drawable.message_sys_bgbot);
                    txtDateTime.setText(TimeFormat.format("yyyy-MM-dd hh:mm:ss", timeData.getGoodsCreateTime()));
                    layoutParams.setMargins(DensityUtil.dip2px(vLine.getContext(), 20), DensityUtil.dip2px(vLine.getContext(), 0), 0, 0);
                    layoutParams.addRule(RelativeLayout.ALIGN_TOP, R.id.txt_date_title);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.txt_date_title);
                }

            }
            vLine.setLayoutParams(layoutParams);
            txt_date_title.setText(timeData.getGoodsName());
            rlTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,SellBuyDetailActivity.class);
                    intent.putExtra("goods_id",timeData.getGoodsId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
