package com.huel.xgms.app;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.httpbean.ResponseEntity;
import com.huel.xgms.page.Pagination;
import com.huel.xgms.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 分页获取app用户
     * @param queryBean
     * @return
     */
    @RequestMapping(value = "/list")
    public Pagination list(PagingQueryBean queryBean){
        LOG.debug("获取app应用用户列表，查询bean：{}", JSON.toJSONString(queryBean));
        return userService.listByPage(queryBean);
    }

    /**
     * 重置用户密码
     * @param userId
     */
    @RequestMapping(value = "/reset/{userId}")
    public void resetPwd(@PathVariable String userId){
        LOG.debug("重置userId:{}的密码", userId);
        if (StringUtils.isEmpty(userId)){
            LOG.warn("重置密码失败：userID为空");
        }
        this.userService.resetPwd(userId, Constants.APPUSER_DEFAULT_PWD);
    }

    /**
     * 禁用app用户
     * @param userId
     */
    @RequestMapping(value = "/disable/{userId}")
    public void disable(@PathVariable String userId){
        LOG.debug("禁用app用户:{}", userId);
        userService.disable(userId);
    }

    /**
     * 启用app用户
     * @param userId
     */
    @RequestMapping(value = "/enable/{userId}")
    public void enable(@PathVariable String userId){
        LOG.debug("启用App用户：{}", userId);
        userService.enable(userId);
    }

    /**
     * 新增app用户
     * @return
     */
    @RequestMapping(value = "/addsave")
    public ResponseEntity addUser(User user){
        LOG.debug("新增app用户，user信息：{}", JSON.toJSONString(user));
        try {
            Assert.hasText(user.getAccountName(), "账户名不能为空");
            Assert.hasText(user.getUserName(), "用户姓名不能为空");
            Assert.hasText(user.getAuth(), "认证状态不能为空");
            if (!User.AUTH_NO.equals(user.getAuth())
                    && !User.AUTH_YES.equals(user.getAuth())){
                throw new RuntimeException("认证状态不合法");
            }
            // 新增App用户
            userService.addUserByAdmin(user);
            return ResponseEntity.createSuccess(null);
        }catch (Exception e){
            return ResponseEntity.createError(e.getMessage());
        }
    }

    /**
     * 编辑修改用户信息，包含用户姓名，和认证状态
     * @return
     */
    @RequestMapping(value = "editsave")
    public ResponseEntity editUser(User user){
        try {
            Assert.hasText(user.getId(), "用户id不能为空");
            Assert.hasText(user.getUserName(), "姓名不能为空");
            Assert.hasText(user.getAuth(), "认证状态不能为空");
            if (!User.AUTH_NO.equals(user.getAuth())
                    && !User.AUTH_YES.equals(user.getAuth())){
                throw new RuntimeException("认证状态不合法");
            }
            userService.editUser(user);
            return ResponseEntity.createSuccess(null);
        }catch (Exception e){
            return ResponseEntity.createError(e.getMessage());
        }
    }
}
