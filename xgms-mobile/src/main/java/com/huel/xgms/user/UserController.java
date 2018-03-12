package com.huel.xgms.user;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.user.bean.AuthBean;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.service.IAuthService;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.ISCII91;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private IAuthService authService;

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
    @RequestMapping(value = "/pri/user/info", method = RequestMethod.POST)
    public ResponseBean update(@RequestBody User user, HttpServletRequest request){
        // 用户的id 信息从Session会话中获得
        String userId = (String) request.getAttribute("userId");
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
    /**
     * 学生认证接口
     * @param bean
     * @param request
     * @return
     */
    @RequestMapping( value = "/pri/user/auth", method = RequestMethod.POST)
    public ResponseBean auth(@RequestBody AuthBean bean, HttpServletRequest request){
        LOG.debug("学生认证，认证信息：{}", JSON.toJSONString(bean));
        try {
            Assert.hasText(bean.getSchoolName(), "院校名称不能为空");
            Assert.hasText(bean.getEducation(), "学历不能为空");
            Assert.hasText(bean.getUserName(), "真实姓名不能为空");
            Assert.hasText(bean.getStudentNo(), "学号不能为空");
            Assert.hasText(bean.getEntranceYear(), "入学年份不能为空");
            // 用户通过 Session 获取，这里不做校验
            String userId = (String) request.getAttribute("userId");
            bean.setUserId(userId);
            authService.auth(bean);
            return ResponseBean.createSuccess(null);
        }catch (Exception e){
            LOG.error("学生认证失败：{}", e);
            return ResponseBean.createError(e.getMessage());
        }
    }

    /**
     * 获取用户认证状态
     * @param request
     * @return
     */
    @RequestMapping( value = "/pri/user/auth", method = RequestMethod.GET)
    public ResponseBean getAuthStatus(HttpServletRequest request){
        try {
            // 通过session 获取用户id
            String userId = (String) request.getAttribute("userId");
            String status = authService.getAuthStatus(userId);
            return ResponseBean.createSuccess(status);
        }catch (Exception e){
            LOG.error("获取用户认证失败：{}" + e);
            return ResponseBean.createError(e.getMessage());
        }
    }
}
