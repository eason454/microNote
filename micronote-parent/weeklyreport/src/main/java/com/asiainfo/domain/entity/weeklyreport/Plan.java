package com.asiainfo.domain.entity.weeklyreport;

import com.asiainfo.domain.entity.microRecord.RecordAttachment;
import com.asiainfo.util.consts.CommonConst.PlanRecordState;

import javax.persistence.*;
import java.util.List;

/**
 * Created by eason on 2017/1/9.
 */
@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "plan_id")
	private Long planId;

	@Column
	private String content;

	@Column(name = "start_date")
	private Long startDate;

	@Column(name = "end_date")
	private Long endDate;

	@Column(name = "report_user_id")
	private String reportUserId;
	
	@Column(name = "week")
	private int week;

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	/**
	 * 状态
	 */
	@Column
	private String state = PlanRecordState.PLANNING;

	@OneToMany(targetEntity = RecordAttachment.class)
	private List<RecordAttachment> recordAttachments;

	public Plan() {
	}

	public Plan(String content, Long startDate, Long endDate, String reportUserId, String state, String operState,
			List<RecordAttachment> recordAttachments) {
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reportUserId = reportUserId;
		this.state = state;
		this.recordAttachments = recordAttachments;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<RecordAttachment> getRecordAttachments() {
		return recordAttachments;
	}

	public void setRecordAttachments(List<RecordAttachment> recordAttachments) {
		this.recordAttachments = recordAttachments;
	}
}
