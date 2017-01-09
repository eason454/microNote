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
	private long reportUserId;
	
	/**
	 * 审核人Id
	 */
	@Column(name="auditing_user_id")
	private long auditingUserId;

	public long getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(long reportUserId) {
		this.reportUserId = reportUserId;
	}

	public long getAuditingUserId() {
		return auditingUserId;
	}

	public void setAuditingUserId(long auditingUserId) {
		this.auditingUserId = auditingUserId;
	}
	
}
