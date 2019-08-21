package com.xihua.wx.weixiao.vo.request;

public class UserRequest {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userTel;
    private Long userBirth;
    private String userSign;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Long getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Long userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }
}
