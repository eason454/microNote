package com.asiainfo.domain.entity.weeklyreport;

import javax.persistence.*;

@Entity
@Table(name="weekly_report_to")
public class WeeklyReportTo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "weekly_report_to_id")
	long id;
	/**
	 * 汇报人Id
	 */
	@Column(name="report_user_id")
	private String reportUserId;
	
	/**
	 * 审核人Id
	 */
	@Column(name="auditing_user_id")
	private String auditingUserId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public WeeklyReportTo(String reportUserId, String auditingUserId) {
		this.reportUserId = reportUserId;
		this.auditingUserId = auditingUserId;
	}
}
