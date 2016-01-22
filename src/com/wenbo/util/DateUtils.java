package com.wenbo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 获取当前时间
 * 
 * @author jiang.li
 *
 */
public class DateUtils {

	/** 定义常量 **/
	public static final String DATE_GATHER = "MM-dd";
	public static final String DATE_JFP_STR = "yyyyMM";
	public static final String DATE_TIME_MM = "yyyy-MM-dd HH:mm";
	public static final String DATE_TIME_MM_NEW = "yyyy/MM/dd HH:mm";
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SMALL_STR = "yyyy-MM-dd";
	public static final String DATE_KEY_STR = "yyMMddHHmmss";
	public static final String DATE_KEY_FULL_STR = "yyyyMMddHHmmssSSS";

	public static void main(String[] s) {
		System.out.println(getLastTime(new Date(), -30));
	}

	/**
	 * 给定一个日期，返回加减n天后的日期
	 * 
	 * @param basicDate
	 * @param n
	 * @return
	 */
	public static String getLastTime(Date basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, n);
		return df.format(rightNow.getTime());
	}

	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, DATE_FULL_STR);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 两个时间比较
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(Date date1) {
		Date date2 = new Date();
		int rnum = date1.compareTo(date2);
		return rnum;
	}

	/**
	 * 两个时间比较(时间戳比较)
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(long date1) {
		long date2 = dateToUnixTimestamp();
		if (date1 > date2) {
			return 1;
		} else if (date1 < date2) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime(String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getTime(Date time, String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前计费期
	 * 
	 * @return
	 */
	public static String getJFPTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
		return df.format(new Date());
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String
	 *            date 需要转换的日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date) {
		long timestamp = 0;

		try {
			timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date)
					.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timestamp;
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String
	 *            date 需要转换的日期 yyyy-MM-dd
	 * 
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date, String dateFormat) {
		long timestamp = 0;

		try {
			timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timestamp;
	}

	/**
	 * 将当前日期转换成Unix时间戳
	 * 
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp() {

		long timestamp = new Date().getTime();

		return timestamp;
	}
/***
 * 返回截止到当前时间的倒计时时间
 * @param data 2015-10-20 08:00
 * @param formt 时间格式
 * @return
 */
	public static String timedaojishi(String data, String formt) {
		long day, hour, minutes, seconds;
		long endtime = DateUtils.dateToUnixTimestamp(data, formt);
		long timenow = DateUtils.dateToUnixTimestamp();
		long timecha = endtime - timenow;
		if(timecha<0){
			return "已截止";
		}
		day = ((timecha / 1000) / (3600 * 24));
		hour = ((timecha / 1000) - day * 86400) / 3600;
	//	minutes = ((timecha / 1000) - day * 86400 - hour * 3600) / 60;
	//	seconds = (timecha / 1000) - day * 86400 - hour * 3600 - minutes * 60;
		return "剩余"+day+"天"+hour+"小时";
		

	}

	/**
	 * 将Unix时间戳转换成日期
	 * 
	 * @param long timestamp 时间戳
	 * 
	 * @return String 日期字符串
	 */
	public static String unixTimestampToDate(long timestamp) {

		SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);

		sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));

		return sd.format(new Date(timestamp));
	}
}
