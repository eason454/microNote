package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.domain.kara.KaraRequestObject;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportToService;
import com.asiainfo.service.weeklyreport.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;
import com.asiainfo.repository.weeklyreport.WeeklyReportToRepository;

@RestController
public class WeeklyReportToController {
	
	@Autowired
	private IWeeklyReportToService weeklyReportToService;
	@Autowired
	private UserService userService;
	@RequestMapping(path = "/setWeeklyReportTo", method = RequestMethod.POST)
	public String setWeeklyReportTo(@RequestBody KaraRequestObject request){
		String userId=request.getUserId();
		String auditUserId=request.getText();
		WeeklyReportTo weeklyReportTo = weeklyReportToService.findByReportUserId(userId);
		weeklyReportTo.setAuditingUserId(auditUserId);
		//查询汇报对象是否已经在微记用户中存在
		User user=userService.queryUserById(auditUserId);
		if(user==null){
			//调用kara查询用户信息
		}
		boolean flag=weeklyReportToService.saveWeeklyReportTo(weeklyReportTo);
		if(flag){

		}
		return "OK";
	}
}
