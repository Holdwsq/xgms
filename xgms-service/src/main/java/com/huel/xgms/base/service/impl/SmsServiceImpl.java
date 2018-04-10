package com.huel.xgms.base.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.base.bean.SmsRespBean;
import com.huel.xgms.base.service.ISmsService;
import com.huel.xgms.util.Constants;
import com.huel.xgms.util.HttpRequestUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 短信发送服务接口实现
 * @author admin
 * @date 2018/4/9
 */
@Service
public class SmsServiceImpl implements ISmsService{
    /**
     * 发送短信路径
     */
    public static String opIndustry = "/industrySMS/sendSMS";

    @Override
    public void sendRegisterCode(String phone) {
        String url = Constants.SMS_BASE_URL + opIndustry;
        String commonParam = createCommonParam();
        Random random = new Random();
        // 随机数验证码
        int code = random.nextInt(8999) + 1000;
        String tmpSmsContent = "【校购】尊敬的用户，您好，您正在注册校购App应用，验证码为："+ code +"，若非本人操作请忽略此短信";
        // 请求参数
        String data = "accountSid=" + Constants.SMS_ACCOUNT_SID + "&to=" + phone
                + "&smsContent=" + tmpSmsContent + commonParam;

        String resp = HttpRequestUtils.post(url + data, null);

        if (!StringUtils.isEmpty(resp)){
            SmsRespBean smsRespBean = JSON.parseObject(resp, SmsRespBean.class);

        }
    }

    /**
     * 获取公共参数
     * @return
     */
    private static String createCommonParam()
    {
        // 时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // 签名
        String sig = DigestUtils.md5Hex(Constants.SMS_ACCOUNT_SID + Constants.SMS_AUTH_TOKEN + timestamp);

        return "&timestamp=" + timestamp + "&sig=" + sig;
    }
}
