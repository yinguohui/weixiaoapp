package com.xihua.wx.weixiao.achieve.main.publish.adapter;

import com.xihua.wx.weixiao.bean.TimeData;

import java.util.Comparator;

/**
 * 对时间进行排序，在实际开发中最好要后台给按时间顺序排序好的数据
 */

public class TimeComparator implements Comparator<TimeData> {
    @Override
    public int compare(TimeData td1, TimeData td2) {
        return td2.getPosttime().compareTo(td1.getPosttime());
    }
}
