package com.asiainfo.controller.weeklyreport;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;

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
