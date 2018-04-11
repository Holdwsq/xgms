package com.huel.xgms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间、日期工具类
 * @author wsq
 * @date 2018/4/10
 */
public class DateUtil {
    /**
     * 日期格式类型
     */
    public static final String DATE_PATTERN1 = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE_PATTERN2 = "yyyy/MM/dd";

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

    /**
     * 格式化日期
     * @param format 日期格式
     * @param date 日期
     * @return 格式化日期
     */
    public static String formatDate(String format, Date date){
        return new SimpleDateFormat(format).format(date);
    }
    public static String formatTimestamp(String format, long time){
        return formatDate(format, new Date(time));
    }

    public static void main(String... args){
        String s = DateUtil.formatDate(DateUtil.DATE_PATTERN1, new Date());
        System.out.println(s);
        long sendTime = 1523447156675L;
        long endTime = 1523448956675L;
        String startTiemStr = DateUtil.formatTimestamp(DateUtil.DATE_PATTERN1, sendTime);
        System.out.println("sendTime: " + startTiemStr);

        System.out.println("endTime: " + DateUtil.formatTimestamp(DateUtil.DATE_PATTERN1, endTime));
    }
}
