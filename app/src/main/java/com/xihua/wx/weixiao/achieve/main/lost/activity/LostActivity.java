package com.xihua.wx.weixiao.achieve.main.lost.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xihua.wx.weixiao.R;

public class LostActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back,lostlists;
    LinearLayout ll_lost1,ll_lost2,ll_lost_query,ll_found_query;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        init();
    }
    private void init(){
        iv_back = findViewById(R.id.iv_back);
        ll_lost1 = findViewById(R.id.ll_lost1);
        ll_lost2 = findViewById(R.id.ll_lost2);
        lostlists = findViewById(R.id.lostlists);
        ll_found_query = findViewById(R.id.ll_found_query);
        ll_lost_query = findViewById(R.id.ll_lost_query);

        lostlists.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_lost1.setOnClickListener(this);
        ll_lost2.setOnClickListener(this);
        ll_found_query.setOnClickListener(this);
        ll_lost_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_lost1:
                startActivity(new Intent(LostActivity.this,LostPublishActivity.class));
                break;
            case R.id.ll_lost2:
                startActivity(new Intent(LostActivity.this,FoundPublishActivity.class));
                break;
            case R.id.lostlists:
                startActivity(new Intent(LostActivity.this,LostInfoActivity.class));
                break;
            case R.id.ll_found_query:
                startActivity(new Intent(LostActivity.this,FoundQueryActivity.class));
                break;
            case R.id.ll_lost_query:
                startActivity(new Intent(LostActivity.this,LostQueryActivity.class));
                break;
        }
    }
}
