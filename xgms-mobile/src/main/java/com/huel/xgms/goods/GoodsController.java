package com.huel.xgms.goods;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.app.goods.bean.Home;
import com.huel.xgms.app.goods.service.IGoodsService;
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
import java.io.File;

/**
 * 商品管理（主要 业务）
 * @author wsq
 * @date 2018/3/12
 */
@RestController
@RequestMapping(value = "/api/")
public class GoodsController {
    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private IGoodsService goodsService;
    @RequestMapping(value = "/pub/home", method = RequestMethod.GET)
    public ResponseBean getHomeInfo(HttpServletRequest request){
        LOG.debug("获取应用主页信息");
        String userId = (String) request.getAttribute("userId");
        try{
            Home data = goodsService.getHomeInfo();
            return ResponseBean.createSuccess(data);
        }catch (Exception e){
            LOG.error("获取应用主页信息失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }
    @RequestMapping(value = "/pri/goods", method = RequestMethod.POST)
    public ResponseBean publishGoods(GoodsInfo goodsInfoBean, HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        LOG.debug("用户发布商品，参数传递：title:{}, userId:{}", goodsInfoBean.getTitle(), userId);
        try{

            Assert.notNull(goodsInfoBean.getTitle(), "商品标题不能为空");
            Assert.notEmpty(goodsInfoBean.getFiles(), "请上传图片");
            Assert.notNull(goodsInfoBean.getType(), "商品类别不能为空");

            goodsInfoBean.setUserId(userId);
            goodsService.publishGoods(goodsInfoBean);

            return ResponseBean.createSuccess(null);
        }catch (Exception e){
            LOG.error("用户发布商品失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }

    /**
     * 分页查询商品列表
     * @param queryBean
     * @param request
     * @return ResponseBean
     */
    @RequestMapping(value = "/pub/goods", method = RequestMethod.GET)
    public ResponseBean getGoodsList(PagingQueryBean queryBean, HttpServletRequest request){
        LOG.debug("分页获取商品列表, queryBean:{}", JSON.toJSONString(queryBean));
        try {
            // 当前登录人员id
            String userId = (String) request.getAttribute("userId");

            PageData data = goodsService.list(queryBean);
            return ResponseBean.createSuccess(data);
        }catch (Exception e){
            LOG.error("查询商品列表失败：" + e);
            return ResponseBean.createError(e.getMessage());
        }
    }
}
