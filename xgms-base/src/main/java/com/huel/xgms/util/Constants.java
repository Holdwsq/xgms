package com.huel.xgms.util;

/**
 * 系统常量
 * @author wsq
 * @date 2018/3/14
 */
public class Constants {
    /**
     *未删除标识
     */
    public static final String DELETE_FLAG_NO = "0";
    /**
     * 删除标识
     */
    public static final String DELETE_FLAG_YES = "1";
    /**
     * 七牛token过期时间
     */
    public static final long QINIU_TOKEN_EXPIRES_TIME = 24 * 60 * 60;

    public static final String JWT_KEY = (String) ConfigUtil.getValue("JWT.KEY");

    public static final int JWT_ADMIN_EXPIRA = Integer.parseInt((String) ConfigUtil.getValue("JWT.ADMIN.EXPIRA"));

    public static final String MD5_SALT = (String) ConfigUtil.getValue("MD5.SALT");

    public static final String ADMIN_DEFAULT_PWD = (String) ConfigUtil.getValue("ADMIN.DEFAULT.PWD");

    public static final String APPUSER_DEFAULT_PWD = (String) ConfigUtil.getValue("APPUSER.DEFAULT.PWD");

    public static final String APK_UPLOAD_PAT = (String) ConfigUtil.getValue("APK.UPLOAD.PATH");

    public static final String SMS_ACCOUNT_SID = (String) ConfigUtil.getValue("SMS.ACCOUNT.SID");

    public static final String SMS_AUTH_TOKEN = (String) ConfigUtil.getValue("SMS.AUTH.TOKEN");

    public static final String SMS_BASE_URL = (String) ConfigUtil.getValue("SMS.BASE.URL");
    /**
     * 短信验证码过期时间 30分钟
     */
    public static final long PIN_EXPIRES_TIME = 30 * 60 * 1000;
    /**
     * 系统用户名前缀
     */
    public static final String APP_USER_PREFIX = (String) ConfigUtil.getValue("APP_USER_PREFIX");
}
