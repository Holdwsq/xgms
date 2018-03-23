package com.huel.xgms.app.goods.bean;

import java.util.List;

/**
 * 商品评论bean
 * @author wsq
 * @date 2018/3/20
 */
public class GoodsCommentBean extends GoodsComment {
    private static final long serialVersionUID = -3952662915563017374L;
    /**
     * 评论者名称
     */
    private String commenterName;
    /**
     * 评论者头像
     */
    private String commenterPortrait;
    /**
     * 被回复人用户id， 若{replyCommentId 为空 该字段为null}
     */
    private String replyUserId;
    /**
     * 被回复人名字
     */
    private String replyUserName;
    /**
     * 子回复列表
     */
    private List<GoodsCommentBean> subComments;

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommenterPortrait() {
        return commenterPortrait;
    }

    public void setCommenterPortrait(String commenterPortrait) {
        this.commenterPortrait = commenterPortrait;
    }

    public List<GoodsCommentBean> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<GoodsCommentBean> subComments) {
        this.subComments = subComments;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }
}
