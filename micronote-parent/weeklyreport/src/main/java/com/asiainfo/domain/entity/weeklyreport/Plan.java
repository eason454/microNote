package com.asiainfo.domain.entity.weeklyreport;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.asiainfo.domain.entity.microRecord.RecordAttachment;
import com.asiainfo.util.consts.CommonConst.PlanRecordState;

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
	private long reportUserId;

	public long getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(long reportUserId) {
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

	public Plan(String content, Long startDate, Long endDate, long reportUserId, String state, String operState,
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
