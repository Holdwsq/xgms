package com.huel.xgms.app.account.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.account.bean.AccountBean;
import com.huel.xgms.app.account.service.IAccountService;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.account.dao.IAccountDao;
import com.huel.xgms.base.bean.PinRecordBean;
import com.huel.xgms.base.dao.IPinRecordDao;
import com.huel.xgms.base.service.ISmsService;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.DateUtil;
import com.huel.xgms.util.RegexUtil;
import com.huel.xgms.util.UUIDMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 应用账户服务
 * @author wsq
 * @date 2018/1/7
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private static Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private ISmsService smsService;
    @Autowired
    private IPinRecordDao pinRecordDao;

    @Override
    public User login(String accountName, String pwd) {
        if (StringUtils.isEmpty(accountName)
                || StringUtils.isEmpty(pwd)){
            return null;
        }
        return accountDao.login(accountName, pwd);
    }

    @Override
    public User register(AccountBean bean) {
        // 检测用户名的合法性
        LOG.debug("用户注册, 参数传递 bean:{}", JSON.toJSONString(bean));
        boolean flag = validateAccountName(bean.getAccountName(), null);
        if (!flag){
            throw new RuntimeException("用户已被注册");
        }

        // 保存用户账户信息
        User user = new User();
        String uuid = UUIDMaker.generateUUID();
        user.setId(uuid);
        user.setDeleteFlag(User.FLAG_DELETE_NO);
        user.setAccountName(bean.getAccountName());
        user.setPwd(bean.getPwd());
        long currentTimeMillis = System.currentTimeMillis();
        user.setCreateTime(currentTimeMillis);
        user.setUpdateTime(currentTimeMillis);
        user.setAuth(User.AUTH_NO);

        accountDao.register(user);
        return user;
    }

    @Override
    public boolean validateAccountName(String accountName, String userId) {
        LOG.debug("检测账户名是否合法,参数传递：accountName:{}, userId:{}", accountName, userId);
        User user = accountDao.checkAccountName(accountName, userId);
        // user 为空 账户名可以使用
        if (user == null){
            return true;
        }
        return false;
    }

    @Override
    public void modifyPwd(String userId, String pwd) {
        accountDao.modifyPwd(userId, pwd);
    }

    @Override
    public void getRegCode(final String phone) {
        // 验证手机号是否正确
        boolean mobile = RegexUtil.isMobile(phone);
        if (!mobile){
            throw new IllegalArgumentException("手机号不正确");
        }
        // 通过手机号获取用户信息
        User userByPhone = accountDao.getUserByPhone(phone);
        if (userByPhone != null){
            throw new IllegalArgumentException("该手机号已被注册");
        }
        // 获取该手机号当天注册次数
        Date startTime = DateUtil.getTodayStartTime();
        Date endTime = DateUtil.getTodayEndTime();
        int count = pinRecordDao.countByTime(phone, startTime.getTime(), endTime.getTime());
        if (count >= 5){
            throw new IllegalArgumentException("当天注册机会已用完");
        }

        // 另起一个线程去发送验证码
        new Thread(new Runnable() {
            @Override
            public void run() {
                smsService.sendRegisterCode(phone);
            }
        }).start();
    }
    @Override
    public void verifyCode(PinRecordBean bean) {
        if (bean == null){
            return;
        }
        // 通过手机号获取，最新的验证码信息
        PinRecordBean lastPin = pinRecordDao.getLastPin(bean.getPhone());
        if (lastPin == null){
            throw new IllegalArgumentException("验证码错误，请重新输入");
        }
        // 判断验证码是否过期
        long expiryTime = lastPin.getExpiryTime();
        if (expiryTime > System.currentTimeMillis()){
            throw new IllegalArgumentException("验证码失效，请重新输入");
        }
        Integer code = lastPin.getCode();
        if (!code.equals(bean.getCode())){
            throw new IllegalArgumentException("验证码错误，请重新输入");
        }
    }
}
