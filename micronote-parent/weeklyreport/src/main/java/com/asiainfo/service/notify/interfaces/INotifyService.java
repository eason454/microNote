package com.asiainfo.service.notify.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.service.notify.impl.Message;

@FeignClient("notifyServer")
public interface INotifyService {
	
	@RequestMapping(path = "/notify/notify", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@RequestBody Message message);
	
}
