package com.asiainfo.util.time;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

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
       DateTime dateTime=new DateTime();
       return  dateTime.dayOfWeek().withMinimumValue().millisOfDay().withMinimumValue().toDate().getTime();
    }
	/**
	 * 获取下周一的日期
	 */
	public static long getNextWeekStartDate() {
		return getEndDateThisWeek();//本周末最后一秒等同于下周第一秒
	}

	/**
	 * 獲取下周末的日期
	 * 
	 * @return
	 */
    public static long getNextWeekEndDate() {
        DateTime dateTime=new DateTime();
        return dateTime.plusWeeks(1).dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate().getTime();
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
/*
	public static void main(String[] args) {
		logger.debug(getWeekStartDateByWeek(2017, 1));
		logger.debug(getWeekEndDateByWeek(2017, 1));
		logger.debug(getStartDateThisWeek());
		logger.debug(getEndDateThisWeek());
		logger.debug(getNextWeekStartDate());
		logger.debug(getNextWeekEndDate());
		logger.debug(getWeekOfYear());
		logger.error(getStartDateThisWeek());
	}*/
	public static int getDayOfWeek(){
		DateTime dateTime=new DateTime();
		return dateTime.getDayOfWeek();
	}
	public static long getEndDateThisWeek(){
        DateTime dateTime=new DateTime();
        return dateTime.dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate().getTime();
    }
	public static long getDayInWeek(long currentTime, String day) {
		/*
		 * 获取输入时间所在周的某一天 day取值：MONDAY,SUNDAY 暂时只取每周的首与尾，不作其它考虑
		 */
		Calendar calendar = Calendar.getInstance();
		if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
		}
		if (day == "MONDAY") {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else if (day == "SUNDAY") {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
		return calendar.getTimeInMillis();
	}

	public static int getWeekOfYear(long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

}
