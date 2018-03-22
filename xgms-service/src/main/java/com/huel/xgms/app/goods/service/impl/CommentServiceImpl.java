package com.huel.xgms.app.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.huel.xgms.app.goods.bean.GoodsComment;
import com.huel.xgms.app.goods.bean.GoodsCommentBean;
import com.huel.xgms.app.goods.dao.IGoodsCommentDao;
import com.huel.xgms.app.goods.service.ICommentService;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;
import org.jfaster.mango.plugin.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * 评论服务接口实现
 * @author wsq
 * @date 2018/3/20
 */
@Service
public class CommentServiceImpl implements ICommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private IGoodsCommentDao goodsCommentDao;

    @Override
    public PageData<GoodsCommentBean> list(PagingQueryBean queryBean) {
        if (queryBean == null
                || StringUtils.isEmpty(queryBean.getId())){
            return null;
        }
        PageData<GoodsCommentBean> pageData = new PageData<>();
        pageData.setPageNo(queryBean.getPageNo());
        pageData.setPageSize(queryBean.getPageSize());

        // 组建 mongo 的page内置对象
        Page page = new Page(pageData.getPageNo(), pageData.getPageSize());
        List<GoodsComment> comments = goodsCommentDao.list(queryBean, page);
        if (CollectionUtils.isEmpty(comments)){
            pageData.setRecordCount(0);
            pageData.setData(null);
            return pageData;
        }
        LOG.info("获取到的评论列表为：{}" + JSON.toJSONString(comments));
        List<GoodsCommentBean> commentBeans = Lists.newArrayList();
        // 遍历顶级评论列表
        for (GoodsComment comment : comments) {
            GoodsCommentBean bean = new GoodsCommentBean();
            BeanUtils.copyProperties(comment, bean);
            commentBeans.add(bean);
        }
        // 获取所有该商品的评论，使用内存寻找下级回复消息
        /// todo 以后数据量大时，采用分页进行
        List<GoodsComment> allCommentList = goodsCommentDao.list("", page);


        return null;
    }
}
