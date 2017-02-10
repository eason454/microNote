package com.asiainfo.microNote.notify.controller;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportSubmitReportMessage;
import com.asiainfo.microNote.notify.service.weeklyReport.WeeklyReportSubmitNotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * @author yi
 *
 */
@RestController
public class NotifyController {

	@Autowired
	AmqpTemplate amqpTemplate;
	
	@RequestMapping(path = "/notifyAuditing", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@RequestBody WeeklyReportSubmitReportMessage message) {
		WeeklyReportSubmitNotifyService.notifyAuditing(message);
		return "OK";
	}
	
	@RequestMapping(path = "/notifySubmit", method = RequestMethod.POST)
	@ResponseBody
	public String notifySubmit(@RequestBody WeeklyReportSubmitReportMessage message) throws AmqpException, JsonProcessingException {
//		WeeklyReportSubmitNotifyService.notifyAuditing(message);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		amqpTemplate.convertAndSend("submitExchange", "route1", mapper.writeValueAsString(message));
		return "OK";
	}

}
