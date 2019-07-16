package com.xihua.wx.weixiao.bean;

import java.util.List;

public class LikeMessageList {
    private Integer code;
    private Integer page;
    private List<LikeMessageBean> list;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<LikeMessageBean> getList() {
        return list;
    }

    public void setList(List<LikeMessageBean> list) {
        this.list = list;
    }

    public class LikeMessageBean{
        private Integer likeDetailId;
        private Integer userId;
        private String userName;
        private String userImg;
        private Long createTime;
        private Integer topicId;
        private String topicImg;
        private String topicContent;

        public Integer getLikeDetailId() {
            return likeDetailId;
        }

        public void setLikeDetailId(Integer likeDetailId) {
            this.likeDetailId = likeDetailId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

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

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
            this.topicId = topicId;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicContent() {
            return topicContent;
        }

        public void setTopicContent(String topicContent) {
            this.topicContent = topicContent;
        }
    }
}
