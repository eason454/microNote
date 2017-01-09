package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import com.netflix.governator.annotations.binding.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(path = "/queryReportRecords", method = RequestMethod.GET)
    public List<ReportRecord> queryReportRecrodsByWeek(Long currentTime) {
        return weeklyReportService.findByCreateDateBetween(currentTime);
    }

    @RequestMapping(path = "/queryPlans", method = RequestMethod.GET)
    public List<Plan> queryReportPlansByWeek(Long currentTime){
        return null;
    }
}
