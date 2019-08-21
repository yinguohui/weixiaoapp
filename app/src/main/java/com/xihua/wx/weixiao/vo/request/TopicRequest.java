package com.xihua.wx.weixiao.vo.request;

/**
 * @ClassName TopicRequest
 * @Auhtor ygh
 * @DATE 2019/7/31 22:14
 **/
public class TopicRequest {
    private String topicContent;
    private Integer topicUserId;

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Integer getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(Integer topicUserId) {
        this.topicUserId = topicUserId;
    }
}
