package com.asiainfo.microNote.notify.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.microNote.notify.pojo.Message;
import com.asiainfo.microNote.notify.service.RealTimeNotifyService;


@RestController
public class NotifyController {

	@RequestMapping(path = "/notifyOnTime", method = RequestMethod.POST)
	@ResponseBody
	public String notifyOnTime(@RequestBody Message message) {
		RealTimeNotifyService.notifyOnTime(message);
		return "OK";
	}

	@RequestMapping(path = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@RequestBody Message message) {
		RealTimeNotifyService.notify(message);
		return "OK";
	}
}
