package com.quartz.monitor.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

    /** 默认日期格式 */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        format.applyPattern("yyyyMMddHHmmss");
        String timeStr = format.format(new Date());
        return timeStr;
    }

    public static String getCurrentTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        format.applyPattern("yyyy-MM-dd HH:mm:ss");
        String timeStr = format.format(new Date());
        return timeStr;
    }

    /****
     * 根据格式获取时间
     * 
     * @param formatStr 如："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getCurrentTimestamp(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        format.applyPattern(formatStr);
        String timeStr = format.format(new Date());
        return timeStr;
    }

    /**
     * 将Timestamp转换成指定的日期格式
     * 
     * @param formatStr 默认 yyyy-MM-dd HH:mm:ss)
     * @param dateSource 源日期 Timestamp)
     * @return
     */
    public static String getTimeByFomat(String formatStr, Timestamp dateSource) {
        if (StringUtils.isBlank(formatStr)) {// 容错处理
            formatStr = DEFAULT_PATTERN;
        }
        if (dateSource == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(dateSource);
    }

    /**
     * 将Timestamp转换成默认的日期格式 "yyyy-MM-dd HH:mm:ss"
     * 
     * @return 格式化的描述时间字符串
     */
    public static String getTimeByFomat(Timestamp dateSource) {
        return getTimeByFomat(DEFAULT_PATTERN, dateSource);
    }

    /**
     * 将Timestamp转换成指定的日期格式
     * 
     * @param fomatStr "yyyy年MM月dd日 HH:mm:ss")
     * @param dateSource 源日期 Date)
     * @return
     */
    public static String getTimeByFomat(String fomatStr, Date dateSource) {
        if (dateSource == null) {// 容错处理
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        format.applyPattern(fomatStr);
        return format.format(dateSource);
    }

    /****
     * 将日期字符串按照指定的日期格式输出
     * 
     * @param formatStr 如："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getTimeByFormat(String time, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        if (StringUtils.isNotBlank(formatStr)) {
            format.applyPattern(formatStr);
        }
        return StringUtils.isNotBlank(time) ? format.format(format.parse(time)) : "";
    }

    /****
     * 将日期字符串按照特定的格式("yyyy-MM-dd HH:mm:ss")输出
     * 
     * @param formatStr 如："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getTimeByFormat(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        return StringUtils.isNotBlank(time) ? format.format(format.parse(time)) : "";
    }

    /**
     * 验证时间(long类型)是否在开始时间(1970-01-01 00:00:00)和当前时间范围内
     * 
     * @param time 需要验证的时间 long
     * @return 在返回true，否则false
     */
    public static boolean whetherValidDate(long time) {
        long beginDate = -28800000l;// 电脑的开始时间 1970-01-01 00:00:00
        long endDate = System.currentTimeMillis();// 电脑当前时间
        if (time > beginDate && time <= endDate) {
            return true;
        }
        return false;
    }
}
