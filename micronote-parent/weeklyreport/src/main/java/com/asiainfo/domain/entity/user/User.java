package com.asiainfo.domain.entity.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="micro_record_user")
public class User {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "user_id")
	private String id;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_account")
	private String account;
	@Column(name="user_no")
	private String userNumber;

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	@Column(name = "create_date")
	private long createDate=System.currentTimeMillis();
	
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

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public User(String name, String account, String userNumber, long createDate) {
		this.name = name;
		this.account = account;
		this.userNumber = userNumber;
		this.createDate = createDate;
	}

	public User() {
	}

	public String getId() {
		return id;
	}
}
