package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class PlanController {
    @PostMapping(path = "/createWeeklyPlan")
    public ReportRecord createWeeklyPlan(@RequestBody ReportRecord reportRecord, @RequestHeader(value="userId") long userId) {
        return null;
    }
    @PostMapping(path="/modifyWeeklyPlan")
    public boolean modifyWeeklyPlan(@RequestBody ReportRecord reportRecord){
        return true;
    }
    @DeleteMapping(path="/deleteWeeklyPlan")
    public boolean deleteWeeklyPlan(@RequestBody ReportRecord reportRecord){
        return true;
    }
}
