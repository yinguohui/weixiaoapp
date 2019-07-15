package com.xihua.wx.weixiao.bean;

import java.util.List;

public class CommentDetailBean {


    public CommentDetailBean(String userName, String topicContent, String topicCreate, String userImg) {
        this.userName = userName;
        this.topicContent = topicContent;
        this.topicCreate = topicCreate;
        this.userImg = userImg;
    }
    private String userName;
    private String userImg;

    private String topicId;
    private String topicContent;
    private String topicImg;
    private String topicCreate;
    private String topicStatus;
    private Integer topicComment;
    private List<ReplyDetailBean> replyList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getReviewId() {
        return topicId;
    }

    public void setReviewId(String topicId) {
        this.topicId = topicId;
    }

    public String getReviewContent() {
        return topicContent;
    }

    public void setReviewContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getReviewCreate() {
        return topicCreate;
    }

    public void setReviewCreate(String topicCreate) {
        this.topicCreate = topicCreate;
    }

    public String getReviewStatus() {
        return topicStatus;
    }

    public void setReviewStatus(String topicStatus) {
        this.topicStatus = topicStatus;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicCreate() {
        return topicCreate;
    }

    public void setTopicCreate(String topicCreate) {
        this.topicCreate = topicCreate;
    }

    public String getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(String topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Integer getTopicComment() {
        return topicComment;
    }

    public void setTopicComment(Integer topicComment) {
        this.topicComment = topicComment;
    }

    public List<ReplyDetailBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyDetailBean> replyList) {
        this.replyList = replyList;
    }
}