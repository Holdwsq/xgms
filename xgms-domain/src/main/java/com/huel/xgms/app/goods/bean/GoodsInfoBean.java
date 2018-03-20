package com.huel.xgms.app.goods.bean;

import java.util.List;

/**
 * 商品信息展示bean
 * @author wsq
 * @date 2018/3/15
 */
public class GoodsInfoBean extends GoodsInfo {
    private static final long serialVersionUID = -4740860109851637608L;

    /**
     * 发布人名字
     */
    private String userName;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 发布人头像
     */
    private String userPortrait;
    /**
     * 图片请求路径
     */
    private List<String> fileUrls;
    /**
     * 评论列表
     */
    private List<GoodsCommentBean> commentList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public List<GoodsCommentBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<GoodsCommentBean> commentList) {
        this.commentList = commentList;
    }
}
