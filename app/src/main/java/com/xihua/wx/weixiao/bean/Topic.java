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
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer topicId;
    private String topicNo;
    private String topicContent;
    private String topicImg;
    private Integer topicLike;
    private Integer topicComment;
    private Long topicCreateTime;
    private Integer topicStatus;
    private Integer topicUserId;


    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicNo() {
        return topicNo;
    }

    public void setTopicNo(String topicNo) {
        this.topicNo = topicNo;
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

    public Integer getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(Integer topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Integer getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(Integer topicUserId) {
        this.topicUserId = topicUserId;
    }

    @Override
    public String toString() {
        return "Topic{" +
        ", topicId=" + topicId +
        ", topicNo=" + topicNo +
        ", topicContent=" + topicContent +
        ", topicImg=" + topicImg +
        ", topicLike=" + topicLike +
        ", topicComment=" + topicComment +
        ", topicCreateTime=" + topicCreateTime +
        ", topicStatus=" + topicStatus +
        ", topicUserId=" + topicUserId +
        "}";
    }
}
