package com.xihua.wx.weixiao.bean;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author YGH123
 * @since 2019-07-24
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userNo;
    private String userName;
    private String userPassword;
    private Integer userStatus;
    private String userImg;
    private Long userCreateTime;
    private String userTel;
    private Long userBirth;
    private Integer userRole;
    private String userSign;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Long getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Long userCreateTime) {
        this.userCreateTime = userCreateTime;
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    @Override
    public String toString() {
        return "User{" +
        ", userId=" + userId +
        ", userNo=" + userNo +
        ", userName=" + userName +
        ", userPassword=" + userPassword +
        ", userStatus=" + userStatus +
        ", userImg=" + userImg +
        ", userCreateTime=" + userCreateTime +
        ", userTel=" + userTel +
        ", userBirth=" + userBirth +
        ", userRole=" + userRole +
        ", userSign=" + userSign +
        "}";
    }
}
