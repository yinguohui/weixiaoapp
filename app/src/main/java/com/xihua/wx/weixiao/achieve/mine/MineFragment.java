package com.xihua.wx.weixiao.achieve.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.base.view.BaseFragment;
import com.xihua.wx.weixiao.achieve.mine.activity.LoginActivity;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;

public class MineFragment extends BaseFragment implements View.OnClickListener{
    RelativeLayout rl_selloutgoods,rl_sellinggoods,rl_donationlists,rl_getinfo,rl_lostfound,rl_setting,rl_exit,rl_aboutus;
    CircleNetworkImageImage iv_user;

    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initContentView(View viewContent) {

    }

    private void init(View view){
//得到控件
        iv_user =  view.findViewById(R.id.iv_user);
        rl_selloutgoods =  view.findViewById(R.id.rl_selloutgoods);
        rl_sellinggoods =  view.findViewById(R.id.rl_sellinggoods);
        rl_donationlists =  view.findViewById(R.id.rl_donationlists);
        rl_getinfo =  view.findViewById(R.id.rl_getinfo);
        rl_lostfound =  view.findViewById(R.id.rl_lostfound);
        rl_setting =  view.findViewById(R.id.rl_setting);
        rl_exit = view.findViewById(R.id.rl_exit);
        rl_aboutus = view.findViewById(R.id.rl_aboutus);

        //设置控件事件

        rl_selloutgoods.setOnClickListener(this);
        rl_sellinggoods.setOnClickListener(this);
        rl_donationlists.setOnClickListener(this);
        rl_getinfo.setOnClickListener(this);
        rl_lostfound.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        rl_exit.setOnClickListener(this);
        rl_aboutus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_selloutgoods:
                //startActivity(new Intent(getActivity(), TitleActivity.class));
                break;
            case R.id.rl_sellinggoods:
                //startActivity(new Intent(getActivity(), MyOrderInfoActivity.class));
                break;
            case R.id.rl_donationlists:
               // startActivity(new Intent(getActivity(), MyMovieTicketActivity.class));
                break;
            case R.id.rl_getinfo:
               // startActivity(new Intent(getActivity(), ScoreActivity.class));
                break;
            case R.id.rl_lostfound:
               // startActivity(new Intent(getActivity(), SellTicketsActivity.class));
                break;
            case R.id.rl_setting:
                //startActivity(new Intent(getActivity(), MallActivity.class));
                break;
            case R.id.rl_exit:
                startActivity(new Intent(getContext(),LoginActivity.class));
                break;
            case R.id.rl_aboutus:
                //startActivity(new Intent(getActivity(), DiscountTypeActivty.class));
                break;
        }
    }
    
}
