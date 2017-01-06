package com.asiainfo.domain.entity.weeklyreport;

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
	private long createDate = System.currentTimeMillis();
	
	@Column(name = "micro_record_id")
	private long microRecordId;
	
	@Column(name = "start_date")
	private long startDate;

	@Column(name = "end_date")
	private long endDate;

	@Column(name = "state")
	private String state = "new";

	/**
	 * 1、plan
	 * 2、work
	 */
	@Column(name = "record_type")
	private String recordType;

	public ReportRecord(Long reportRecordId, long microRecordId) {
		super();
		this.reportRecordId = reportRecordId;
		this.microRecordId = microRecordId;
	}


	public Long getReportRecordId() {
		return reportRecordId;
	}

	public void setReportRecordId(Long reportRecordId) {
		this.reportRecordId = reportRecordId;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getMicroRecordId() {
		return microRecordId;
	}

	public void setMicroRecordId(long microRecordId) {
		this.microRecordId = microRecordId;
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


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	
}
