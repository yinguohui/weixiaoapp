package com.xihua.wx.weixiao.achieve.login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xihua.wx.weixiao.R;
import com.xihua.wx.weixiao.utils.TextUtils;

public class PrivacyPolicyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        init();
    }
    private void init(){
        text = findViewById(R.id.text);
        String s = TextUtils.autoSplitText(text);
        text.setText(s);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
