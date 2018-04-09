package com.huel.xgms;

import com.huel.xgms.base.service.ISmsService;
import com.huel.xgms.util.ConfigUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author admin
 * @date 2018/4/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class SmsServiceTest {
    @Autowired
    public ISmsService smsService;

    /**
     * 测试发送测试验证码
     */
    @Test
    public void testSendRegisterCode(){
        ConfigUtil.init("system.porperties");
        String phoneNum = "15670099659";
        String s = smsService.sendRegisterCode(phoneNum);
        System.out.println(s);
    }
}
