package com.asiainfo.controller.notify;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.service.notify.impl.Message;
import com.asiainfo.service.notify.impl.NotifyService;


@RestController
public class NotifyController {

	@RequestMapping(path = "/notifyOnTime", method = RequestMethod.POST)
	@ResponseBody
	public String notifyOnTime(@RequestBody Message message) {
		NotifyService.notifyOnTime(message);
		return "OK";
	}

	@RequestMapping(path = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@RequestBody Message message) {
		NotifyService.notify(message);
		return "OK";
	}
}
