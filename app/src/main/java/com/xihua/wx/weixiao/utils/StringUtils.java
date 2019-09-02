package com.xihua.wx.weixiao.utils;

import android.widget.EditText;

public class StringUtils {

    public static Boolean judgeIsBlack(EditText editText){
        String s = editText.getText().toString();
        if (s.trim().equals("")){
            return false;
        }
        return true;
    }
    public static String getEidtContent(EditText editText){
        return editText.getText().toString().trim();
    }
    public static Double getEidtContentDouble(EditText editText){
        return Double.parseDouble(editText.getText().toString().trim());
    }
    public static Integer getEidtContentInteger(EditText editText){
        return Integer.parseInt(editText.getText().toString().trim());
    }
}
