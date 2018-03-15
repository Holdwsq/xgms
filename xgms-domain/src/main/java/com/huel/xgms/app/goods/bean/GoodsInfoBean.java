package com.huel.xgms.app.goods.bean;

/**
 * 商品信息展示bean
 * @author wsq
 * @date 2018/3/15
 */
public class GoodsInfoBean extends GoodsInfo {
    private static final long serialVersionUID = -4740860109851637608L;

    private String userName;
    private String schoolName;
    private String userPortrait;

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
}
