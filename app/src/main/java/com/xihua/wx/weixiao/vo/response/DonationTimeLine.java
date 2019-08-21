package com.xihua.wx.weixiao.vo.response;


public class DonationTimeLine {
    private int donationId;
    private String donationName;
    private Long donationCreateTime;

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public Long getDonationCreateTime() {
        return donationCreateTime;
    }

    public void setDonationCreateTime(Long donationCreateTime) {
        this.donationCreateTime = donationCreateTime;
    }
}
