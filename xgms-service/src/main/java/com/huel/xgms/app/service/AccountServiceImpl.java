package com.huel.xgms.app.service;

import com.alibaba.druid.util.StringUtils;
import com.huel.xgms.app.bean.AccountBean;
import com.huel.xgms.app.bean.User;
import com.huel.xgms.app.dao.IAccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description 登录服务
 * @author wsq
 * @date 2018/1/7
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private static Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private IAccountDao accountDao;

    @Override
    public User login(String account, String pwd) {
        if (StringUtils.isEmpty(account)
                || StringUtils.isEmpty(pwd)){
            return null;
        }
        return accountDao.login(account, pwd);
    }

    @Override
    public void register(AccountBean bean) {
        // 检测用户名的合法性
    }
}
