package com.huel.xgms.user;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
            LOG.error("查询用户信息失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @param userId
     * @param user
     * @return {@link ResponseBean}
     */
    @RequestMapping(value = "/pri/user/{userId}", method = RequestMethod.POST)
    public ResponseBean update(@PathVariable("userId")String userId, @RequestBody User user){
        LOG.debug("更新用户信息,参数传递：userID:{}, info:{}", userId, JSON.toJSONString(user));
        try {
            Assert.hasText(userId, "用户ID不能为空");
            Assert.notNull(user, "用户信息不能为null");

            user.setId(userId);
            userService.update(user);
            return ResponseBean.createSuccess(null);
        }catch (Exception e){
            LOG.error("更新用户信息失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }

}
