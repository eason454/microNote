package com.asiainfo.service.notify.impl;


/**
 * 提交周报发送通知消息格式
 * @author yi
 */
public class WeeklyReportSubmitReportMessage {

	private NotifyUser notifyUser;

	private WeeklyReportInfo WeeklyReport;

	public NotifyUser getNotifyUser() {
		return notifyUser;
	}

	public void setNotifyUser(NotifyUser notifyUser) {
		this.notifyUser = notifyUser;
	}

	public WeeklyReportInfo getWeeklyReport() {
		return WeeklyReport;
	}

	public void setWeeklyReport(WeeklyReportInfo weeklyReport) {
		WeeklyReport = weeklyReport;
	}

	/**
	 * 通知需要的周报信息
	 * @author yi
	 */
	public class WeeklyReportInfo {
		String reportUserId;
		// 提交人
		String reportUserName;
		// 提交的周
		int week;
		// 提交时间
		long submitTime;

		public String getReportUserName() {
			return reportUserName;
		}

		public void setReportUserName(String reportUserName) {
			this.reportUserName = reportUserName;
		}

		public int getWeek() {
			return week;
		}

		public void setWeek(int week) {
			this.week = week;
		}

		public long getSubmitTime() {
			return submitTime;
		}

		public void setSubmitTime(long submitTime) {
			this.submitTime = submitTime;
		}

		public String getReportUserId() {
			return reportUserId;
		}

		public void setReportUserId(String reportUserId) {
			this.reportUserId = reportUserId;
		}
		
		
	}

}
