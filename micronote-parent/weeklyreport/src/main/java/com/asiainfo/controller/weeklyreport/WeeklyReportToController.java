package com.asiainfo.controller.weeklyreport;

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
	WeeklyReportToRepository weeklyReportToRepository;
	
	@RequestMapping(path = "/weeklyReportTos/setWeeklyReportTo", method = RequestMethod.POST)
	public String setWeeklyReportTo(@RequestBody WeeklyReportTo weeklyReportTo){
		WeeklyReportTo weeklyReportToExist = weeklyReportToRepository.findByReportUserId(weeklyReportTo.getReportUserId());
		/**
		 * 如果有汇报人就删除已有的关系
		 * 没有汇报人就添加汇报人
		 */
		if(weeklyReportToExist != null){
			weeklyReportToRepository.delete(weeklyReportToExist);
		}
		weeklyReportToRepository.save(weeklyReportTo);
		return "Ok";
	}
	
}
