package com.asiainfo.domain;




public class User {
	
	private String id;
	private String name;
	private String account;
	private String userNumber;
	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}



	public User(String name, String account, String userNumber) {
		this.name = name;
		this.account = account;
		this.userNumber = userNumber;
	}

	public User() {
	}

	public String getId() {
		return id;
	}
}
