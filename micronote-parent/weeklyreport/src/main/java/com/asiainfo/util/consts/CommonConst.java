package com.asiainfo.util.consts;

/**
 * Created by eason on 2017/1/6.
 */
public interface CommonConst {

	interface WeeklyReportReport {

		String SUBMMITED = "submmited";
		String READY = "ready";

	}

	interface NotificationType {
		String WEEKLY_REPORT_CHECK_NOTIFY  = "weekly_report_check_notify";
		String WEEKLY_REPORT_REPORT_NOTIFY  = "weekly_report_report_notify";
	}
	
	interface PlanRelationship{
		String DELAY = "delay";
		String CONFIRM = "confirm";
	}
	/**
	 * <B>周报条目是计划(plan)状态为:</B> <br>
	 * new 新建未生效<br>
	 * planning 规划<br>
	 * canceled 已取消<br>
	 * confirmed 已确认<br>
	 * @author yi
	 */
	interface PlanRecordState {
		String NEW = "new";
		String PLANNING = "planning";
		//延遲的計劃
		String DELAY = "delay";
		//被延遲的計劃
		String DELAYED = "delayed";
		String CANCELED = "canceled";
		String CONFIRMED = "confirmed";
	}
	
	/**
	 * <B>周报条目是工作(work)状态为:</B> <br>
	 * new 新建未生效<br>
	 * worked 完成工作<br>
	 * @author yi
	 */
	interface WorkRecordState {
		String NEW = "new";
		String WORKED = "worked";
	}
	
	/**
	 * <B>周报条目類型爲:</B> <br>
	 * work 完成工作<br>
	 * plan 工作計劃<br>
	 * @author yi
	 */
	interface RecordType {
		String PLAN = "plan";
		String WORK = "wrok";
	}
	interface KaraInfo{
		String responseTypeDefault="in_channel";
		String querySuccess="查询成功";
		String nextWeeklyInfo="下周计划";
		String thisWeeklyInfo="本周计划";
		String weeklyWork="本周工作成果";
		String recordElement="工作条目";
		String responseSuccessCode="000000";
		String userNotExists="对不起，设置失败，未查询到您设置到员工信息!";
		String setWeeklyReportResult="设置结果:";
		String SetWeeklyReportResultFail="设置失败，原因:%s";
		String saveRecordSuccess = "保存信息成功！";
		String SetWeeklyReportResultSuccess="设置成功，您的汇报对象为:%s,工号:%s";
        String clickUrlToViewWeeklyReport="请点击以下链接查看周报:";
        String weeklyReportDetail="周报详情";
        String urlSplit="|";
	}

}
