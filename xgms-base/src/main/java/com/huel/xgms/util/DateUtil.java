package com.huel.xgms.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间、日期工具类
 * @author wsq
 * @date 2018/4/10
 */
public class DateUtil {
    /**
     * 获取今日起始时间
     * @return
     */
    public static Date getTodayStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取今日结束时间
     * @return
     */
    public static Date getTodayEndTime(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }
}
