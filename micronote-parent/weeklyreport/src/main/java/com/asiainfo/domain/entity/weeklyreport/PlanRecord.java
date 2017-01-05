package com.asiainfo.domain.entity.weeklyreport;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "plan_record")
@PrimaryKeyJoinColumn(referencedColumnName = "report_record_id")
public class PlanRecord extends ReportRecord {
	@Column(name = "start_date")
	private long startDate;

	@Column(name = "end_date")
	private long endDate;

	@Column(name = "state")
	private String state = "new";

	public PlanRecord(Long reportRecordId, long startDate, long endDate, long microRecordId) {
		super(reportRecordId, microRecordId);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public PlanRecord(Long reportRecordId, long microRecordId) {
		super(reportRecordId, microRecordId);
		this.startDate = System.currentTimeMillis();
		this.endDate = System.currentTimeMillis() + 86400000 * 7;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
}
