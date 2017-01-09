package com.asiainfo.util.time;

import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * 
 * @author yi
 *
 */
public  class TimeUtil {

	public static final Logger logger = (Logger) Logger.getInstance(TimeUtil.class);
	
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
		logger.debug(getNextFridayDate());
	}
}
