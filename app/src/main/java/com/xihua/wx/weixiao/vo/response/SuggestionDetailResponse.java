package com.xihua.wx.weixiao.vo.response;


import com.xihua.wx.weixiao.bean.User;

public class SuggestionDetailResponse {
    private int suggestionId;
    private String suggestionContent;
    private String suggestionImg;
    private Integer suggestionUserId;
    private Long suggestionCreateTime;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getSuggestionContent() {
        return suggestionContent;
    }

    public void setSuggestionContent(String suggestionContent) {
        this.suggestionContent = suggestionContent;
    }

    public String getSuggestionImg() {
        return suggestionImg;
    }

    public void setSuggestionImg(String suggestionImg) {
        this.suggestionImg = suggestionImg;
    }

    public Integer getSuggestionUserId() {
        return suggestionUserId;
    }

    public void setSuggestionUserId(Integer suggestionUserId) {
        this.suggestionUserId = suggestionUserId;
    }

    public Long getSuggestionCreateTime() {
        return suggestionCreateTime;
    }

    public void setSuggestionCreateTime(Long suggestionCreateTime) {
        this.suggestionCreateTime = suggestionCreateTime;
    }
}
