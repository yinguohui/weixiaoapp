package com.xihua.wx.weixiao.bean;

import java.util.List;

public class LostinfoList {
    private Integer code;
    private Integer page;
    private Integer total;
    private List<LostinfoBean> list;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<LostinfoBean> getList() {
        return list;
    }

    public void setList(List<LostinfoBean> list) {
        this.list = list;
    }

    public class LostinfoBean{
        private Integer lostinfoId;
        private String lostinfoName;
        private String lostinfoDescription;
        private String lostinfoImg;
        private Long lostinfoTime;

        public Long getLostinfoTime() {
            return lostinfoTime;
        }

        public void setLostinfoTime(Long lostinfoTime) {
            this.lostinfoTime = lostinfoTime;
        }

        public Integer getLostinfoId() {
            return lostinfoId;
        }

        public void setLostinfoId(Integer lostinfoId) {
            this.lostinfoId = lostinfoId;
        }

        public String getLostinfoName() {
            return lostinfoName;
        }

        public void setLostinfoName(String lostinfoName) {
            this.lostinfoName = lostinfoName;
        }

        public String getLostinfoDescription() {
            return lostinfoDescription;
        }

        public void setLostinfoDescription(String lostinfoDescription) {
            this.lostinfoDescription = lostinfoDescription;
        }

        public String getLostinfoImg() {
            return lostinfoImg;
        }

        public void setLostinfoImg(String lostinfoImg) {
            this.lostinfoImg = lostinfoImg;
        }
    }
}
