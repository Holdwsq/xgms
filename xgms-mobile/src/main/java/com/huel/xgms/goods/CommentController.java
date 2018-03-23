package com.huel.xgms.goods;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.goods.bean.GoodsComment;
import com.huel.xgms.app.goods.bean.GoodsCommentBean;
import com.huel.xgms.app.goods.service.ICommentService;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论控制器
 * @author wsq
 * @date 2018/3/20
 */
@RestController
@RequestMapping(value = "/api")
public class CommentController {
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private ICommentService commentService;

    /**
     * 分页获取商品评价
     * @param request
     * @return
     */
    @RequestMapping(value = "/pub/comments", method = RequestMethod.GET)
    public ResponseBean getComments(PagingQueryBean queryBean, HttpServletRequest request){
        LOG.debug("获取商品评价>>>>>>goodsId:{}", queryBean.getId());
        try{
            Assert.hasText(queryBean.getId(), "商品id不能为空");
            String userId = (String) request.getAttribute("userId");
            /// todo 以后可能会记录日志，记录用户的浏览记录 或者 拦截器统一处理
            PageData<GoodsCommentBean> pageData = commentService.list(queryBean);
            return ResponseBean.createSuccess(pageData);
        }catch (Exception e){
            LOG.error("加载留言列表失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }

    /**
     * 发表评论
     * @param comment 评论信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/pri/comments", method = RequestMethod.POST)
    public ResponseBean publishComment(GoodsComment comment, HttpServletRequest request){
        LOG.debug("发表评论，评论信息：{}", JSON.toJSONString(comment));
        try{
            // 从session获取userid
            String userId = (String) request.getAttribute("userId");
            // 这里暂时写死
            if (userId == null){
                userId = "4fd544049f974767b08e22829c7cb188";
            }
            Assert.hasText(comment.getContent(), "留言内容不能为空");
            Assert.hasText(comment.getGoodsId(), "商品id不能为空");

            comment.setCommenterId(userId);
            String commentId = commentService.addComment(comment);
            return ResponseBean.createSuccess(commentId);
        }catch (Exception e){
            LOG.error("发表留言失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }

    /**
     * 删除留言
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/pri/comments/{id}", method = RequestMethod.DELETE)
    public ResponseBean deleteComment(@PathVariable String id, HttpServletRequest request){
        try{
            // 通过request 拿来 userId，下面判断是否是这个用户
            String userId = (String) request.getAttribute("userId");
            Assert.hasText(id, "删除的留言id不能为空");
            commentService.delete(userId, id);
            return ResponseBean.createSuccess(null);
        }catch (Exception e){
            LOG.error("删除留言失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }
}