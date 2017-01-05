package com.asiainfo.domain.entity.weeklyreport;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "finished_record")
@PrimaryKeyJoinColumn(referencedColumnName = "report_record_id")
public class FinishedRecord extends ReportRecord {
	
	public FinishedRecord(Long reportRecordId, long microRecordId) {
		super(reportRecordId, microRecordId);
	}
	

}
