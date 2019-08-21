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
public class LikeDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer likedetailId;
    private String likedetailNo;
    private Integer likedetailUserId;
    private Integer likedetailTopicId;
    private Integer likedetailStatus;
    private Long likedetailCreateTime;


    public Integer getLikedetailId() {
        return likedetailId;
    }

    public void setLikedetailId(Integer likedetailId) {
        this.likedetailId = likedetailId;
    }

    public String getLikedetailNo() {
        return likedetailNo;
    }

    public void setLikedetailNo(String likedetailNo) {
        this.likedetailNo = likedetailNo;
    }

    public Integer getLikedetailUserId() {
        return likedetailUserId;
    }

    public void setLikedetailUserId(Integer likedetailUserId) {
        this.likedetailUserId = likedetailUserId;
    }

    public Integer getLikedetailTopicId() {
        return likedetailTopicId;
    }

    public void setLikedetailTopicId(Integer likedetailTopicId) {
        this.likedetailTopicId = likedetailTopicId;
    }

    public Integer getLikedetailStatus() {
        return likedetailStatus;
    }

    public void setLikedetailStatus(Integer likedetailStatus) {
        this.likedetailStatus = likedetailStatus;
    }

    public Long getLikedetailCreateTime() {
        return likedetailCreateTime;
    }

    public void setLikedetailCreateTime(Long likedetailCreateTime) {
        this.likedetailCreateTime = likedetailCreateTime;
    }

    @Override
    public String toString() {
        return "LikeDetail{" +
        ", likedetailId=" + likedetailId +
        ", likedetailNo=" + likedetailNo +
        ", likedetailUserId=" + likedetailUserId +
        ", likedetailTopicId=" + likedetailTopicId +
        ", likedetailStatus=" + likedetailStatus +
        ", likedetailCreateTime=" + likedetailCreateTime +
        "}";
    }
}
