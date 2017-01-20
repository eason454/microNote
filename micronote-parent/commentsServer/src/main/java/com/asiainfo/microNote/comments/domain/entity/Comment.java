package com.asiainfo.microNote.comments.domain.entity;

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
	
	/**
	 * mappedBy 被关联的表的列
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "comment", orphanRemoval=true)
	private Set<CommentRecord> records ;
	
	@Column(name = "create_date")
	private long createDate;
	
	@Column(name = "comment_target_id")
	private long commentTargetId;
	
	@Column(name = "target_type")
	private String targetType;

	public Comment(long commentTargetId, String targetType){
		super();
		this.commentTargetId = commentTargetId;
		this.targetType = targetType;
		this.createDate = System.currentTimeMillis();
	}
	
	public Comment(){
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<CommentRecord> getRecords() {
		if(this.records == null)
			this.records = new HashSet<CommentRecord>();
		return records;
	}

	public void setRecords(Set<CommentRecord> records) {
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
