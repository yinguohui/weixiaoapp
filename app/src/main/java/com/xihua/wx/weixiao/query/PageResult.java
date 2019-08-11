package com.xihua.wx.weixiao.query;

import java.io.Serializable;
import java.util.List;

/**
 * @auther: zhangbing
 * @date: 2018/11/21 13:32
 * @description:
 */
public class PageResult<T> implements Serializable, Cloneable {

    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 数据集
     */
    private List<T> items;

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

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
