package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;

/**
 * Created by eason on 2017/1/11.
 */
public interface IWeeklyReportToService {
     boolean saveWeeklyReportTo(WeeklyReportTo weeklyReportTo);
     WeeklyReportTo findByReportUserId(String userId);
}
