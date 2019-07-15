package com.xihua.wx.weixiao.bean;

public class ReplyDetailBean {

    private Integer reviewId;
    private String reviewContent;
    private Integer reviewStatus;
    private Long reviewCreateTime;
    private String reviewUserName;
    private String reviewUserImg;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public ReplyDetailBean(String reviewUserName, String reviewContent) {
        this.reviewUserName = reviewUserName;
        this.reviewContent = reviewContent;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Long getReviewCreateTime() {
        return reviewCreateTime;
    }

    public void setReviewCreateTime(Long reviewCreateTime) {
        this.reviewCreateTime = reviewCreateTime;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewUserImg() {
        return reviewUserImg;
    }

    public void setReviewUserImg(String reviewUserImg) {
        this.reviewUserImg = reviewUserImg;
    }
}