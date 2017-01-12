package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;

import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
public interface IWeeklyReportService {
    WeeklyReport createWeeklyReport(String reportUserId);
    List<ReportRecord> findByCreateDateBetween(String reportUserId, long currentTime);
    WeeklyReport queryWeeklyReportByUserId(String userId);
    
    boolean submitWeeklyReport(long weeklyReportId) throws Exception;
    WeeklyReport queryWeeklyReportByUserIdAndWeekly(String userId, int weekly);
    WeeklyReport modifyWeeklyReport(WeeklyReport weeklyReport);
    List<ReportRecord> findByUserIdAndTime(String userId, long time);
}
