package com.xihua.wx.weixiao.vo.response;


import com.xihua.wx.weixiao.bean.User;

public class ReviewResponse {
    private Integer reviewId;
    private String reviewContent;
    private Integer reviewTopicId;
    private Integer reviewUserId;
    private Long reviewCreateTime;
    private User user;

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
}
