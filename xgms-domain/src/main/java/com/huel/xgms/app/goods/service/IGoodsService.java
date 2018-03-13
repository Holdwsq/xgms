package com.huel.xgms.app.goods.service;

import com.huel.xgms.app.goods.bean.GoodsInfo;

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
}
