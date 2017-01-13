package com.asiainfo.microNote.comments.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 聚合用户信息提供给页面查询
 * 
 * @author yi
 *
 */
public class CommentRecordInfo {

	private long id;
	private String userId;
	private String userName;
	private String content;
	private long createDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
