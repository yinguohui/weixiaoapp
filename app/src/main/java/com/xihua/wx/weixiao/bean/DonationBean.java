package com.xihua.wx.weixiao.bean;

import java.util.Date;

/**
 *  donation
 * @author ygh 2019-07-06
 */
public class DonationBean{

    /**
     * donation_id
     */
    private Integer donationId;

    /**
     * donation_no
     */
    private String donationNo;

    /**
     * 捐赠名称
     */
    private String donationName;

    /**
     * 捐赠数量
     */
    private Integer donationNum;

    /**
     * 捐赠地点
     */
    private Integer donationPlace;

    /**
     * 捐赠上门收取时间
     */
    private Date donationCollectionTime;

    /**
     * 捐赠描述
     */
    private String donationDescription;

    /**
     * 捐赠图片
     */
    private String donationImgs;

    /**
     * 捐赠创建时间
     */
    private Date donationCreateTime;

    /**
     * 捐赠人信息
     */
    private String donationUserNo;

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public String getDonationNo() {
        return donationNo;
    }

    public void setDonationNo(String donationNo) {
        this.donationNo = donationNo;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public Integer getDonationNum() {
        return donationNum;
    }

    public void setDonationNum(Integer donationNum) {
        this.donationNum = donationNum;
    }

    public Integer getDonationPlace() {
        return donationPlace;
    }

    public void setDonationPlace(Integer donationPlace) {
        this.donationPlace = donationPlace;
    }

    public Date getDonationCollectionTime() {
        return donationCollectionTime;
    }

    public void setDonationCollectionTime(Date donationCollectionTime) {
        this.donationCollectionTime = donationCollectionTime;
    }

    public String getDonationDescription() {
        return donationDescription;
    }

    public void setDonationDescription(String donationDescription) {
        this.donationDescription = donationDescription;
    }

    public String getDonationImgs() {
        return donationImgs;
    }

    public void setDonationImgs(String donationImgs) {
        this.donationImgs = donationImgs;
    }

    public Date getDonationCreateTime() {
        return donationCreateTime;
    }

    public void setDonationCreateTime(Date donationCreateTime) {
        this.donationCreateTime = donationCreateTime;
    }

    public String getDonationUserNo() {
        return donationUserNo;
    }

    public void setDonationUserNo(String donationUserNo) {
        this.donationUserNo = donationUserNo;
    }
}