package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class ReportRecordController {
    @Autowired
    private IReportRecordService reportRecordService;
@PostMapping(path = "/create")
    public ReportRecord createWeeklyPlan(@RequestBody ReportRecord reportRecord){
        return reportRecordService.createWeeklyPlan(reportRecord);
   }
}
