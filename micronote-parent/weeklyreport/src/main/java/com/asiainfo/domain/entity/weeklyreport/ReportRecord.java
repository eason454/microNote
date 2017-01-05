package com.asiainfo.domain.entity.weeklyreport;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "report_record")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReportRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "report_record_id")
	private Long reportRecordId;
	@Column(name = "create_date")
	private Time createDate;

	public ReportRecord(Long reportRecordId, Time createDate) {
		super();
		this.reportRecordId = reportRecordId;
		this.createDate = createDate;
	}

	public ReportRecord() {
		super();
	}

	public Long getReportRecordId() {
		return reportRecordId;
	}

	public void setReportRecordId(Long reportRecordId) {
		this.reportRecordId = reportRecordId;
	}

	public Time getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Time createDate) {
		this.createDate = createDate;
	}

}
