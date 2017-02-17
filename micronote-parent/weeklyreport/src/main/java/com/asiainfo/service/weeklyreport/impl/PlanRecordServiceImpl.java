package com.asiainfo.service.weeklyreport.impl;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.domain.entity.microRecord.RecordAttachment;
import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.PlanRel;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.repository.weeklyreport.PlanRelRepository;
import com.asiainfo.repository.weeklyreport.PlanRepository;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;
import com.asiainfo.util.CommonUtils;
import com.asiainfo.util.consts.CommonConst;
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
	@Autowired
	private PlanRelRepository planRelRepository;
	
	@Override
	public boolean canelPlan(long planId, String reason) throws Exception{
		// TODO 修改计划状态到取消 (canceled)
		Plan plan = planRepository.findOne(planId);
		plan.setState(PlanRecordState.CANCELED);
		plan.setCancelReason(reason);
		plan.setStartDate(System.currentTimeMillis());
		planRepository.save(plan);
		return true;
	}
	
	@Override
	public boolean recoverPlan(long planId) throws Exception{
		Plan plan = planRepository.findOne(planId);
		plan.setState(PlanRecordState.PLANNING);
		plan.setCancelReason(null);
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

		List<RecordAttachment> attachments = new ArrayList<RecordAttachment>();
		attachments.addAll(plan.getRecordAttachments());
		workRecord.setRecordAttachments(attachments);
		//TODO 保存計劃工作和工作的修改
		plan = planRepository.save(plan);
		
		//添加周報外鍵關系
		WeeklyReport weeklyReport = weeklyReportRepository.findOne(worklyReportId);
		workRecord.setWeeklyReport(weeklyReport);
		workRecord = reportRecordRepository.save(workRecord);
		
		
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
			throw new Exception("NO_PLAN_FOUND");
		}
		
		Plan delayPlan = new Plan();
		
		BeanUtils.copyProperties(plan, delayPlan, "planId");
		
		int week = TimeUtil.getWeekOfYear();
		week = week > 52 ?  0 :week + 1;
		
		//取得下周五的日期
		long endDate = TimeUtil.getNextWeekEndDate();
		long startDate = TimeUtil.getNextWeekStartDate();
		//保存延遲計劃
		
		List<RecordAttachment> attachments = new ArrayList<RecordAttachment>();
		attachments.addAll(plan.getRecordAttachments());
		
		delayPlan.setRecordAttachments(attachments);
		delayPlan.setState(CommonConst.PlanRecordState.DELAY);
		delayPlan.setWeek(week);
		delayPlan.setStartDate(startDate);
		delayPlan.setEndDate(endDate);
		delayPlan = planRepository.save(delayPlan);
		//修改原始計劃被延遲
		plan.setState(CommonConst.PlanRecordState.DELAYED);
		planRepository.save(plan);
		
		//添加延遲關系
		PlanRel planRel = new PlanRel();
		planRel.setPlanId(plan.getPlanId());
		planRel.setRelatedPlanId(delayPlan.getPlanId());
		planRel.setRelationship(CommonConst.PlanRelationship.DELAY);
		planRelRepository.save(planRel);
		
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
			//刪除有計劃關系
			List<PlanRel> planRels = planRelRepository.findByRelatedPlanId(planId);
			for(PlanRel planRel : planRels){
				Plan plan = planRepository.findOne(planRel.getPlanId());
				plan.setState(CommonConst.PlanRecordState.PLANNING);
				planRepository.save(plan);
			}
			planRelRepository.delete(planRels);
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
