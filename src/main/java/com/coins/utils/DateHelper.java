package com.coins.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

    /**
     * 获取指定日期是星期几
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 给指定日期增加天数
     * @param date 指定日期
     * @param day 天数
     * @return 返回增加天数后的日期
     */
    public static Date addDate(Date date,int day){
        if(date== null )return null;
        return new Date((date.getTime()/1000 + day*24*60*60)*1000);

    }
    /**
     * 获取指定日期是星期几
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt, String[] weekDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取延后的0点时间
     *
     * @param dateTime
     * @return
     */
    public static Date getDayTimeEnd(Date dateTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取延后的0点时间
     *
     * @param dateTime
     * @return
     */
    public static Date getDayTimeBegin(Date dateTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取Days天后的的0点时间
     *
     * @param dateTime
     * @return
     */
    public static Date getDayTimeBegin(Date dateTime, Integer days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取7天后的的0点时间
     *
     * @param dateTime
     * @return
     */
    public static Date get7DayTimeBegin(Date dateTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取dateTime 当天，第 hour 小时的 Date
     * @param dateTime
     * @param hour
     * @return
     */
    public static Date getDayTime(Date dateTime, Integer hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 计算两个日期时间相差的月份
     * @param date1
     * @param date2
     * @return
     */
    public static Integer diffMonth(Date date1, Date date2) {
        Integer year1   = getYear(date1);
        Integer year2   = getYear(date2);
        Integer month1  = getMonth(date1);
        Integer month2  = getMonth(date2);
        Integer day1    = getDayOfMonth(date1);
        Integer day2    = getDayOfMonth(date2);
        Integer hour1   = getHour(date1);
        Integer hour2   = getHour(date2);
        Integer min1    = getMinute(date1);
        Integer min2    = getMinute(date2);
        Integer sec1    = getSecond(date1);
        Integer sec2    = getSecond(date2);

        int diffYear    = 0;
        int diffMonth   = 0;
        int diffDay     = 0;
        int diffHour    = 0;
        int diffMin     = 0;
        int diffSec     = 0;

        if (date1.getTime() > date2.getTime()) {
            diffYear    = year1 - year2;
            diffMonth   = month1 - month2;
            diffDay     = day1 - day2;
            diffHour    = hour1 - hour2;
            diffMin     = min1 - min2;
            diffSec     = sec1 - sec2;
        }
        else {
            diffYear    = year2 - year1;
            diffMonth   = month2 - month1;
            diffDay     = day2 - day1;
            diffHour    = hour2 - hour1;
            diffMin     = min2 - min1;
            diffSec     = sec2 - sec1;
        }


        int month = diffYear * 12 + diffMonth;

        if (diffSec < 0) {
            diffMin -= 1;
        }

        if (diffMin < 0) {
            diffHour -= 1;
        }

        if (diffHour < 0) {
            diffDay -= 1;
        }

        if (diffDay < 0) {
            month -= 1;
        }

        return month;
    }

    /**
     * 计算两个日期时间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static Integer diffDay(Date date1, Date date2) {
        long diffTime    = 0;
        date1   = DateHelper.getDayTimeBegin(date1);
        date2   = DateHelper.getDayTimeBegin(date2);

        if (date1.before(date2)) {
            diffTime    = date2.getTime() - date1.getTime();
        }
        else {
            diffTime    = date1.getTime() - date2.getTime();
        }

        long diffDays = diffTime / 86400 / 1000;
        return Integer.valueOf(String.valueOf(diffDays));
    }
    public static Integer diffHour(Date endDate, Date startDate) {
    	long diffTime    = endDate.getTime()-startDate.getTime();
    	long diffDays = diffTime / 3600 / 1000;
    	return Integer.valueOf(String.valueOf(diffDays));
    }

    public static Integer getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public static Integer getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static Integer getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Integer getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static Integer getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date max(Date date1, Date date2) {
        if (date1.before(date2)) {
            return date2;
        }
        return date1;
    }

    public static Date min(Date date1, Date date2) {
        if (date1.before(date2)) {
            return date1;
        }
        return date2;
    }
	public static String convertDateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	public static Date convertStringToDate(String date) {
	    try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        return df.parse(date);
        } catch (Exception e) {
           e.printStackTrace();
        }
	    return null;
	}

}
