package com.xihua.wx.weixiao.vo.response;

import com.xihua.wx.weixiao.bean.User;

/**
 * @Classname ChatAllResponse
 * @Description TODO
 * @Date 2019/8/19 13:49
 * @Created by ygh
 */
public class ChatAllUserResponse {
    private String chatContent;
    private Long chatCreateTime;
    private User user;

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }


    public Long getChatCreateTime() {
        return chatCreateTime;
    }

    public void setChatCreateTime(Long chatCreateTime) {
        this.chatCreateTime = chatCreateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
