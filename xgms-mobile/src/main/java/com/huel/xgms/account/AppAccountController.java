package com.huel.xgms.account;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.account.bean.AccountBean;
import com.huel.xgms.app.account.service.IAccountService;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.httpbean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户账户接口
 * @author wsq
 * @date 2018/1/3
 */
@RestController
@RequestMapping("/api")
public class AppAccountController {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAccountService accountService;
    /**
     * @description 客户端用户登录验证
     * @author wsq
     * @date 2018/1/7
     * @param accountName
     * @param pwd
     * @return ResponseBean
     */
    @RequestMapping(value = "/pub/account/login", method = RequestMethod.POST)
    public ResponseBean login(String accountName, String pwd, HttpServletRequest request){
        LOG.debug("客服端用户登录,参数传递:accountName:{}, pwd:{}", accountName, pwd);
        if (StringUtils.isEmpty(accountName)
                || StringUtils.isEmpty(pwd)){
            return ResponseBean.createError("用户名和密码不能为空");
        }
        User user = accountService.login(accountName, pwd);
        if (user != null){
            user.setCreateTime(0L);
            user.setPwd(null);
            user.setAccountName(null);
            // 保存用户信息到session中
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            return ResponseBean.createSuccess(user);
        }
        return ResponseBean.createError("用户名或密码不正确");
    }

    /**
     * @description 用户注册接口
     * @param bean
     * @return
     */
    @RequestMapping(value = "/pub/account/register", method = RequestMethod.POST)
    public ResponseBean register(AccountBean bean, HttpServletRequest request){
        LOG.debug("注册用户注册，参数传递：{}", JSON.toJSONString(bean));
        try{
            Assert.hasText(bean.getAccountName(), "用户名不能为空");
            Assert.hasText(bean.getPwd(), "密码不能为空");
            if (!bean.getPwd().equals(bean.getConfirmPwd())){
                throw new RuntimeException("两次密码不一致");
            }
            // 调用注册接口
            accountService.register(bean);
            return  ResponseBean.createSuccess(null);
        }catch(Exception e){
            return ResponseBean.createError(e.getMessage());
        }
    }

    /**
     * 修改密码 该接口需要认证 后期做处理
     * @param userId
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/pri/account/modifyPwd", method = RequestMethod.POST)
    public ResponseBean modifyPwd(String userId, String pwd, HttpServletRequest request){
        LOG.debug("修改密码,参数传递 userId:{}, pwd:{}", userId, pwd);
        try {
            if (StringUtils.isEmpty(userId)
                    || StringUtils.isEmpty(pwd)){
                throw new RuntimeException("修改密码失败");
            }
            HttpSession session = request.getSession();
            String id = (String) session.getAttribute("userId");
            System.out.println("id:" + id);
            accountService.modifyPwd(userId, pwd);
            return ResponseBean.createSuccess(null);
        }catch (Exception e){
            return ResponseBean.createError(e.getMessage());
        }
    }

}
