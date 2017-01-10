package com.asiainfo.domain.entity.comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 评论条目
 * @author yi
 */
@Entity
@Table(name="comment_record")
public class CommentRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_record_id")
	long id;
	
	String content;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
