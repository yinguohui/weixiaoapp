package com.xihua.wx.weixiao.achieve.main;

import android.content.Intent;

import android.view.View;
import android.widget.LinearLayout;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.base.view.BaseFragment;
import com.xihua.wx.weixiao.achieve.main.donation.activity.DonationActivity;
import com.xihua.wx.weixiao.achieve.main.info.activity.InfoActivity;
import com.xihua.wx.weixiao.achieve.main.lost.activity.LostActivity;
import com.xihua.wx.weixiao.achieve.main.publish.activity.PublishActivity;
import com.xihua.wx.weixiao.achieve.main.sell.SellActivity;

public class MainFragment extends BaseFragment implements View.OnClickListener {
    LinearLayout ll_sell,ll_publish,ll_donation,ll_lost,ll_info;


    @Override
    public int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    public void initContentView(View viewContent) {
        ll_sell = viewContent.findViewById(R.id.ll_sell);
        ll_publish = viewContent.findViewById(R.id.ll_publish);
        ll_donation = viewContent.findViewById(R.id.ll_donation);
        ll_lost = viewContent.findViewById(R.id.ll_lost);
        ll_info = viewContent.findViewById(R.id.ll_info);

        ll_sell.setOnClickListener(this);
        ll_publish.setOnClickListener(this);
        ll_donation.setOnClickListener(this);
        ll_lost.setOnClickListener(this);
        ll_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_sell:
                startActivity(new Intent(getActivity(),SellActivity.class));
                break;
            case R.id.ll_publish:
                startActivity(new Intent(getActivity(),PublishActivity.class));
                break;
            case R.id.ll_donation:
                startActivity(new Intent(getActivity(),DonationActivity.class));
                break;
            case R.id.ll_lost:
                startActivity(new Intent(getActivity(),LostActivity.class));
                break;
            case R.id.ll_info:
                startActivity(new Intent(getActivity(),InfoActivity.class));
                break;
        }
    }
}
