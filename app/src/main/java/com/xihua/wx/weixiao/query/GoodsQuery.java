package com.xihua.wx.weixiao.query;

public class GoodsQuery{

    /** 每页显示记录数 */
    private int pageSize = 10;

    /** 当前页页码 */
    private int currentPage = 1;

    /** 当前页页码 */
    private Integer pageOffset ;

    private int goodsUserId;

    public int getGoodsUserId() {
        return goodsUserId;
    }

    public void setGoodsUserId(int goodsUserId) {
        this.goodsUserId = goodsUserId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }
}
