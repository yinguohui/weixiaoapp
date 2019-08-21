package com.xihua.wx.weixiao.achieve.mine.adapter;

import com.xihua.wx.weixiao.vo.response.DonationTimeLine;
import com.xihua.wx.weixiao.vo.response.SuggestionTimeLine;

import java.util.Comparator;

/**
 * 对时间进行排序，在实际开发中最好要后台给按时间顺序排序好的数据
 */

public class SuggestionComparator implements Comparator<SuggestionTimeLine> {
    @Override
    public int compare(SuggestionTimeLine td1, SuggestionTimeLine td2) {
        return td2.getSuggestionCreateTime().compareTo(td1.getSuggestionCreateTime());
    }
}
