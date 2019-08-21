package com.xihua.wx.weixiao.query;

public class LostInfoQuery extends ObjectQuery{
    private Integer lostinfoUserId;

    private Integer lostType;

    private String key;

    public Integer getLostinfoUserId() {
        return lostinfoUserId;
    }

    public void setLostinfoUserId(Integer lostinfoUserId) {
        this.lostinfoUserId = lostinfoUserId;
    }

    public Integer getLostType() {
        return lostType;
    }

    public void setLostType(Integer lostType) {
        this.lostType = lostType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
