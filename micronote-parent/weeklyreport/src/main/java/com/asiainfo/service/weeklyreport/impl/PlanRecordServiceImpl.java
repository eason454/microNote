package com.asiainfo.service.weeklyreport.impl;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.repository.weeklyreport.PlanRepository;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;
import com.asiainfo.util.CommonUtils;
import com.asiainfo.util.consts.CommonConst.PlanRecordState;
import com.asiainfo.util.consts.CommonConst.WorkRecordState;
import com.asiainfo.util.time.TimeUtil;
/**
 * 简单的计划操作实现
 * 
 * @author yi
 *
 */
@Service
public class PlanRecordServiceImpl implements IPlanRecordService {

	@Autowired
	private ReportRecordRepository reportRecordRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private WeeklyReportRepository weeklyReportRepository;
	
	@Override
	public boolean canelPlan(long planId) throws Exception{
		// TODO 修改计划状态到取消 (canceled)
		Plan plan = planRepository.findOne(planId);
		plan.setState(PlanRecordState.CANCELED);
		plan.setStartDate(System.currentTimeMillis());
		planRepository.save(plan);
		return true;
	}

	@Override
	public ReportRecord confirmePlan(long planId, long worklyReportId) throws Exception {
		 //  TODO 确认计划完成
		 //  查询现在要完成的计划 变成确认状态confirmed
		Plan plan = planRepository.findOne(planId);
		plan.setState(PlanRecordState.CONFIRMED);
		plan.setStartDate(System.currentTimeMillis());
		
		//TODO 复制一个条目为新的完成工作
		ReportRecord workRecord = new ReportRecord();
		workRecord.setCreateDate(System.currentTimeMillis());
		workRecord.setEndDate(System.currentTimeMillis());
		workRecord.setContent(plan.getContent());
		workRecord.setReportUserId(plan.getReportUserId());
		workRecord.setState(WorkRecordState.WORKED);

		List attachments = new ArrayList();
		attachments.addAll(plan.getRecordAttachments());
		workRecord.setRecordAttachments(attachments);
		//TODO 保存計劃工作和工作的修改
		plan = planRepository.save(plan);
//		workRecord = reportRecordRepository.save(workRecord);
		
		//添加周報外鍵關系
		WeeklyReport weeklyReport = weeklyReportRepository.findOne(worklyReportId);
		workRecord.setWeeklyReport(weeklyReport);
		workRecord = reportRecordRepository.save(workRecord);
		
//		List<ReportRecord> reportRecords = weeklyReport.getReportRecord();
//		reportRecords.add(workRecord);
//		weeklyReport.setReportRecord(reportRecords);
//		weeklyReportRepository.save(weeklyReport);
		
		
		return workRecord;
	}

	@Override
	public boolean delayPlan(long planRecordId) throws Exception{
		
		/**
		 * 2017-02-06 CHANGE BY YI
		 * TODO 不穩定需求，延遲計劃是順延一周
		 */
		Plan plan = planRepository.findOne(planRecordId);
		if(plan  == null){
			return false;
		}
		int week = TimeUtil.getWeekOfYear();
		week = week > 52 ? week + 1 : week;
		
		//取得下周五的日期
		long endDate = TimeUtil.getNextWeekEndDate();
		long startDate = TimeUtil.getNextWeekStartDate();
		
		plan.setWeek(week);
		plan.setStartDate(startDate);
		plan.setEndDate(endDate);
		planRepository.save(plan);
		
//		//判断一下现在的结束时间是否大于下周周末的时间
//		if(plan.getEndDate() < nextWeekEndDate){
//			//修改结束日期到下周五
//			plan.setEndDate(nextWeekEndDate);
//			planRepository.save(plan);
//		}
		
		return true;
	}
	
	@Override
	public Plan createWeeklyPlan(Plan plan, int week ) {
		/**
		 * 2017-02-06 CHANGE BY YI
		 * TODO 不穩定需求，修改計劃只能做一周
		 */
		//============================================================
		int nextWeek = week + 1;
		int year = DateTime.now().getYear();
		//處理跨年
		nextWeek = nextWeek > 52 ? 0 : nextWeek;
		year = nextWeek > 52 ? year + 1 : year;
		
		long startDate = TimeUtil.getWeekStartDateByWeek(year, nextWeek);
		long endDate = TimeUtil.getWeekEndDateByWeek(year, nextWeek);
		
		plan.setStartDate(startDate);
		plan.setEndDate(endDate);
		plan.setWeek(nextWeek);
		//===========================================================
		return planRepository.save(plan);
	}

	@Override
	public boolean modifyWeeklyPlan(Plan plan) {
		Plan oldPlan=planRepository.findOne(plan.getPlanId());
		String[] nullProperties=CommonUtils.getNullPropertyNames(plan);
		BeanUtils.copyProperties(plan,oldPlan,nullProperties);
		planRepository.save(oldPlan);
		return true;
	}

	@Override
	public boolean deleteWeeklyPlan(long planId) {
		boolean exists = planRepository.exists(planId);
		if(exists){
			planRepository.delete(planId);
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<Plan> queryNextWeekPlan(String userId) {
		long startTimeNextWeek=TimeUtil.getNextWeekStartDate();
		long endTimeNextWeek=TimeUtil.getNextWeekEndDate();
		return planRepository.findByReportUserIdAndEndDateGreaterThanAndStartDateLessThan(userId, startTimeNextWeek, endTimeNextWeek);
	}
	@Override
	public List<Plan> queryThisWeekPlan(String userId) {
		long startTimeThisWeek=TimeUtil.getStartDateThisWeek();
		long endTimeThisWeek= TimeUtil.getEndDateThisWeek();
		return planRepository.findByReportUserIdAndEndDateGreaterThanAndStartDateLessThan(userId,startTimeThisWeek,endTimeThisWeek);
	}
}
