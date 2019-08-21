package com.xihua.wx.weixiao.vo.request;

public class ReviewRequest {
    private String reviewContent;
    private Integer reviewTopicId;
    private Integer reviewUserId;

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
}
