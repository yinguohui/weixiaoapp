package com.xihua.wx.weixiao.vo.response;


import com.xihua.wx.weixiao.bean.User;

public class DonationDetailResponse {
    private Integer donationId;
    private String donationName;
    private Integer doantionPlace;
    private Long doantionTime;
    private Integer donationNum;
    private String doantionDescrption;
    private Long donationCreateTime;
    private String donationImg;
    private User user;

    public String getDonationImg() {
        return donationImg;
    }

    public void setDonationImg(String donationImg) {
        this.donationImg = donationImg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public Integer getDoantionPlace() {
        return doantionPlace;
    }

    public void setDoantionPlace(Integer doantionPlace) {
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

    public Long getDonationCreateTime() {
        return donationCreateTime;
    }

    public void setDonationCreateTime(Long donationCreateTime) {
        this.donationCreateTime = donationCreateTime;
    }
}
