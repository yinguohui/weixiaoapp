package com.xihua.wx.weixiao.vo.response;

public class TopicTimeLine {
    private Integer topicId;
    private String topicContent;
    private Long topicCreateTime;

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

    public Long getTopicCreateTime() {
        return topicCreateTime;
    }

    public void setTopicCreateTime(Long topicCreateTime) {
        this.topicCreateTime = topicCreateTime;
    }
}
