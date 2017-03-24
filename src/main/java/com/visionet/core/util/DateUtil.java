package com.visionet.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static Calendar now = Calendar.getInstance();
	
	public static int getYear(){
		return now.get(Calendar.YEAR);
	}
	
	public static int getMonth(){
		return now.get(Calendar.MONTH);
	}
	
	public static int getDay(){
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * return timestamp and add random number
	 * 
	 * @return
	 */
	public static String gettimeStamp() {
		long timeMillis = System.currentTimeMillis();
		long x = 1 + (int) (Math.random() * 10000);
		return String.valueOf(timeMillis) + "" + String.valueOf(x);
	}

	public static Date String2DateInYYYYMMDD(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;

	}

	public static Date String2DateInYYYYMMDDHHMMSS(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	public static String returnString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String d = "";
		d = sdf.format(date);
		return d;
	}

	public static void main(String[] args) {
		// String s = "2016-06-23 16:56:55";
		// Date string2DateInYYYYMMDD = String2DateInYYYYMMDDHHMMSS(s);
		// System.err.println(string2DateInYYYYMMDD);
		String gettimeStamp = returnString(new Date());
		System.err.println(gettimeStamp);
	}

	/**
	 * 两个时间差，返回分钟数（Long类型）
	 * @param beforeDate
	 * @param endDate
	 * @return
	 */
	public static long minusTime(Date beforeDate, Date endDate) {
		Long minutes = 0L;
		try {
			Long ss = endDate.getTime() - beforeDate.getTime();
			minutes = ss / (1000 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minutes;
	}



}
