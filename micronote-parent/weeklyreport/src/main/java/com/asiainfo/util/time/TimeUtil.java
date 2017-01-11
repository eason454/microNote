package com.asiainfo.util.time;

import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * 
 * @author yi
 *
 */
public class TimeUtil {

	public static final Logger logger = (Logger) Logger.getInstance(TimeUtil.class);

	/**
	 * 用一个周号获取周一时间
	 */
	public static long getWeekStartDateByWeek(int weekYear, int weekOfYear) {
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(weekYear, weekOfYear, 2);
		setZero(calendar);
		logger.debug("getWeekStartDateByWeek:" + calendar.getTime());
		return calendar.getTime().getTime();
	}

	/**
	 * 用一个周号获取周五时间
	 */
	public static long getWeekEndDateByWeek(int weekYear, int weekOfYear) {
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(weekYear, weekOfYear + 1, 2);
		setZero(calendar);
		calendar.setTimeInMillis(calendar.getTime().getTime() - 1);
		logger.debug("getWeekEndDateByWeek:" + calendar.getTime());
		return calendar.getTime().getTime();
	}

	/**
	 * 获取本周一的日期
	 */
	public static long getStartDateThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		calendar.add(Calendar.DATE, -day_of_week + 1);
		logger.debug("getStartDateThisWeek:" + calendar.getTime());
		return calendar.getTime().getTime();
	}

	/**
	 * 获取本周末的日期
	 */
	public static long getEndDateThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		calendar.add(Calendar.DATE, -day_of_week + 7);
		logger.debug("getEndDateThisWeek:" + calendar.getTime());
		return calendar.getTime().getTime();
	}

	/**
	 * 获取下周一的日期
	 */
	public static long getNextWeekStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		setZero(calendar);
		logger.debug("getNextWeekStartDate:" + calendar.getTime());
		return calendar.getTime().getTime();
	}

	/**
	 * 獲取下周末的日期
	 * 
	 * @return
	 */
	public static long getNextWeekEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, 2);
		setZero(calendar);
		calendar.setTimeInMillis(calendar.getTime().getTime() - 1);
		logger.debug("getNextWeekEndDate:" + calendar.getTime());
		return calendar.getTime().getTime();
	}

	private static void setZero(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * 获取当前时间所属的周
	 * 
	 * @return
	 */
	public static int getWeekOfYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static void main(String[] args) {
		logger.debug(getWeekStartDateByWeek(2017, 1));
		logger.debug(getWeekEndDateByWeek(2017, 1));
		logger.debug(getStartDateThisWeek());
		logger.debug(getEndDateThisWeek());
		logger.debug(getNextWeekStartDate());
		logger.debug(getNextWeekEndDate());
		logger.debug(getWeekOfYear());
	}

}
