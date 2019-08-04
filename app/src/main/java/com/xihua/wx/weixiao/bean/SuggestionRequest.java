package com.xihua.wx.weixiao.bean;

public class SuggestionRequest {
    private String suggestionContent;
    private Integer suggestionUserId;

    public String getSuggestionContent() {
        return suggestionContent;
    }

    public void setSuggestionContent(String suggestionContent) {
        this.suggestionContent = suggestionContent;
    }

    public Integer getSuggestionUserId() {
        return suggestionUserId;
    }

    public void setSuggestionUserId(Integer suggestionUserId) {
        this.suggestionUserId = suggestionUserId;
    }
}
