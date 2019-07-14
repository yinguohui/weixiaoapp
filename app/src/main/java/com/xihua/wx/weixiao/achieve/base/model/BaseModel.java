package com.xihua.wx.weixiao.achieve.base.model;

import android.content.Context;

import com.xihua.wx.weixiao.mvp.model.MvpModel;

public abstract class BaseModel implements MvpModel {

    private Context context;

    public BaseModel(Context context){
        this.context = context;
    }

    public String getServerUrl(){
        //http://yapi.demo.qunar.com/mock/11910/title
        //return  "http://api.budejie.com/api/api_open.php";
        return  "http://yapi.demo.qunar.com/mock/11910/title";

    }

}