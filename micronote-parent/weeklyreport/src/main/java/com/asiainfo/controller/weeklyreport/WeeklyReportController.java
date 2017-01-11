package com.asiainfo.controller.weeklyreport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.asiainfo.domain.kara.response.KaraField;
import com.asiainfo.domain.kara.response.KaraMessage;
import com.asiainfo.util.consts.CommonConst;
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
		//created by zhaojl 2017-1-11
        logger.info("header:" + header.toString());
        logger.info("param:"+param.toString());
        logger.info("body:"+body.toString());
		String userId = body.get("userId");
		long currentTime = Long.parseLong(body.get("currentTime"));
		//获取数据
		List<ReportRecord> reportRecords = weeklyReportService.findByCreateDateBetween(userId,currentTime);
		//构造返回结构
        StringBuffer content=new StringBuffer();
		KaraField field=new KaraField();
		List<KaraField> list=new ArrayList<KaraField>();
		//将数据写入到返回结构中
		for (ReportRecord reportRecord : reportRecords) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(reportRecord.getCreateDate());
			field.setTitle(calendar.getTime().toString() + ":");
			field.setValue(reportRecord.getContent());
			list.add(field);
		}
		//调公有方法生成返回对象
        KaraMessage message= MessageConstructor.constructMessageWithFields(CommonConst.KaraInfo.weeklyWork,list);
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
