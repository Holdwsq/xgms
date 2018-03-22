package com.huel.xgms.app.goods.service;

import com.huel.xgms.app.goods.bean.GoodsCommentBean;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;

import java.util.List;

/**
 * 评论服务接口
 * @author wsq
 * @date 2018/3/20
 */
public interface ICommentService {
    /**
     * 获取商品 评论
     * @param queryBean
     * @return
     */
    PageData<GoodsCommentBean> list(PagingQueryBean queryBean);
}

