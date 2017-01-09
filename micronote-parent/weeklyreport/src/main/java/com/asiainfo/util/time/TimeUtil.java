package com.asiainfo.util.time;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 
 * @author yi
 *
 */
public  class TimeUtil {

	public static final Logger logger = (Logger) Logger.getInstance(TimeUtil.class);
	
	/**
	 * 用一个周号获取周一时间
	 */
	public static long getMondayDateByWeek(int weekYear,int weekOfYear){
		Calendar  calendar = Calendar.getInstance();
		calendar.setWeekDate(weekYear, weekOfYear, 2);
		logger.debug("getMondayDate:"+calendar.getTime());
		return calendar.getTime().getTime();
	}
	
	/**
	 * 用一个周号获取周五时间
	 */
	public static long getFridayDateByWeek(int weekYear,int weekOfYear){
		Calendar  calendar = Calendar.getInstance();
		calendar.setWeekDate(weekYear, weekOfYear, 6);
//		calendar.
		logger.debug("getMondayDate:"+calendar.getTime());
		return new Date( calendar.getTime().getTime()).;
	}
	
	/**
	 * 用一个周号获取周五时间
	 */
	/**
	 * 获取本周一的日期
	 */
	public static long getMondayDate(){
		Calendar  calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR , 0);
		logger.debug("getMondayDate:"+calendar.getTime());
		return calendar.getTime().getTime();
	}
	/**
	 * 获取本周五的日期
	 */
	public static long getFridayDate(){
		Calendar  calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR , 0);
		calendar.add(Calendar.DATE, 4);
		logger.debug("getFridayDate:"+calendar.getTime());
		return calendar.getTime().getTime();
	}
    /**
     * 获取下周一的日期
     */
	public static long getNextMondayDate(){
		Calendar  calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR , 1);
		logger.debug("getNextMondayDate:"+calendar.getTime());
		return calendar.getTime().getTime();
	}
	
	/**
	 * 獲取下周五的日期
	 * @return
	 */
	public static long getNextFridayDate(){
		Calendar  calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR , 1);
		calendar.add(Calendar.DATE, 4);
		logger.debug("getNextFridayDate:"+calendar.getTime());
		return calendar.getTime().getTime();
	}
	
	public static void main(String[] args){
		logger.debug(getMondayDateByWeek(2017,1));
		logger.debug(getFridayDateByWeek(2017,1));
		logger.debug(getMondayDate());
		logger.debug(getFridayDate());
		logger.debug(getNextMondayDate());
		logger.debug(getNextFridayDate());
	}
}
