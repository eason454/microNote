package com.asiainfo.domain.entity.weeklyreport;

import com.asiainfo.domain.entity.microRecord.RecordAttachment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private long createDate;
	
	@Column(name = "start_date")
	private long startDate;

	@Column(name = "end_date")
	private long endDate;

	@Column(name = "state")
	private String state = "new";

	@Column(name="content", nullable=false)
	private String content;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@OneToOne
	@JoinColumn(name="plan_id")
	private Plan plan;

    public Long getReportRecordId() {
        return reportRecordId;
    }

    public void setReportRecordId(Long reportRecordId) {
        this.reportRecordId = reportRecordId;
    }

    @Column(name="report_user_id")
	private String reportUserId;
//    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH},optional = false,fetch = FetchType.EAGER)
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name="weekly_report_id")
    @JsonIgnoreProperties("reportRecord")
	private WeeklyReport weeklyReport;
    public WeeklyReport getWeeklyReport() {
        return weeklyReport;
    }

    public void setWeeklyReport(WeeklyReport weeklyReport) {
        this.weeklyReport = weeklyReport;
    }

    public String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public List<RecordAttachment> getRecordAttachments() {
		return recordAttachments;
	}

	public void setRecordAttachments(List<RecordAttachment> recordAttachments) {
		this.recordAttachments = recordAttachments;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, targetEntity = RecordAttachment.class)
	private List<RecordAttachment> recordAttachments = new ArrayList<RecordAttachment>();
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 2、work
	 */
	@Column(name = "record_type")
	private String recordType;


	public ReportRecord(long startDate, long endDate, String content, String recordType) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.content = content;
	}


	public String getRecordType() {
		return recordType;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
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

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public ReportRecord() {
	}

	public ReportRecord(long createDate, long startDate, long endDate, String state, String content, String reportUserId, List<RecordAttachment> recordAttachments, String recordType) {
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
		this.content = content;
		this.reportUserId = reportUserId;
		this.recordAttachments = recordAttachments;
		this.recordType = recordType;
	}

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	public ReportRecord cloneReportRecord() throws CloneNotSupportedException{
		return (ReportRecord) super.clone();
	}
}
