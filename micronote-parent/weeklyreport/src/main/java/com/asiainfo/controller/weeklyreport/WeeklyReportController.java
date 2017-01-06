package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eason on 2017/1/6.
 */
@RestController
public class WeeklyReportController {
    @Autowired
    private WeeklyReportService weeklyReportService;
    @RequestMapping(path = "/createWeeklyReport",method = RequestMethod.POST)
    public WeeklyReport createWeeklyReport(@PathVariable("userId") long reportUserId){
     return  weeklyReportService.createWeeklyReport(reportUserId);
    }
}
