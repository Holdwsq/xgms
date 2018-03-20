package com.huel.xgms.app.goods.service;

import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.bean.GoodsInfoBean;
import com.huel.xgms.app.goods.bean.Home;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;

import java.io.IOException;

/**
 * 商品服务接口
 * @author wsq
 * @date 2018/3/13
 */
public interface IGoodsService {
    /**
     * 发布商品
     * @param goodsInfoBean
     */
    void publishGoods(GoodsInfo goodsInfoBean) throws IOException;

    /**
     * 获取商品列表
     * @param queryBean
     * @return
     */
    PageData list(PagingQueryBean queryBean);

    /**
     * 获取主页信息
     * @return
     */
    Home getHomeInfo();

    /**
     * 获取单个商品详情
     * @param goodsId
     * @return 商品详情
     */
    GoodsInfoBean getDetail(String goodsId);
}
