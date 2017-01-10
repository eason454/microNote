package com.asiainfo.microNote.notify.pojo;

public class Message {

	private String userId;
	private String content;
	private String notifyType;

	public Message() {
		super();
	}

	public Message(String users, String content, String notifyType) {
		super();
//		this.users = users.split(" ");
		this.content = content;
		this.notifyType = notifyType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}