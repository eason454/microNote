package com.asiainfo.controller.weeklyreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class PlanController {
	@Autowired
	IPlanRecordService planRecordService;

	@PostMapping(path = "/createWeeklyPlan")
	public ReportRecord createWeeklyPlan(@RequestBody ReportRecord reportRecord,
			@RequestHeader(value = "userId") long userId) {
		return null;
	}

	@PostMapping(path = "/modifyWeeklyPlan")
	public boolean modifyWeeklyPlan(@RequestBody ReportRecord reportRecord) {
		return true;
	}

	@DeleteMapping(path = "/deleteWeeklyPlan")
	public boolean deleteWeeklyPlan(@RequestBody ReportRecord reportRecord) {
		return true;
	}

	@PostMapping(path = "/cancelPlan")
	public boolean cancelPlan(@RequestParam(value = "planId") long planId) {
		try {
			return planRecordService.canelPlan(planId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@PostMapping(path = "/confirmedPlan")
	public boolean confirmedPlan(@RequestParam(value = "planId") long planId,
			@RequestParam(value = "planId") long worklyReportId) {
		try {
			return planRecordService.confirmePlan(planId, worklyReportId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@PostMapping(path = "/delayPlan")
	public boolean delayPlan(@RequestParam(value = "planId") long planId) {
		try {
			return planRecordService.delayPlan(planId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
