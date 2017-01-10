package com.asiainfo.microNote.notify.user.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.asiainfo.microNote.notify.pojo.NotifyUser;

@FeignClient("weeklyreport")
public interface IUserService {

	@GetMapping("/")
	List<NotifyUser> getUserByPageAndSort(int page, int size, String sort);
}
