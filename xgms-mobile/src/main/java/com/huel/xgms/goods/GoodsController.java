package com.huel.xgms.goods;

import com.huel.xgms.app.goods.bean.GoodsInfo;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2018/3/12
 */
@RestController
@RequestMapping(value = "/api/")
public class GoodsController {
    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);

    @RequestMapping(value = "/pri/goods", method = RequestMethod.POST)
    public ResponseBean publishGoods(GoodsInfo goodsInfoBean, HttpServletRequest request){
        LOG.debug("用户发布商品，参数传递：{}");

        return ResponseBean.createError(null);
    }
}
