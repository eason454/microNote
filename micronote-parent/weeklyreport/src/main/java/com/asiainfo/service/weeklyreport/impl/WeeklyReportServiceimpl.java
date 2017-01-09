package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import java.util.Calendar;
import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
@Service
public class WeeklyReportServiceimpl implements WeeklyReportService {
	@Autowired
	private ReportRecordRepository reportRecordRepository;
	@Autowired
	private WeeklyReportRepository weeklyReportRepository;
	@Override
	public WeeklyReport createWeeklyReport(long reportUserId) {
		// 构造WeeklyReport对象
		WeeklyReport weeklyReport = new WeeklyReport(reportUserId);
		return weeklyReportRepository.save(weeklyReport);
	}

	@Override
	public List<ReportRecord> findByCreateDateBetween(long currentTime) {
		long startDate = getDayInWeek(currentTime, "MONDAY");
		long endDate = getDayInWeek(currentTime,"SUNDAY");
		return reportRecordRepository.findByCreateDateBetweenOrderByCreateDateDesc(startDate,endDate);
	}

	private long getDayInWeek(long currentTime, String day) {
		/*获取输入时间所在周的某一天
		day取值：MONDAY,SUNDAY 暂时只取每周的首与尾，不作其它考虑
		 */
		int daySequence = 0;
		Calendar calendar = Calendar.getInstance();
		if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
		}
		if (day == "MONDAY") {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else if (day == "SUNDAY") {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
		return calendar.getTimeInMillis();
	}
	public WeeklyReport queryWeeklyReportByUserId(long userId) {
		return weeklyReportRepository.findByReportUserId(userId);
	}

	@Override
	public boolean submitWeeklyReport(long weeklyReportId) throws Exception {
		//TODO 提交周報
		
		//TODO 生成推送信息
		
		return false;
	}
}
