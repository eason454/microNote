package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class ReportRecordController {
    @Autowired
    private IReportRecordService reportRecordService;
    @Autowired
    private WeeklyReportService weeklyReportService;
    @PostMapping(path = "/createWeeklyPlan")
    public ReportRecord createWeeklyPlan(@RequestBody ReportRecord reportRecord,@RequestHeader(value="userId") long userId) {
        WeeklyReport weeklyReport= weeklyReportService.queryWeeklyReportByUserId(userId);
        List<ReportRecord> list=new ArrayList<ReportRecord>();
        list.add(reportRecord);
        weeklyReport.setReportRecord(list);
        return reportRecordService.createWeeklyPlan(reportRecord);
    }
    @PostMapping(path="/modifyWeeklyPlan")
    public boolean modifyWeeklyPlan(@RequestBody ReportRecord reportRecord){
        return reportRecordService.modifyWeeklyPlan(reportRecord);
    }
    @DeleteMapping(path="/deleteWeeklyPlan")
    public boolean deleteWeeklyPlan(@RequestBody ReportRecord reportRecord){
        return reportRecordService.deleteWeeklyPlan(reportRecord.getReportRecordId());
    }
}
