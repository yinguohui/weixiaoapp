package com.xihua.wx.weixiao.achieve.mine.adapter;

import com.xihua.wx.weixiao.vo.response.DonationTimeLine;
import com.xihua.wx.weixiao.vo.response.TopicTimeLine;

import java.util.Comparator;

/**
 * 对时间进行排序，在实际开发中最好要后台给按时间顺序排序好的数据
 */

public class DonationComparator implements Comparator<DonationTimeLine> {
    @Override
    public int compare(DonationTimeLine td1, DonationTimeLine td2) {
        return td2.getDonationCreateTime().compareTo(td1.getDonationCreateTime());
    }
}
