package com.asiainfo.service.weeklyreport.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;
import com.asiainfo.util.consts.CommonConst.PlanRecordState;
import com.asiainfo.util.consts.CommonConst.WorkRecordState;
import com.asiainfo.util.time.TimeUtil;

/**
 * 简单的计划操作实现
 * 
 * @author yi
 *
 */
@Component
public class PlanRecordServiceImpl implements IPlanRecordService {

	@Autowired
	ReportRecordRepository reportRecordRepository;

	@Override
	public boolean canelPlan(long planRecordId) throws Exception{
		// TODO 修改计划状态到取消 (canceled)
		ReportRecord reportRecord = reportRecordRepository.findOne(planRecordId);
		reportRecord.setState(PlanRecordState.CANCELED);
		reportRecord.setStartDate(System.currentTimeMillis());
		reportRecordRepository.save(reportRecord);
		return true;
	}

	@Override
	public boolean confirmePlan(long planRecordId, long worklyReportId) throws Exception {
		 //  TODO 确认计划完成
		 //  查询现在要完成的计划 变成确认状态confirmed
		ReportRecord planRecord = reportRecordRepository.findOne(planRecordId);
		planRecord.setState(PlanRecordState.CONFIRMED);
		planRecord.setStartDate(System.currentTimeMillis());
		
		//TODO 复制一个条目为新的完成工作
		ReportRecord workRecord = planRecord.cloneReportRecord();
		workRecord.setReportRecordId(null);
//		workRecord.setRecordType(RecordType.WORK);
		workRecord.setState(WorkRecordState.WORKED);
		workRecord.setCreateDate(System.currentTimeMillis());
		
		//TODO 保存計劃工作和工作的修改
		reportRecordRepository.save(planRecord);
		workRecord = reportRecordRepository.save(workRecord);
			
		return true;
	}

	@Override
	public boolean delayPlan(long planRecordId) throws Exception{
		
		// TODO 延遲計劃
		ReportRecord planRecord = reportRecordRepository.findOne(planRecordId);
		//取得下周五的日期
		long nextWeekEndDate = TimeUtil.getNextWeekEndDate();
		//判断一下现在的结束时间是否大于下周五的时间
		if(planRecord.getEndDate() < nextWeekEndDate){
			//修改结束日期到下周五
			planRecord.setEndDate(nextWeekEndDate);
			reportRecordRepository.save(planRecord);
		}
		
		return true;
	}

}
