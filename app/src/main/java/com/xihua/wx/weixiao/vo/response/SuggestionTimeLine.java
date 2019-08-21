package com.xihua.wx.weixiao.vo.response;

public class SuggestionTimeLine {
    private int suggestionId;
    private String suggestionContent;

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

    public Long getSuggestionCreateTime() {
        return suggestionCreateTime;
    }

    public void setSuggestionCreateTime(Long suggestionCreateTime) {
        this.suggestionCreateTime = suggestionCreateTime;
    }

    private Long suggestionCreateTime;

}
