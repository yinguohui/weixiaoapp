package com.xihua.wx.weixiao.bean;


public class DonationRequest {
    private String donationName;
    private String doantionPlace;
    private Long doantionTime;
    private Integer donationNum;
    private String doantionDescrption;
    private Integer donationUserId;
    private Long donationCreateTime;

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public String getDoantionPlace() {
        return doantionPlace;
    }

    public void setDoantionPlace(String doantionPlace) {
        this.doantionPlace = doantionPlace;
    }

    public Long getDoantionTime() {
        return doantionTime;
    }

    public void setDoantionTime(Long doantionTime) {
        this.doantionTime = doantionTime;
    }

    public Integer getDonationNum() {
        return donationNum;
    }

    public void setDonationNum(Integer donationNum) {
        this.donationNum = donationNum;
    }

    public String getDoantionDescrption() {
        return doantionDescrption;
    }

    public void setDoantionDescrption(String doantionDescrption) {
        this.doantionDescrption = doantionDescrption;
    }

    public Integer getDonationUserId() {
        return donationUserId;
    }

    public void setDonationUserId(Integer donationUserId) {
        this.donationUserId = donationUserId;
    }

    public Long getDonationCreateTime() {
        return donationCreateTime;
    }

    public void setDonationCreateTime(Long donationCreateTime) {
        this.donationCreateTime = donationCreateTime;
    }
}
