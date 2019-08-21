package com.xihua.wx.weixiao.bean;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YGH123
 * @since 2019-07-24
 */
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer reviewId;
    private String reviewNo;
    private String reviewContent;
    private Integer reviewTopicId;
    private Integer reviewUserId;
    private Long reviewCreateTime;
    private Integer reviewStatus;


    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
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

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Override
    public String toString() {
        return "Review{" +
        ", reviewId=" + reviewId +
        ", reviewNo=" + reviewNo +
        ", reviewContent=" + reviewContent +
        ", reviewTopicId=" + reviewTopicId +
        ", reviewUserId=" + reviewUserId +
        ", reviewCreateTime=" + reviewCreateTime +
        ", reviewStatus=" + reviewStatus +
        "}";
    }
}
