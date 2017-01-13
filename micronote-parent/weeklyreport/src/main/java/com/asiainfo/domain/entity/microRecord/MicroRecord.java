package com.asiainfo.domain.entity.microRecord;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="micro_record")
public class MicroRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="micro_record_id")
	private long id;
	
	@Column(name="create_date", nullable=false)
	private long createDate = System.currentTimeMillis();
	
	/**
	 * 分为以下应用类型: </br>
	 * 1、<B>weekly_report</B> 周报记录 </br>
	 * 2、<B>record</B> 自由记录 </br>
	 */
	@Column(name="record_resource")
	private String recordResource = "record";
	
	@Column(name="state", nullable=false)
	private String state = "new";
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="id")
	private Set<RecordAttachment> recordAttachments = new HashSet<RecordAttachment>();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Set<RecordAttachment> getRecordAttachments() {
		return recordAttachments;
	}

	public void setRecordAttachments(Set<RecordAttachment> recordAttachments) {
		this.recordAttachments = recordAttachments;
	}

	public String getRecordResource() {
		return recordResource;
	}

	public void setRecordResource(String recordResource) {
		this.recordResource = recordResource;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	
}
