package com.xihua.wx.weixiao.vo.response;

import com.xihua.wx.weixiao.bean.User;

/**
 * @ClassName TopicResponse
 * @Auhtor ygh
 * @DATE 2019/7/31 23:16
 **/
public class TopicResponse {
    private Integer topicId;
    private String topicContent;
    private String topicImg;
    private Integer topicLike;
    private Integer topicComment;
    private Long topicCreateTime;
    private Integer topicUserId;
    public User user;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public Integer getTopicLike() {
        return topicLike;
    }

    public void setTopicLike(Integer topicLike) {
        this.topicLike = topicLike;
    }

    public Integer getTopicComment() {
        return topicComment;
    }

    public void setTopicComment(Integer topicComment) {
        this.topicComment = topicComment;
    }

    public Long getTopicCreateTime() {
        return topicCreateTime;
    }

    public void setTopicCreateTime(Long topicCreateTime) {
        this.topicCreateTime = topicCreateTime;
    }

    public Integer getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(Integer topicUserId) {
        this.topicUserId = topicUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
