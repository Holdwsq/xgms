package com.huel.xgms.app.goods.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 主页信息bean
 * @author wsq
 * @date 2018/3/18
 */
public class Home implements Serializable{

    private static final long serialVersionUID = -8234952771439885130L;
    /**
     * Benner 轮播图列表
     */
    private List<BaseBean> homeBanners;
    /**
     * 折扣列表
     */
    private List<BaseBean> homeDiscounts;
    /**
     * 商品信息列表
     */
    private List<GoodsInfo> goodsInfos;

    public List<BaseBean> getHomeBanners() {
        return homeBanners;
    }

    public void setHomeBanners(List<BaseBean> homeBanners) {
        this.homeBanners = homeBanners;
    }

    public List<BaseBean> getHomeDiscounts() {
        return homeDiscounts;
    }

    public void setHomeDiscounts(List<BaseBean> homeDiscounts) {
        this.homeDiscounts = homeDiscounts;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }
}
