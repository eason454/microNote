package com.asiainfo.controller.notify;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.service.notify.Message;
import com.asiainfo.service.notify.NotifyServer;


@Controller
public class NotifyController {

	@RequestMapping(path = "/notifyOnTime", method = RequestMethod.POST)
	@ResponseBody
	public String notifyOnTime(@RequestBody Message message) {
		NotifyServer.notifyOnTime(message);
		return "OK";
	}

	@RequestMapping(path = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@RequestBody Message message) {
		
		NotifyServer.notify(message);
		return "OK";
	}
}
