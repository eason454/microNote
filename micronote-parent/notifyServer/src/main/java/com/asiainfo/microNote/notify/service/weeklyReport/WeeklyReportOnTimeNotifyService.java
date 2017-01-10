package com.asiainfo.microNote.notify.service.weeklyReport;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 周报通知服务
 * @author yi
 */
@Service
public class WeeklyReportOnTimeNotifyService {
	
	/**
	 * 每周五通知所有用户填写周报
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void notifyUserSubimtWeeklyReportOnEveryWeekend(){
		
	}
	
	
	/**
	 * 通知用户查看填写的周报
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void notifyUserCheckWeeklyReport(){
		
	}
}
