package com.xihua.wx.weixiao.vo.response;

import com.xihua.wx.weixiao.bean.User;

/**
 * @Classname ChatAllResponse
 * @Description TODO
 * @Date 2019/8/19 13:49
 * @Created by ygh
 */
public class ChatAllResponse {
    private Integer chatId;
    private Integer chatSendId;
    private String chatContent;
    private Integer chatStatus;
    private Long chatCreateTime;
    private User user;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getChatSendId() {
        return chatSendId;
    }

    public void setChatSendId(Integer chatSendId) {
        this.chatSendId = chatSendId;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Integer getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
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
