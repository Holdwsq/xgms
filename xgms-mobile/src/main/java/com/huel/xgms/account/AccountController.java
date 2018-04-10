package com.huel.xgms.account;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.huel.xgms.app.account.bean.AccountBean;
import com.huel.xgms.app.account.service.IAccountService;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.httpbean.ResponseBean;
import com.huel.xgms.util.HttpRequestUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户账户接口
 * @author wsq
 * @date 2018/1/3
 */
@RestController
@RequestMapping("/api")
public class AccountController {
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
            User user = accountService.register(bean);
            return  ResponseBean.createSuccess(user);
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
    @RequestMapping(value = "/pub/account/register2", method = RequestMethod.POST)
    public ResponseBean register(String phone, HttpServletRequest request){
        LOG.debug("手机号注册，phone：{}" + phone);
        try{
            if (StringUtils.isEmpty(phone)){
                throw new IllegalArgumentException("手机号为空");
            }
            accountService.register(phone);
            return ResponseBean.createSuccess(null);
        }catch(Exception e){
            return ResponseBean.createError(e.getMessage());
        }
    }
    public static void main(String[] args){
        String accountSid = "0cb1bf682d854c96ab9fa03efe05542a";
        String code = "12345";
        String smsContent = "【校购】尊敬的用户，您好，您正在注册校购App应用，验证码为："+ code +"，若非本人操作请忽略此短信";
        String to = "15670099659";
        String sig = "6ce9f65383e840a5959f5bd9ce7a05c8";
        Map<String, String> maps = Maps.newHashMap();
        maps.put("accountSid", accountSid);
        maps.put("smsContent", smsContent);
        maps.put("to", to);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        maps.put("timestamp", timestamp);
        sig = DigestUtils.md5Hex(accountSid + sig + timestamp);
        maps.put("sig", accountSid + sig + timestamp);
        String data = "accountSid=" + accountSid + "&smsContent=" + smsContent + "&to=" + to
                + "&timestamp=" + timestamp + "&sig=" + sig;
        String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
        HttpRequestUtils.post(url + "?" + data, null);
    }
}
