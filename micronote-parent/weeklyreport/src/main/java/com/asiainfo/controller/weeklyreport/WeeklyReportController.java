package com.asiainfo.controller.weeklyreport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.asiainfo.domain.kara.KaraRequestObject;
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
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportService;

/**
 * Created by eason on 2017/1/6.
 */
@RestController
public class WeeklyReportController {
	private Log logger = LogFactory.getLog(WeeklyReportController.class);
	@Autowired
	private IWeeklyReportService weeklyReportService;

	@RequestMapping(path = "/createWeeklyReport", method = RequestMethod.POST)
	public WeeklyReport createWeeklyReport(@RequestParam("userId") String reportUserId) {
		return weeklyReportService.createWeeklyReport(reportUserId);
	}

	@RequestMapping(path = "/queryWeeklyRecord", method = RequestMethod.POST)
	public KaraMessage queryReportRecrodsByWeek(@RequestBody KaraRequestObject request){
		/*
		2017-1-12:Create by Zhaojl
		1、解析传入的对象获取到需要的值
		2、调用Repository层的方法查询数据
		3、组合成返回格式并返回
		 */
		//获取数据
		String userId=request.getUserId();
		long currentTime = Long.valueOf(request.getText());

		//定义返回结构
		KaraField field=new KaraField();
		List<KaraField> list=new ArrayList<KaraField>();

		//调用方法
		List<ReportRecord> reportRecords = weeklyReportService.findByUserIdAndTime(userId,currentTime);

		//将数据写入到返回结构中
		for (ReportRecord reportRecord : reportRecords) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(reportRecord.getCreateDate());
			field.setTitle(calendar.getTime().toString() + ":");
			field.setValue(reportRecord.getContent());
			list.add(field);
		}
		KaraMessage karaMessage = MessageConstructor.constructMessageWithFields(CommonConst.KaraInfo.weeklyWork,list);
		return karaMessage;
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
