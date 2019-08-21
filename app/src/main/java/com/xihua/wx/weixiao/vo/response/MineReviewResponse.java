package com.xihua.wx.weixiao.vo.response;


import com.xihua.wx.weixiao.bean.Topic;
import com.xihua.wx.weixiao.bean.User;

/**
 * @Classname MineReviewResponse
 * @Description TODO
 * @Date 2019/8/18 11:42
 * @Created by ygh
 */
public class MineReviewResponse {
    private Integer reviewId;
    private String reviewContent;
    private Integer reviewTopicId;
    private Integer reviewUserId;
    private Long reviewCreateTime;
    private User user;
    private Topic topic;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Integer getReviewTopicId() {
        return reviewTopicId;
    }

    public void setReviewTopicId(Integer reviewTopicId) {
        this.reviewTopicId = reviewTopicId;
    }

    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Long getReviewCreateTime() {
        return reviewCreateTime;
    }

    public void setReviewCreateTime(Long reviewCreateTime) {
        this.reviewCreateTime = reviewCreateTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}