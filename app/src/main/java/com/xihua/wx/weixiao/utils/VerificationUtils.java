package com.xihua.wx.weixiao.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class VerificationUtils {

    //验证手机密码
    public static boolean judge(EditText tel, EditText pwd, Context context){
        //验证手机号正则表达式
        Pattern p =Pattern.compile("^[1][0-9]{10}$");
        boolean b = p.matcher(tel.getText().toString()).matches();
        if (!b){
            ToastUtil.showToast(context,"请输入正确的手机号");
            return b;
        }
        p = Pattern.compile("^.{6,16}$");
        b = p.matcher(pwd.getText().toString()).matches();
        if (!b){
            ToastUtil.showToast(context,"请输入正确的密码（6-16位）");
        }
        return b;
    }
    //注册验证
    public static boolean judge(EditText tel, EditText pwd, EditText secpwd, Context context){
        //验证手机号正则表达式
        Pattern p =Pattern.compile("^[1][0-9]{10}$");
        boolean b = p.matcher(tel.getText().toString()).matches();
        if (!b){
            ToastUtil.showToast(context,"请输入正确的手机号");
            return b;
        }
        p = Pattern.compile("^.{6,16}$");
        b = p.matcher(pwd.getText().toString()).matches();
        if (!b){
            ToastUtil.showToast(context,"请输入正确的密码（6-16位）");
            return b;
        }
        if (!pwd.getText().toString().equals(secpwd.getText().toString())){
            ToastUtil.showToast(context,"两次密码不一致");
            return false;
        }
        return b;
    }
    //验证手机
    public static boolean judge(EditText tel ,Context context){
        //验证手机号正则表达式
        Pattern p =Pattern.compile("^[1][0-9]{10}$");
        boolean b = p.matcher(tel.getText().toString()).matches();
        if (!b){
            ToastUtil.showToast(context,"请输入正确的手机号");
            return b;
        }
        return b;
    }
}
