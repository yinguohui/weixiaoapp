package com.xihua.wx.weixiao.vo.response;

import com.xihua.wx.weixiao.bean.User;

/**
 * @ClassName GoodsResponse
 * @Auhtor ygh
 * @DATE 2019/8/4 18:38
 **/
public class GoodsDetailResponse {
    private Integer goodsId;
    private String goodsName;
    private String goodsPlace;
    private String goodsDesciption;
    private Integer goodsType;
    private Double goodsPrice;
    private String goodsImg;
    private Long goodsCreateTime;
    private User user;

    public Long getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(Long goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String gooodsImg) {
        this.goodsImg = gooodsImg;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
