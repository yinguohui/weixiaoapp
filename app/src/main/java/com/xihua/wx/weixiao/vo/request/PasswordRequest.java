package com.xihua.wx.weixiao.vo.request;

public class PasswordRequest {

  private String oldPasswrod;
  private String newPassword;
  private int userId;

    public String getOldPasswrod() {
        return oldPasswrod;
    }

    public void setOldPasswrod(String oldPasswrod) {
        this.oldPasswrod = oldPasswrod;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
