package com.asiainfo.microNote.notify.pojo;

/**
 * 通知信息
 * @author yi
 *
 */
public class Message {

	private String userId;
	private String userName;
	private StringBuffer content;
	private String notifyType;
	
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

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

}