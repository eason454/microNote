package com.asiainfo.domain.entity.weeklyreport;

import com.asiainfo.domain.entity.microRecord.RecordAttachment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "report_record")
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

	/**
	 * <B>周报条目是计划(plan)状态为:</B> <br>
	 * new 新建未生效<br>
	 * planning 规划<br>
	 * canceled 已取消<br>
	 * confirmed 已确认<br>
	 * <br>
	 * <B>周报条目是工作(work)状态为:</B><br>
	 * new 新建未生效<br>
	 * worked 已完成工作<br>
	 */
	@Column(name = "state")
	private String state = "new";

	@Column(name="content", nullable=false)
	private String content;

	public List<RecordAttachment> getRecordAttachments() {
		return recordAttachments;
	}

	public void setRecordAttachments(List<RecordAttachment> recordAttachments) {
		this.recordAttachments = recordAttachments;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="id")
	private List<RecordAttachment> recordAttachments = new ArrayList<RecordAttachment>();
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

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

	
	public ReportRecord cloneReportRecord() throws CloneNotSupportedException{
		return (ReportRecord) this.clone();
	}
}
