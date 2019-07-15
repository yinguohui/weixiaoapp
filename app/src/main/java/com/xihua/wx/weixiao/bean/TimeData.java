package com.xihua.wx.weixiao.bean;

public class TimeData {

    public String posttime;
    public String title;

    public TimeData(String posttime,String title) {
        this.title = title;
        this.posttime = posttime;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

