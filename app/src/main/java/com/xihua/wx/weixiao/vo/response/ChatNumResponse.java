package com.xihua.wx.weixiao.vo.response;

import com.xihua.wx.weixiao.bean.User;

public class ChatNumResponse {
    private User user;
    private int num;
    private Integer chatUserId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Integer chatUserId) {
        this.chatUserId = chatUserId;
    }
}
