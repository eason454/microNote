package com.asiainfo.microNote.notify.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.microNote.notify.pojo.message.weeklyReport.WeeklyReportSubmitReportMessage;
import com.asiainfo.microNote.notify.service.weeklyReport.WeeklyReportRealTimeNotifyService;

/**
 * 
 * @author yi
 *
 */
@RestController
public class NotifyController {

	@RequestMapping(path = "/notifyAuditing", method = RequestMethod.POST)
	@ResponseBody
	public String notify(@RequestBody WeeklyReportSubmitReportMessage message) {
		WeeklyReportRealTimeNotifyService.notifyAuditing(message);
		return "OK";
	}

}
