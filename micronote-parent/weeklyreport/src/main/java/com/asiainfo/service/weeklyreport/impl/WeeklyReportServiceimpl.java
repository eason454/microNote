package com.asiainfo.service.weeklyreport.impl;

import java.util.List;

import com.asiainfo.util.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.service.notify.impl.Message;
import com.asiainfo.service.notify.interfaces.INotifyService;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportService;
import com.asiainfo.util.consts.CommonConst.NotificationType;
import com.asiainfo.util.consts.CommonConst.WeeklyReportReport;

/**
 * Created by eason on 2017/1/6.
 */
@Service
public class WeeklyReportServiceimpl implements IWeeklyReportService {
	@Autowired
	private ReportRecordRepository reportRecordRepository;
	@Autowired
	private WeeklyReportRepository weeklyReportRepository;
	//注入推送服務

	@Override
	public List<ReportRecord> findByUserIdAndTime(String userId, long time) {
		//根据时间和使用者ID获取一周的记录内容

		//根据时间获取周数
		int weekOfYear = new Long(TimeUtil.getWeekOfYear(time)).intValue();
		//调用方法获取到周报对象
		WeeklyReport weeklyReport = weeklyReportRepository.findByReportUserIdAndWeekly(userId, weekOfYear);
		//获取该周报对应的记录
		return weeklyReport.getReportRecord();
	}
			@Autowired
	private INotifyService notifyService;
	//讀取配置的推送信息
	@Value("${weeklyReport.notify.information}")
	private String notifyMessage;

	@Override
	public WeeklyReport createWeeklyReport(String reportUserId) {
		// 构造WeeklyReport对象
		WeeklyReport weeklyReport = new WeeklyReport(reportUserId);
		return weeklyReportRepository.save(weeklyReport);
	}

	@Override
	public List<ReportRecord> findByCreateDateBetween(String reportUserId,long currentTime) {
		long startDate = TimeUtil.getDayInWeek(currentTime, "MONDAY");
		long endDate = TimeUtil.getDayInWeek(currentTime, "SUNDAY");
		return reportRecordRepository.findByReportUserIdAndCreateDateBetweenOrderByCreateDateDesc(reportUserId, startDate, endDate);
	}


	public WeeklyReport queryWeeklyReportByUserId(String userId) {
		return weeklyReportRepository.findByReportUserId(userId);
	}

	/**
	 * 提交周報<br>
	 * 提醒審核人查看周報<br>
	 * 第一版本实现实时推送<br>
	 * 第二版检查提醒频率后考虑是否改造为集中推送
	 */
	@Override
	public boolean submitWeeklyReport(long weeklyReportId) throws Exception {
		// TODO 提交周報
		WeeklyReport weeklyReport = weeklyReportRepository.findOne(weeklyReportId);
		weeklyReport.setState(WeeklyReportReport.SUBMMITED);
		weeklyReportRepository.save(weeklyReport);

		// TODO 生成推送信息
		notifyService.notify(new Message(weeklyReport.getAuditingUserId() + "", notifyMessage,
				NotificationType.WEEKLY_REPORT_CHECK_NOTIFY));
		return true;
	}

	@Override
	public WeeklyReport queryWeeklyReportByUserIdAndWeekly(String userId, int weekly) {
		return weeklyReportRepository.findByReportUserIdAndWeekly(userId, weekly);
	}

    @Override
    public WeeklyReport modifyWeeklyReport(WeeklyReport weeklyReport) {
        return weeklyReportRepository.save(weeklyReport);
    }
}
