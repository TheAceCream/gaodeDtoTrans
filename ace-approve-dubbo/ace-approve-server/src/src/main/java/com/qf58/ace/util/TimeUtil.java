package com.qf58.ace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 *
 * @author: HYC
 * @description:
 * @time: 2018年10月24日
 * @modifytime:
 */
public class TimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    private static final String EXACT_TO_SECOND = "yyyy-MM-dd HH:mm:ss";
    private static final String EXACT_TO_DAY = "yyyy-MM-dd";

    /**
     * 时间字符串获取时间戳
     * @param time
     * @param format
     * @return
     */
    public static Long String2TimeStamp(String time, String format){
        return TimeUtil.String2Date(time,format).getTime();
    }

    /**
     * 时间字符串获取时间戳
     * @param time
     * 默认格式 yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static Long String2TimeStamp(String time){
        return String2TimeStamp(time,EXACT_TO_SECOND);
    }

    /**
     * 时间格式字符串输出
     * @param date
     * @param format
     * @return
     */
    public static String Date2String(Date date, String format){
        SimpleDateFormat dateFormat=new SimpleDateFormat(format);
        String dateString="";
        try {
            if (date != null){
                dateString = dateFormat.format(date);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return dateString;
    }

    /**
     * 时间格式字符串输出
     * @param date 日期对象
     * 默认格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String Date2String(Date date){
        return Date2String(date,EXACT_TO_SECOND);
    }

    /**
     * 时间格式字符串输出
     * @param date 时间戳
     * 默认格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String Date2String(Long date){
        return Date2String(new Date(date),EXACT_TO_SECOND);
    }

    /**
     * 时间字符串获取date对象
     * @param time
     * @param format
     * @return
     */
    public static Date String2Date(String time, String format){
        SimpleDateFormat dateFormat=new SimpleDateFormat(format);
        Date date=new Date();
        try {
            date = dateFormat.parse(time);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return date;
    }

    /**
     * 时间字符串转Date对象
     * @param time
     * @return
     */
    public static Date String2Date(String time){
        return String2Date(time,EXACT_TO_SECOND);
    }

    public static void main(String[] args) {
        //System.out.println(TimeUtil.String2Date("2018-10-24 10:42:34",TimeUtil.EXACT_TO_SECOND).getTime());
        System.out.println(TimeUtil.Date2String(new Date(),TimeUtil.EXACT_TO_SECOND));
    }
}
