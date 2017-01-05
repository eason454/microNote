package com.asiainfo.domain.entity.weeklyreport;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="finished_record")
@PrimaryKeyJoinColumn(referencedColumnName="reportRecordId")
public class FinishedRecord extends ReportRecord {
private String content;
@Column(name="create_date")
private Time createDate;
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Time getCreateDate() {
	return createDate;
}
public void setCreateDate(Time createDate) {
	this.createDate = createDate;
}
public FinishedRecord(Long reportRecordId, Time createDate) {
	super(reportRecordId, createDate);
}
public FinishedRecord(Long reportRecordId, Time createDate, String content, Time createDate2) {
	super(reportRecordId, createDate);
	this.content = content;
	createDate = createDate2;
}
}
