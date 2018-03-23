package com.huel.xgms.app.goods.service;

import com.huel.xgms.app.goods.bean.GoodsComment;
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
     * 获取商品 留言
     * @param queryBean 查询bean
     * @return 分页评论bean
     */
    PageData<GoodsCommentBean> list(PagingQueryBean queryBean);

    /**
     * 新增商品 留言
     * @param comment 评论信息详情
     * @return 评论添加后的id
     */
    String addComment(GoodsComment comment);

    /**
     * 删除商品 留言
     * @param userId 用户id
     * @param id 留言记录id
     */
    void delete(String userId, String id);
}

