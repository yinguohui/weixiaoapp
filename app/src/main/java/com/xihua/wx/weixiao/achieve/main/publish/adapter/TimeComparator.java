package com.xihua.wx.weixiao.achieve.main.publish.adapter;

import com.xihua.wx.weixiao.bean.TimeData;
import com.xihua.wx.weixiao.vo.response.TopicTimeLine;

import java.util.Comparator;

/**
 * 对时间进行排序，在实际开发中最好要后台给按时间顺序排序好的数据
 */

public class TimeComparator implements Comparator<TopicTimeLine> {
    @Override
    public int compare(TopicTimeLine td1, TopicTimeLine td2) {
        return td2.getTopicCreateTime().compareTo(td1.getTopicCreateTime());
    }
}
