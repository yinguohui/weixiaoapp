package com.xihua.wx.weixiao.achieve.mine;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.achieve.base.view.BaseFragment;
import com.xihua.wx.weixiao.achieve.login.activity.LoginActivity;
import com.xihua.wx.weixiao.achieve.main.publish.activity.BelongMeActivity;
import com.xihua.wx.weixiao.achieve.mine.activity.DonationTiemLineActivity;
import com.xihua.wx.weixiao.achieve.mine.activity.LostingTiemLineActivity;
import com.xihua.wx.weixiao.achieve.mine.activity.SellingTiemLineActivity;
import com.xihua.wx.weixiao.achieve.mine.activity.SettingActivity;
import com.xihua.wx.weixiao.achieve.mine.activity.SuggestionTiemLineActivity;
import com.xihua.wx.weixiao.bean.ApiResult;
import com.xihua.wx.weixiao.bean.IdRequest;
import com.xihua.wx.weixiao.utils.MapUtil;
import com.xihua.wx.weixiao.utils.OkHttpUtil;
import com.xihua.wx.weixiao.utils.SpUtil;
import com.xihua.wx.weixiao.utils.ToastUtil;
import com.xihua.wx.weixiao.utils.VolleyUtils;
import com.xihua.wx.weixiao.utils.image.CircleNetworkImageImage;
import com.xihua.wx.weixiao.vo.response.UserResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    RelativeLayout rl_selloutgoods, rl_topic, rl_donationlists, rl_getinfo, rl_lostfound, rl_setting, rl_exit, rl_aboutus;
    TextView tv_user;
    CircleNetworkImageImage iv_user;
    UserResponse response;
    Gson gson = new Gson();
    Logger logger = Logger.getLogger("MineFragment");

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    loadindfale();
                    ToastUtil.showToast(getContext(), "网络错误");
                    break;
                case 1:
                    SpUtil.putString(getContext(), "status", response.getUserStatus() + "");
                    VolleyUtils.loadImage(getContext(), iv_user, response.getUserImg());
                    tv_user.setText(response.getUserName());
                    break;
            }
        }
    };

    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initContentView(View viewContent) {
        init(viewContent);
    }

    private void init(View view) {
        //得到控件
        tv_user = view.findViewById(R.id.tv_user);
        iv_user = view.findViewById(R.id.iv_user);
        rl_selloutgoods = view.findViewById(R.id.rl_selloutgoods);
        rl_topic = view.findViewById(R.id.rl_topic);
        rl_donationlists = view.findViewById(R.id.rl_donationlists);
        rl_getinfo = view.findViewById(R.id.rl_getinfo);
        rl_lostfound = view.findViewById(R.id.rl_lostfound);
        rl_setting = view.findViewById(R.id.rl_setting);
        rl_exit = view.findViewById(R.id.rl_exit);
        rl_aboutus = view.findViewById(R.id.rl_aboutus);

        //设置控件事件
        rl_selloutgoods.setOnClickListener(this);
        rl_topic.setOnClickListener(this);
        rl_donationlists.setOnClickListener(this);
        rl_getinfo.setOnClickListener(this);
        rl_lostfound.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        rl_exit.setOnClickListener(this);
        rl_aboutus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (check()) {
            switch (v.getId()) {
                case R.id.tv_user:
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    break;
                case R.id.rl_selloutgoods:
                    startActivity(new Intent(getActivity(), SellingTiemLineActivity.class));
                    break;
                case R.id.rl_topic:
                    startActivity(new Intent(getActivity(), BelongMeActivity.class));
                    break;
                case R.id.rl_donationlists:
                    startActivity(new Intent(getActivity(), DonationTiemLineActivity.class));
                    break;
                case R.id.rl_getinfo:
                    startActivity(new Intent(getActivity(), SuggestionTiemLineActivity.class));
                    break;
                case R.id.rl_lostfound:
                    startActivity(new Intent(getActivity(), LostingTiemLineActivity.class));
                    break;
                case R.id.rl_setting:
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    break;
            }
        }
        switch (v.getId()) {
            case R.id.tv_user:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.rl_exit:
                EMClient.getInstance().logout(true);
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.rl_aboutus:
                //startActivity(new Intent(getActivity(), DiscountTypeActivty.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String id = SpUtil.getString(getContext(), "userid", "-1");
        if ("-1".equals(id)) {
            tv_user.setText("请登录登录。。。");
            tv_user.setOnClickListener(this);
            return;
        } else {
            loadind();
            tv_user.setClickable(false);
        }
    }

    private void loadind() {
        String id = SpUtil.getString(getContext(), "userid", "");
        IdRequest idRequest = new IdRequest();
        idRequest.setId(Integer.parseInt(id));
        OkHttpUtil.doPost("http://192.168.43.240:8080/user/getuserinfobyid", gson.toJson(idRequest), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.log(Level.OFF, "加载个人信息", e);
                handler.sendEmptyMessage(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    fromdata(response.body().string());
                } else {
                    handler.sendEmptyMessage(-1);
                }
            }
        });
    }

    private void loadindfale() {
        ToastUtil.showToast(getContext(), "信息加载失败，退出重新登陆试试");
        tv_user.setText("请登录登录。。。");
        tv_user.setClickable(true);
        tv_user.setOnClickListener(this);

    }

    private void fromdata(String data) {
        ApiResult<UserResponse> apiResult = gson.fromJson(data, ApiResult.class);
        response = gson.fromJson(gson.toJson(apiResult.getData()), UserResponse.class);
        if (apiResult.getCode() == 200) {
            handler.sendEmptyMessage(1);
        } else {
            handler.sendEmptyMessage(-1);
        }
    }

    private boolean check() {
        String id = SpUtil.getString(getContext(), "userid", "-1");
        if ("-1".equals(id)) {
            ToastUtil.showToast(getContext(), "请登录");
            return false;
        }
        return true;
    }
}
