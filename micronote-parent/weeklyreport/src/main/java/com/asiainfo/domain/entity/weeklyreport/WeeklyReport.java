package com.asiainfo.domain.entity.weeklyreport;

import com.asiainfo.util.consts.CommonConst;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "weekly_report")
public class WeeklyReport {
	
	@Id
	@Column(name="weekly_report_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 1、submitted 已提交<br>
	 * 2、onChange 新的周报<br>
	 */
	@Column(name="state")
	private String state;

	/**
	 * 汇报人Id
	 */
	@Column(name="report_user_id")
	private long reportUserId;
	
	/**
	 * 审核人Id
	 */
	@Column(name="auditing_user_id",nullable = true)
	private Long auditingUserId;
	
	@Column(name="create_date")
	private long createDate=System.currentTimeMillis();
	
	/**
	 * 第几周提交的周报
	 */
	@Column(name="weekly")
	private int weekly = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="reportRecordId")
	private Set<ReportRecord> reportRecord = new HashSet<ReportRecord>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(long reportUserId) {
		this.reportUserId = reportUserId;
	}

	public Long getAuditingUserId() {
		return auditingUserId;
	}

	public void setAuditingUserId(Long auditingUserId) {
		this.auditingUserId = auditingUserId;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public int getWeekly() {
		return weekly;
	}

	public void setWeekly(int weekly) {
		this.weekly = weekly;
	}

	public Set<ReportRecord> getReportRecord() {
		return reportRecord;
	}

	public void setReportRecord(Set<ReportRecord> reportRecord) {
		this.reportRecord = reportRecord;
	}

	public WeeklyReport(long reportUserId) {
		this.reportUserId = reportUserId;
		this.state= CommonConst.WeeklyReportReport.READY;
	}

	public WeeklyReport() {
	}
}
