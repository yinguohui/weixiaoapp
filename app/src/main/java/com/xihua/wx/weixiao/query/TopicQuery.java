package com.xihua.wx.weixiao.query;

public class TopicQuery {
    private Integer topicUserId;

    /** 每页显示记录数 */
    public int pageSize;

    /** 当前页页码 */
    public int currentPage;

    /** 当前页页码 */
    public Integer pageOffset ;

    public Integer getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(Integer topicUserId) {
        this.topicUserId = topicUserId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }
}
