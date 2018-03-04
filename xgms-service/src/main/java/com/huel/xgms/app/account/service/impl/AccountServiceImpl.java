package com.huel.xgms.app.account.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.account.bean.AccountBean;
import com.huel.xgms.app.account.service.IAccountService;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.account.dao.IAccountDao;
import com.huel.xgms.util.UUIDMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public User login(String accountName, String pwd) {
        if (StringUtils.isEmpty(accountName)
                || StringUtils.isEmpty(pwd)){
            return null;
        }
        return accountDao.login(accountName, pwd);
    }

    @Override
    public void register(AccountBean bean) {
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
        user.setAccountName(bean.getAccountName());
        user.setPwd(bean.getPwd());
        long currentTimeMillis = System.currentTimeMillis();
        user.setCreateTime(currentTimeMillis);
        user.setUpdateTime(currentTimeMillis);

        accountDao.register(user);
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
}
