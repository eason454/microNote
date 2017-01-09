package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;

/**
 * Created by eason on 2017/1/9.
 */
public interface IReportRecordService {
    public ReportRecord createWeeklyPlan(ReportRecord reportRecord);
    public boolean modifyWeeklyPlan(ReportRecord reportRecord);
    public boolean deleteWeeklyPlan(long reportRecordId);

}
