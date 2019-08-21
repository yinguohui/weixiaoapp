package com.xihua.wx.weixiao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期格式化  20170606  -> 2017.06.06
public class TimeFormat {
    public static String format(String format, Long time) {
        String result = "";
        Date date = new Date(time);
        SimpleDateFormat df1 = new SimpleDateFormat(format);
        result = df1.format(date);
        return result;
    }
}

