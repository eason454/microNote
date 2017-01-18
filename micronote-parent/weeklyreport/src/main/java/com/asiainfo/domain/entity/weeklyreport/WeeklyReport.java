package com.asiainfo.domain.entity.weeklyreport;

import com.asiainfo.util.consts.CommonConst;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.*;

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
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "weeklyReportId")
public class WeeklyReport {
	
	@Id
	@Column(name="weekly_report_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long weeklyReportId;
	
	/**
	 * 1、submitted 已提交<br>
	 * 2、ready 新的周报<br>
	 */
	@Column(name="state")
	private String state=CommonConst.WeeklyReportReport.READY;

	public Long getWeeklyReportId() {
		return weeklyReportId;
	}

	/**
	 * 汇报人Id
	 */
	@Column(name="report_user_id")
	private String reportUserId;
	
	/**
	 * 审核人Id
	 */
	@Column(name="auditing_user_id",nullable = true)
	private String auditingUserId;
	
	@Column(name="create_date")
	private long createDate=System.currentTimeMillis();
	
	/**
	 * 第几周提交的周报
	 */
	@Column(name="weekly")
	private int weekly = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

	public List<ReportRecord> getReportRecord() {
		return reportRecord;
	}
	public void setReportRecord(List<ReportRecord> reportRecord) {
		this.reportRecord = reportRecord;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "weeklyReport")
	private List<ReportRecord> reportRecord = new ArrayList<ReportRecord>();


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getAuditingUserId() {
		return auditingUserId;
	}

	public void setAuditingUserId(String auditingUserId) {
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


	public WeeklyReport(String reportUserId) {
		this.reportUserId = reportUserId;
		this.state= CommonConst.WeeklyReportReport.READY;
	}

	public WeeklyReport() {
	}
}
