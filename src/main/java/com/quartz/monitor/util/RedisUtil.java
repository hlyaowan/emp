package com.quartz.monitor.util;

import java.util.Calendar;

public class RedisUtil {
    public  static String getAppCycleDeadline(String appCycle) {
        String[] strs = appCycle.split("[0-9]");
        String dUnit = strs[strs.length - 1];
        if ("m".equals(dUnit) || "M".equals(dUnit)) {
            int count = Integer.parseInt(appCycle.split("m|M")[0]);
            return getNextMonthStart(count);
        }
        if ("y".equals(dUnit) || "Y".equals(dUnit)) {
            int count = Integer.parseInt(appCycle.split("y|Y")[0]);
            return getNextYearStart(count);
        }
        if ("d".equals(dUnit) || "D".equals(dUnit)) {
            int count = Integer.parseInt(appCycle.split("d|D")[0]);
            return getNextDayStart(count);
        }
        if ("h".equals(dUnit) || "H".equals(dUnit)) {
            int count = Integer.parseInt(appCycle.split("h|H")[0]);
            return getNextHourStart(count);
        }
        return getNextMonthStart(1);
    }

    public static String getNextYearStart(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0);
        calendar.add(Calendar.YEAR, year);
        Long time = (calendar.getTimeInMillis() / 1000);
        return time.toString();
    }

    public static String getNextMonthStart(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        calendar.add(Calendar.MONTH, month);
        Long time = (calendar.getTimeInMillis() / 1000);
        return time.toString();
    }

    public static String getNextDayStart(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Long time = (calendar.getTimeInMillis() / 1000);
        return time.toString();
    }

    public static String getNextHourStart(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                     calendar.get(Calendar.HOUR_OF_DAY), 0, 0);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        Long time = (calendar.getTimeInMillis() / 1000);
        return time.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(getNextDayStart(1));
    }
}
