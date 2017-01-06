package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;

/**
 * Created by eason on 2017/1/6.
 */
public interface WeeklyReportService {
    public WeeklyReport createWeeklyReport(long reportUserId);
}
