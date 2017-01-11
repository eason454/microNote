package com.asiainfo.controller.weeklyreport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.asiainfo.domain.response.KaraField;
import com.asiainfo.domain.response.KaraMessage;
import com.asiainfo.util.kara.MessageConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;

/**
 * Created by eason on 2017/1/6.
 */
@RestController
public class WeeklyReportController {
	private Log logger = LogFactory.getLog(WeeklyReportController.class);
	@Autowired
	private WeeklyReportService weeklyReportService;

	@RequestMapping(path = "/createWeeklyReport", method = RequestMethod.POST)
	public WeeklyReport createWeeklyReport(@RequestParam("userId") String reportUserId) {
		return weeklyReportService.createWeeklyReport(reportUserId);
	}

	@PostMapping(path = "/queryReportRecords")
	public KaraMessage queryReportRecrodsByWeek(@RequestParam Map<String,String> param,@RequestHeader Map<String,String> header,@RequestBody Map<String,String> body){//@RequestHeader(value = "userId") long userId, , @RequestBody String body
        logger.error("header:" + header.toString());
        logger.error("param:"+param.toString());
        logger.error("body:"+body.toString());
		List<ReportRecord> reportRecords = weeklyReportService.findByCreateDateBetween(Long.parseLong(body.get("currentTime")));
        StringBuffer content=new StringBuffer();
		for (ReportRecord reportRecord : reportRecords) {
			content.append(reportRecord.getContent());
		}

        KaraField field=new KaraField();
        field.setTitle("本周工作成果");
        field.setValue(content.toString());
        List<KaraField> list=new ArrayList<KaraField>();
        list.add(field);
        KaraMessage message= MessageConstructor.constructMessageWithFields(list);
        return message;
    }

	@RequestMapping(path = "/queryPlans", method = RequestMethod.GET)
	public List<Plan> queryReportPlansByWeek(Long currentTime) {
		return null;
	}

	@RequestMapping(path = "/submitWeeklyReport", method = RequestMethod.POST)
	public boolean submitWeeklyReport(@RequestParam("weeklyReportId") long weeklyReportId) throws Exception {
		 return weeklyReportService.submitWeeklyReport(weeklyReportId);
	}
}
