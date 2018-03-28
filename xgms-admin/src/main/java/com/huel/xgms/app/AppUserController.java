package com.huel.xgms.app;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用用户控制器
 * @author wsq
 * @date 2018/3/28
 */
@RestController
@RequestMapping("/admin/appuser")
public class AppUserController {
    private static final Logger LOG = LoggerFactory.getLogger(AppUserController.class);
    /**
     * app用户服务接口
     */
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/list")
    public Pagination list(PagingQueryBean queryBean){
        LOG.debug("获取app应用用户列表，查询bean：{}", JSON.toJSONString(queryBean));
        return userService.listByPage(queryBean);
    }
}
