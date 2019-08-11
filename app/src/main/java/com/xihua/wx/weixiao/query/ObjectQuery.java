package com.xihua.wx.weixiao.query;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 批量查询条件，有其他参数集成此类即可 @Author: zhangbing
 * @date: 2018/10/30 14:05
 */
public class ObjectQuery {
  /** 每页显示记录数 */
  public int pageSize = 10;

  /** 当前页页码 */
  public int currentPage = 1;

  /** 当前页页码 */
  public Integer pageOffset ;

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
