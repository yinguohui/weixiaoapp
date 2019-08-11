package com.xihua.wx.weixiao.vo.request;

/**
 * @ClassName LoginRequest
 * @Auhtor ygh
 * @DATE 2019/7/24 20:43
 **/
public class LoginRequest {
    private String userTel;
    private String userPassword;

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
