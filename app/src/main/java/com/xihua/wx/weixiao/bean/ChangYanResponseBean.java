package com.xihua.wx.weixiao.bean;

import java.util.List;

public class ChangYanResponseBean {
    private Integer code;
    private Integer total;
    private Integer page;
    private List<ChangYanResponse> list;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ChangYanResponse> getList() {
        return list;
    }

    public void setList(List<ChangYanResponse> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public class ChangYanResponse{
        private Integer changYanId;
        private String changYanUsername;
        private String changYanUserImg;
        private Long chanYanTime;
        private String changYanContent;
        private String changYanImg;
        private Integer changyanLike;
        private Integer changYanComment;

        public Integer getChangYanId() {
            return changYanId;
        }

        public void setChangYanId(Integer changYanId) {
            this.changYanId = changYanId;
        }

        public String getChangYanUsername() {
            return changYanUsername;
        }

        public void setChangYanUsername(String changYanUsername) {
            this.changYanUsername = changYanUsername;
        }

        public Long getChanYanTime() {
            return chanYanTime;
        }

        public void setChanYanTime(Long chanYanTime) {
            this.chanYanTime = chanYanTime;
        }

        public String getChangYanContent() {
            return changYanContent;
        }

        public void setChangYanContent(String changYanContent) {
            this.changYanContent = changYanContent;
        }

        public String getChangYanImg() {
            return changYanImg;
        }

        public void setChangYanImg(String changYanImg) {
            this.changYanImg = changYanImg;
        }

        public Integer getChangyanLike() {
            return changyanLike;
        }

        public void setChangyanLike(Integer changyanLike) {
            this.changyanLike = changyanLike;
        }

        public Integer getChangYanComment() {
            return changYanComment;
        }

        public void setChangYanComment(Integer changYanComment) {
            this.changYanComment = changYanComment;
        }

        public String getChangYanUserImg() {
            return changYanUserImg;
        }

        public void setChangYanUserImg(String changYanUserImg) {
            this.changYanUserImg = changYanUserImg;
        }
    }
}
