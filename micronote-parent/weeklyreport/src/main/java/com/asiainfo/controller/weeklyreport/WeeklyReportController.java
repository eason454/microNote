package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eason on 2017/1/6.
 */
@RestController
public class WeeklyReportController {
    @Autowired
    private WeeklyReportService weeklyReportService;
    @RequestMapping(path = "/createWeeklyReport",method = RequestMethod.POST)
    public WeeklyReport createWeeklyReport(@RequestParam("userId") long reportUserId){
     return  weeklyReportService.createWeeklyReport(reportUserId);
    }
}
