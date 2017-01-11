package com.asiainfo.microNote.notify.pojo;

public class Message {

	private String userId;
	private String userName;
	private StringBuffer content;

	public Message() {
		super();
	}

	public Message(String userId,String userName, StringBuffer content) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.content = content;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

}