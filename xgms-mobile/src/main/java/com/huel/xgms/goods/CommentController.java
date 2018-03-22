package com.huel.xgms.goods;

import com.huel.xgms.app.goods.bean.GoodsCommentBean;
import com.huel.xgms.app.goods.service.ICommentService;
import com.huel.xgms.base.bean.PageData;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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
            LOG.error("加载评论列表失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }
}