package com.xihua.wx.weixiao.bean;

public class GoodsRequestBean {
    private String goodsName;
    private Double goodsPrice;
    private String goodsDesciption;
    private Integer goodsUserNo;
    private String goodsPlace;
    private Integer goodsType;

    public Integer getGoodsUserNo() {
        return goodsUserNo;
    }

    public void setGoodsUserNo(Integer goodsUserNo) {
        this.goodsUserNo = goodsUserNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getGoodsDesciption() {
        return goodsDesciption;
    }

    public void setGoodsDesciption(String goodsDesciption) {
        this.goodsDesciption = goodsDesciption;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
