package com.huel.xgms.app.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.huel.xgms.app.goods.bean.GoodsComment;
import com.huel.xgms.app.goods.bean.GoodsCommentBean;
import com.huel.xgms.app.goods.dao.IGoodsCommentDao;
import com.huel.xgms.app.goods.service.ICommentService;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.UUIDMaker;
import org.jfaster.mango.plugin.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

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
    @Autowired
    private IUserService userService;

    @Override
    public PageData<GoodsCommentBean> list(PagingQueryBean queryBean) {
        if (queryBean == null){
            return null;
        }
        String goodsId = queryBean.getId();
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
        pageData.setRecordCount(page.getTotal());
        List<GoodsCommentBean> commentBeans = Lists.newArrayList();

        // 获取所有该商品的评论，使用内存寻找下级回复消息
        /// todo 以后数据量大时，采用分页进行 暂时获取所有的评论
        List<GoodsComment> allCommentList = goodsCommentDao.list(goodsId, null);
        // 遍历顶级评论列表
        for (GoodsComment comment : comments) {
            GoodsCommentBean bean = new GoodsCommentBean();
            BeanUtils.copyProperties(comment, bean);

            List<GoodsComment> tarList = findSubComments(comment.getId(), allCommentList);
            if (!CollectionUtils.isEmpty(tarList)){
                List<GoodsCommentBean> beanList = Lists.newArrayList();

                for (GoodsComment goodsComment : tarList) {
                    GoodsCommentBean subBean = new GoodsCommentBean();
                    BeanUtils.copyProperties(goodsComment, subBean);
                    beanList.add(subBean);
                }
                // 设置下级评论
                Collections.sort(beanList, new Comparator<GoodsCommentBean>() {
                    @Override
                    public int compare(GoodsCommentBean o1, GoodsCommentBean o2) {
                        return o2.getUpdateTime().compareTo(o1.getUpdateTime());
                    }
                });
                bean.setSubComments(beanList);
            }
            commentBeans.add(bean);
        }
        // 设置用户信息
        wrapUserInfo2List(commentBeans);
        // 排序
        Collections.sort(commentBeans, new Comparator<GoodsCommentBean>() {
            @Override
            public int compare(GoodsCommentBean o1, GoodsCommentBean o2) {
                return o2.getUpdateTime().compareTo(o1.getUpdateTime());
            }
        });
        pageData.setData(commentBeans);
        return pageData;
    }

    @Override
    public String addComment(GoodsComment comment) {
        if (comment == null) {
            throw new RuntimeException("评论不能为空");
        }

        long time = System.currentTimeMillis();
        String id = UUIDMaker.generateUUID();
        comment.setId(id);
        comment.setCreateTime(time);
        comment.setUpdateTime(time);
        comment.setDeleteFlag(Constants.DELETE_FLAG_NO);

        goodsCommentDao.save(comment);
        return id;
    }

    @Override
    public void delete(String userId, String id) {
        if (StringUtils.isEmpty(userId)
                ||StringUtils.isEmpty(id)){
            throw new RuntimeException("删除留言失败");
        }
        GoodsComment comment = goodsCommentDao.get(id);
        if (comment == null){
            throw new RuntimeException("不存在的留言");
        }
        String commenterId = comment.getCommenterId();
        if (!commenterId.equals(userId)){
            throw new RuntimeException("非法删除留言");
        }
        goodsCommentDao.delete(id);
    }

    /**
     * 转化用户信息到List
     * @param commentBeans
     */
    private void wrapUserInfo2List(List<GoodsCommentBean> commentBeans) {
        if (CollectionUtils.isEmpty(commentBeans)){
            return;
        }
        // 获取包含的用户id
        Set<String> userIds = Sets.newHashSet();
        for (GoodsCommentBean commentBean : commentBeans) {
            userIds.add(commentBean.getCommenterId());
            List<GoodsCommentBean> subComments = commentBean.getSubComments();

            if (!CollectionUtils.isEmpty(subComments)){
                for (GoodsCommentBean subComment : subComments) {
                    userIds.add(subComment.getCommenterId());
                }
            }
        }
        List<User> list = userService.list(userIds);
        Map<String, User> map = Maps.newHashMap();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        // 封装用户信息
        for (GoodsCommentBean commentBean : commentBeans) {
            User user = map.get(commentBean.getCommenterId());
            commentBean.setCommenterName(user.getAccountName());
            commentBean.setCommenterPortrait(user.getFileId());
            List<GoodsCommentBean> subComments = commentBean.getSubComments();
            if (!CollectionUtils.isEmpty(subComments)){
                // 封装下级评论
                for (GoodsCommentBean subComment : subComments) {
                    User user1 = map.get(subComment.getCommenterId());
                    subComment.setCommenterName(user1 != null ? user1.getAccountName() : "无名氏");
                    subComment.setCommenterPortrait(user1 != null ? user1.getFileId() : null);
                    subComment.setReplyUserId(user.getId());
                    subComment.setReplyUserName(user.getAccountName());
                }
            }
        }
    }

    /**
     * 查询下级评论
     * @param topId 顶级评论id
     * @param srcList 来源评论列表
     * @return List<GoodsComment>
     */
    private List<GoodsComment> findSubComments(String topId, List<GoodsComment> srcList) {
        if (CollectionUtils.isEmpty(srcList)){
            return null;
        }
        List<GoodsComment> tarList =  Lists.newArrayList();

        for (GoodsComment goodsComment : srcList) {
            if (StringUtils.isEmpty(goodsComment.getCommenterId())){
                continue;
            }
            if (topId.equals(goodsComment.getReplyCommentId())){
                tarList.add(goodsComment);
                List<GoodsComment> subComments = findSubComments(goodsComment.getId(), srcList);
                if (!CollectionUtils.isEmpty(subComments)){
                    tarList.addAll(subComments);
                }
            }
        }
        return tarList;
    }
}
