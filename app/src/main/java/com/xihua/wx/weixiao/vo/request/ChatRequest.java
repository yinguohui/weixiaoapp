package com.xihua.wx.weixiao.vo.request;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YGH123
 * @since 2019-07-24
 */
public class ChatRequest implements Serializable {

    private Integer chatSendId;
    private Integer chatReviceId;
    private String chatContent;

    private Integer chatUserId;

    public Integer getChatSendId() {
        return chatSendId;
    }

    public void setChatSendId(Integer chatSendId) {
        this.chatSendId = chatSendId;
    }

    public Integer getChatReviceId() {
        return chatReviceId;
    }

    public void setChatReviceId(Integer chatReviceId) {
        this.chatReviceId = chatReviceId;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Integer getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Integer chatUserId) {
        this.chatUserId = chatUserId;
    }
}
