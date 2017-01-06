package com.asiainfo.domain.entity.notification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notify_log")
public class NotifyLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notify_log_id")
	long id;
	
	@Column
	String description;
	
	@Column
	String state;
	
	@Column(name="notify_date")
	long notifyDate;
	
	
}
