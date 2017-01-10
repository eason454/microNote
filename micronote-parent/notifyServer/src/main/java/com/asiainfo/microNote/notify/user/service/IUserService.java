package com.asiainfo.microNote.notify.user.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asiainfo.microNote.notify.pojo.NotifyUser;

@FeignClient("weeklyreport")
public interface IUserService {

	@RequestMapping(path = "/microNote/queryusersbypages", method = RequestMethod.GET)
	List<NotifyUser> getUserByPageAndSort(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("sort") String sort);
}
