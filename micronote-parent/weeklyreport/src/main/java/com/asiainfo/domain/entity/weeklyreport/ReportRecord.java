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

	
}
