package com.asiainfo.microNote.user.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.asiainfo.microNote.notify.pojo.NotifyUser;

@FeignClient("weeklyreport")
public interface IUserService {

	
	List<NotifyUser> getUserByPageAndSort(int page, int size, String sort);
}
