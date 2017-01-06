package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eason on 2017/1/6.
 */
@Service
public class WeeklyReportServiceimpl implements WeeklyReportService {
    @Autowired
    private WeeklyReportRepository weeklyReportRepository;
    @Override
    public WeeklyReport createWeeklyReport(long reportUserId) {
        //构造WeeklyReport对象
        WeeklyReport weeklyReport=new WeeklyReport(reportUserId);
        return weeklyReportRepository.save(weeklyReport);
    }
}
