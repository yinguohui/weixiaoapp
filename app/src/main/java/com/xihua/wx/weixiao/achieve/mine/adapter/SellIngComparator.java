package com.xihua.wx.weixiao.achieve.mine.adapter;

import com.xihua.wx.weixiao.vo.response.DonationTimeLine;
import com.xihua.wx.weixiao.vo.response.GoodsTimeLine;

import java.util.Comparator;

/**
 * 对时间进行排序，在实际开发中最好要后台给按时间顺序排序好的数据
 */

public class SellIngComparator implements Comparator<GoodsTimeLine> {
    @Override
    public int compare(GoodsTimeLine td1, GoodsTimeLine td2) {
        return td2.getGoodsCreateTime().compareTo(td1.getGoodsCreateTime());
    }
}
