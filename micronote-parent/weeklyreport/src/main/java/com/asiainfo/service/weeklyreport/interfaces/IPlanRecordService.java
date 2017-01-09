package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.Plan;

/**
 * 处理工作计划条目的业务逻辑
 * @author yi
 */
public interface IPlanRecordService{
	
	/**
	 * 取消计划
	 * @param planRecordId
	 * @return
	 */
	public boolean canelPlan(long planRecordId) throws Exception;
	
	/**
	 * 确认完成计划
	 * @param planRecordId
	 * @return
	 */
	public boolean confirmePlan(long planRecordId, long worklyReportId) throws Exception;
	
	/**
	 * 延迟计划
	 * @param planRecordId
	 * @return
	 */
	public boolean delayPlan(long planRecordId) throws Exception;

	/**
	 * 创建计划
	 * @param plan
	 * @return
	 */
	public Plan createWeeklyPlan(Plan plan);

	/**
	 * 修改计划
	 * @param plan
	 * @return
	 */

	public boolean modifyWeeklyPlan(Plan plan);

	/**
	 * 删除计划
	 * @param planId
	 * @return
	 */
	public boolean deleteWeeklyPlan(long planId);
	
}
