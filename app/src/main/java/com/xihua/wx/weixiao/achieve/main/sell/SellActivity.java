package com.xihua.wx.weixiao.achieve.main.sell;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xihua.wx.weixiao.R;

public class SellActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView iv_back;
    LinearLayout ll_sell_sell,ll_sell_buy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        init();
    }
    private void init(){
        ll_sell_sell = findViewById(R.id.ll_sell_sell);
        ll_sell_buy = findViewById(R.id.ll_sell_buy);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);
        ll_sell_sell.setOnClickListener(this);
        ll_sell_buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_sell_sell:
                startActivity(new Intent(SellActivity.this,SellSellActivity.class));
                break;
            case R.id.ll_sell_buy:
                startActivity(new Intent(SellActivity.this,SellBuyActivity.class));
                break;
        }
    }
}
