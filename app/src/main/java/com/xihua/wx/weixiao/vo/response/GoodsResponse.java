package com.xihua.wx.weixiao.vo.response;

/**
 * @ClassName GoodsResponse
 * @Auhtor ygh
 * @DATE 2019/8/4 18:38
 **/
public class GoodsResponse{
    private Integer goodsId;
    private String goodsName;
    private String goodsPlace;
    private String goodsDesciption;
    private Integer goodsType;
    private Double goodsPrice;
    private String goodsImg;

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
}
