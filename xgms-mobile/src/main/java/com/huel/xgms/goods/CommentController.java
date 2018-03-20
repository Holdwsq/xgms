package com.huel.xgms.goods;

import com.huel.xgms.app.goods.service.ICommentService;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/pub/comments", method = RequestMethod.GET)
    public ResponseBean getComments(HttpServletRequest request){
        try{
            return ResponseBean.createSuccess(null);
        }catch (Exception e){
            LOG.error("加载评论列表失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }
}