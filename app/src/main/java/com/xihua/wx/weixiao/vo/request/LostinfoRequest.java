package com.xihua.wx.weixiao.vo.request;

public class LostinfoRequest {
    private String lostinfoName;
    private String lostinfoDescription;
    private String lostinfoPlace;
    private Integer lostinfoType;
    private Integer lostinfoUserId;

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

    public String getLostinfoPlace() {
        return lostinfoPlace;
    }

    public void setLostinfoPlace(String lostinfoPlace) {
        this.lostinfoPlace = lostinfoPlace;
    }

    public Integer getLostinfoType() {
        return lostinfoType;
    }

    public void setLostinfoType(Integer lostinfoType) {
        this.lostinfoType = lostinfoType;
    }

    public Integer getLostinfoUserId() {
        return lostinfoUserId;
    }

    public void setLostinfoUserId(Integer lostinfoUserId) {
        this.lostinfoUserId = lostinfoUserId;
    }
}
