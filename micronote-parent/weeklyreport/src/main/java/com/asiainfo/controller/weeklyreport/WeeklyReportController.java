package com.asiainfo.controller.weeklyreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;

/**
 * Created by eason on 2017/1/6.
 */
@RestController
public class WeeklyReportController {
	@Autowired
	private WeeklyReportService weeklyReportService;

	@RequestMapping(path = "/createWeeklyReport", method = RequestMethod.POST)
	public WeeklyReport createWeeklyReport(@RequestParam("userId") long reportUserId) {
		return weeklyReportService.createWeeklyReport(reportUserId);
	}

	@RequestMapping(path = "/queryReportRecords", method = RequestMethod.GET)
	public List<ReportRecord> queryReportRecrodsByWeek(Long currentTime) {
		return weeklyReportService.findByCreateDateBetween(currentTime);
	}

	@RequestMapping(path = "/queryPlans", method = RequestMethod.GET)
	public List<Plan> queryReportPlansByWeek(Long currentTime) {
		return null;
	}

	@RequestMapping(path = "/submitWeeklyReport", method = RequestMethod.POST)
	public boolean submitWeeklyReport(@RequestParam("weeklyReportId") long weeklyReportId) throws Exception {
		return weeklyReportService.submitWeeklyReport(weeklyReportId);
	}
}
