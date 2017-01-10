package com.asiainfo.microNote.notify.service.weeklyReport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.asiainfo.microNote.notify.adapter.NotifyAdapter;
import com.asiainfo.microNote.notify.pojo.Message;
import com.asiainfo.microNote.notify.pojo.NotifyUser;
import com.asiainfo.microNote.user.service.IUserService;

/**
 * 周报通知服务
 * @author yi
 */
@Service
public class WeeklyReportOnTimeNotifyService {
	
	@Autowired
	IUserService userService;
	@Autowired
	NotifyAdapter notifyAdapter;
	
	@Value("weeklyReport.user.noitfy.onEveryWeek")
	String notifyContentEveryWeek;
	/**
	 * 每周五通知所有用户填写周报
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void notifyUserSubimtWeeklyReportOnEveryWeekend(){
		int page = 0;
		
		List<NotifyUser> users =  userService.getUserByPageAndSort(page, 2, "id");
		
		for(NotifyUser user : users){
			StringBuffer content = new StringBuffer(notifyContentEveryWeek);
		
			notifyAdapter.notify(new Message(user.getId() + "", user.getName() + "", content));
		}
	}
	
	
	/**
	 * 通知用户查看填写的周报
	 */
	@Scheduled(cron = "0/5 * * * * ?")
	public void notifyUserCheckWeeklyReport(){
		//TODO 第一版全部实时推送需要在第二版实现统一推送
	}
}
