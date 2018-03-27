package com.huel.xgms.app.goods.bean;

import com.huel.xgms.util.Constants;

import java.io.Serializable;

/**
 * 商品评论实体
 * @author wsq
 * @date 2018/3/20
 */
public class GoodsComment implements Serializable{
    private static final long serialVersionUID = -1108298313463436629L;
    /**
     * 评论记录id
     */
    private String id;
    /**
     * 评论对应的商品id
     */
    private String goodsId;
    /**
     * 评论人id
     */
    private String commenterId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复评论id
     */
    private String replyCommentId;
    /**
     * 评论创建时间
     */
    private Long createTime;
    /**
     * 评论更新时间
     */
    private Long updateTime;
    /**
     * 删除标识 0 = 未删除， 1 = 删除
     * 未删除，{@link Constants } Delete_Flag_NO
     * 已删除， {@link Constants } DELETE_FLAG_YES
     */
    private String deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(String commenterId) {
        this.commenterId = commenterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
