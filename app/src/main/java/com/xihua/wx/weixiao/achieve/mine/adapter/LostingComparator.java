package com.xihua.wx.weixiao.achieve.mine.adapter;

import com.xihua.wx.weixiao.vo.response.DonationTimeLine;
import com.xihua.wx.weixiao.vo.response.LostFoundTimeLine;

import java.util.Comparator;

/**
 * 对时间进行排序，在实际开发中最好要后台给按时间顺序排序好的数据
 */

public class LostingComparator implements Comparator<LostFoundTimeLine> {
    @Override
    public int compare(LostFoundTimeLine td1, LostFoundTimeLine td2) {
        return td2.getLostinfoCreateTime().compareTo(td1.getLostinfoCreateTime());
    }
}
