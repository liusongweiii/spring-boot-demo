package com.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 判断传入的time 类型  是否在 开始于结束时间之间
	 * @param starTime
	 * @param endTime
	 * @return
	 */
	public static boolean getTaskRunTimeType(Time starTime,Time endTime){
		boolean type = true;
		long nowTimeLong = LocalTime.now().toNanoOfDay();
		long startTimeLong = LocalTime.parse(starTime.toString()).toNanoOfDay();
		long endTimeLong = LocalTime.parse(endTime.toString()).toNanoOfDay();
		if(starTime.getTime()>endTime.getTime()){//判断是否跨天
			//当前时间小于开始时间 并且 当前时间要大于结束时间
			if(nowTimeLong<startTimeLong&&nowTimeLong>endTimeLong)
				type = false;
		}else{
			//当前时间在开始于结束时间之间  取反
			if(!(nowTimeLong>startTimeLong&&nowTimeLong<endTimeLong))
				type = false;
		}
		return type;
	}
	
	// 获取今天时分秒为0的时间戳
	public static Long getDateTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * data转String
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertDateToString(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String str = formatter.format(date);
		return str;
	}

	/**
	 * 时间戳转字符串
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String convertLongToString(Long time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String str = formatter.format(new Date(time));
		return str;
	}

	/**
	 * 字符串转data
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(dateStr);
	}


	/**
	 * 字符串转指定格式字符串
	 * @param dataStr
	 * @param pattern
	 * @param newpattern
	 * @return
	 */
	public static String convertStringToString(String dataStr,String pattern,String newpattern)throws ParseException{
		if(StringUtil.isEmpty(dataStr)){
			return null;
		}
		Date data = convertStringToDate(dataStr,pattern);
		return convertDateToString(data,newpattern);
	}

	/**
	 * 时间戳转timestamp
	 * @param time
	 * @return
	 */
	public static Timestamp convertLongToTimeStamp(long time) {
		return new Timestamp(time);
	}

	/**
	 * data转timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp convertDateToTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 时间戳转data
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date convertLongToDate(long time) throws ParseException {
		Date date = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(date);
		date = formatter.parse(str);
		return date;
	}

	/**
	 * 
	 * @Description: 获取当前日期
	 * 
	 * @author jaryur
	 * 
	 * @param @return 字符串格式日期
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getCurrentDate(String pattern) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 获取当前具体时间（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @Description:
	 * 
	 * @author jaryur
	 * 
	 * @param @return 字符串格式日期
	 */
	public static String getCurrentDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	// 获取报表的起始年月
	public static String getStartTime() {
		String startTime = "";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		year = year - 3;
		startTime = startTime + year + "01";
		return startTime;
	}

	// 获取当年的年份
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 
	 * @param date
	 *            获得当前月份的天数
	 */
	public static int getDayofMonth(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		return instance.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前日期的周一的日期
	 */
	public static Integer getMondayofWeek(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String weekhand = sdf.format(instance.getTime());
		return Integer.parseInt(weekhand);

	}

	/*
	 * 转年月日时分秒的String 为 Timestamp
	 */
	public static Timestamp convertStringToTimeStamp(String str) throws ParseException {
		if (str != null) {
			Date date = null;
				date = convertStringToDate(str, "yyyyMMddHHmmss");
			Timestamp time = new Timestamp(date.getTime());
			return time;
		}
		return null;
	}
	
	/*
	 * 转年月日时分秒的String 为 Timestamp
	 */
	public static Timestamp convertStringToTimeStamp(String str,String pattern) throws ParseException {
		if (str != null) {
			Date date = null;
			date = convertStringToDate(str,pattern);
			Timestamp time = new Timestamp(date.getTime());
			return time;
		}
		return null;
	}

	/*
	 * 将年月日格式的字符串转化为TimeStamp
	 */
	public static Timestamp convertStringToTimeStampFromYMD(String str) throws ParseException {
		if (str != null) {
			Date date = null;
			date = convertStringToDate(str, "yyyyMMdd");
			Timestamp time = new Timestamp(date.getTime());
			return time;
		}
		return null;
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获得yyyyMMddHHmmss格式
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}

	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int i = cal.get((Calendar.DAY_OF_WEEK)) - 1;
		return i;
	}
}
