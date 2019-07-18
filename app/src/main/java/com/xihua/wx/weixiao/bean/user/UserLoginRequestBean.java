package com.xihua.wx.weixiao.bean.user;

public class UserLoginRequestBean {
    private String tel;
    private String password;
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginRequestBean{" +
                "tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
