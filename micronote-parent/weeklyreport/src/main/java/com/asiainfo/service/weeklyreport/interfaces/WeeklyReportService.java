package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;

import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
public interface WeeklyReportService {
    public WeeklyReport createWeeklyReport(long reportUserId);
    public List<ReportRecord> findByCreateDateBetween(long currentTime);
    public WeeklyReport queryWeeklyReportByUserId(long userId);
    
    boolean submitWeeklyReport(long weeklyReportId) throws Exception;
}
