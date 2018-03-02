package com.huel.xgms.account;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.bean.AccountBean;
import com.huel.xgms.app.bean.User;
import com.huel.xgms.app.service.IAccountService;
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
 * @description
 * @author wsq
 * @date 2018/1/3
 */
@RestController
@RequestMapping("/api/pub")
public class UserLoginController {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAccountService loginService;
    /**
     * @description 客户端用户登录验证
     * @author wsq
     * @date 2018/1/7
     * @param account
     * @param pwd
     * @return ResponseBean
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean login(String account, String pwd, HttpServletRequest request){
        LOG.debug("客服端用户登录,参数传递:name:{}, pwd:{}", account, pwd);
        if (StringUtils.isEmpty(account)
                || StringUtils.isEmpty(pwd)){
            return ResponseBean.createError("用户名和密码不能为空");
        }
        User user = loginService.login(account, pwd);
        if (user != null){
            user.setCreateTime(0L);
            user.setPwd(null);
            user.setAccount(null);
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBean register(AccountBean bean, HttpServletRequest request){
        LOG.debug("注册用户注册，参数传递：{}", JSON.toJSONString(bean));
        try{
            Assert.hasText(bean.getAccount(), "用户名不能为空");
            Assert.hasText(bean.getPwd(), "密码不能为空");
            if (!bean.getPwd().equals(bean.getConfirmPwd())){
                throw new RuntimeException("两次密码不一致");
            }
            // 调用注册接口
            loginService.register(bean);
            return  ResponseBean.createSuccess(null);
        }catch(Exception e){
            return ResponseBean.createError(e.getMessage());
        }
    }

}
