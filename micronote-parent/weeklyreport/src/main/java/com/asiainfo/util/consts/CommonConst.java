package com.asiainfo.util.consts;

/**
 * Created by eason on 2017/1/6.
 */
public interface CommonConst {

	public interface WeeklyReportReport {

		public static final String SUBMMITED = "submmited";
		public static final String READY = "ready";

	}

	public interface NotificationType {
		public static final String WEEKLY_REPORT_CHECK_NOTIFY  = "weekly_report_check_notify";
		public static final String WEEKLY_REPORT_REPORT_NOTIFY  = "weekly_report_report_notify";
	}
	
	/**
	 * <B>周报条目是计划(plan)状态为:</B> <br>
	 * new 新建未生效<br>
	 * planning 规划<br>
	 * canceled 已取消<br>
	 * confirmed 已确认<br>
	 * @author yi
	 */
	public static interface PlanRecordState {
		public static final String NEW = "new";
		public static final String PLANNING = "planning";
		public static final String CANCELED = "canceled";
		public static final String CONFIRMED = "confirmed";
	}
	
	/**
	 * <B>周报条目是工作(work)状态为:</B> <br>
	 * new 新建未生效<br>
	 * worked 完成工作<br>
	 * @author yi
	 */
	public static interface WorkRecordState {
		public static final String NEW = "new";
		public static final String WORKED = "worked";
	}
	
	/**
	 * <B>周报条目類型爲:</B> <br>
	 * work 完成工作<br>
	 * plan 工作計劃<br>
	 * @author yi
	 */
	public static interface RecordType {
		public static final String PLAN = "plan";
		public static final String WORK = "wrok";
	}
	public static interface KaraInfo{
		public static final String responseTypeDefault="in_channel";
		public static final String querySuccess="查询成功";
		public static final String nextWeeklyInfo="下周计划";
		public static final String thisWeeklyInfo="本周计划";
		String weeklyWork="本周工作成果";

	}

}
