package com.asiainfo.domain.entity.comments;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 评论主类
 * @author yi
 *
 */

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, targetEntity = CommentRecord.class)
	private List<CommentRecord> records ;
	
	@Column(name = "create_date")
	private long createDate;
	
	@Column(name = "comment_target_id")
	private long commentTargetId;
	
	@Column(name = "target_type")
	private String targetType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CommentRecord> getRecords() {
		return records;
	}

	public void setRecords(List<CommentRecord> records) {
		this.records = records;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getCommentTargetId() {
		return commentTargetId;
	}

	public void setCommentTargetId(long commentTargetId) {
		this.commentTargetId = commentTargetId;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
}
