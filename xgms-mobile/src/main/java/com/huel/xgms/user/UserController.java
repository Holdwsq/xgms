package com.huel.xgms.user;

import com.alibaba.druid.util.StringUtils;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin
 * @author wsq
 * @date 2018/3/4
 */
@RestController
@RequestMapping( value = "/api")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/pub/user/{userId}", method = RequestMethod.GET)
    public ResponseBean getUserInfo(@PathVariable("userId") String userId){
        LOG.debug("获取用户信息，参数传递 userID ：{}", userId);
        try{
            if (StringUtils.isEmpty(userId)){
                throw new RuntimeException("获取用户信息失败");
            }
            // 调用 服务 获取用户信息
            User userInfo = userService.getUserInfo(userId);
            if (userInfo != null){
                userInfo.setPwd(null);
            }
            return ResponseBean.createSuccess(userInfo);
        }catch (Exception e){
            return ResponseBean.createError(e.getMessage());
        }
    }
}
