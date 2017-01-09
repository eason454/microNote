package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class PlanController {
    @Autowired
    private IPlanRecordService planRecordService;
    
    @PostMapping(path = "/createWeeklyPlan")
    public Plan createWeeklyPlan(@RequestBody Plan plan) {
        return planRecordService.createWeeklyPlan(plan);
    }
    
    @PostMapping(path="/modifyWeeklyPlan")
    public boolean modifyWeeklyPlan(@RequestBody Plan plan){
        return planRecordService.modifyWeeklyPlan(plan);
    }
    
    @DeleteMapping(path="/deleteWeeklyPlan")
    public boolean deleteWeeklyPlan(@RequestBody Plan plan){
        return planRecordService.deleteWeeklyPlan(plan.getPlanId());
    }
    
    @GetMapping(path="/queryNextWeekPlan")
    public List<Plan> queryNextWeekPlan(@RequestHeader(value = "userId") long userId){
        return planRecordService.queryNextWeekPlan(userId);
    }
    
    @PostMapping(path = "/cancelPlan")
    public boolean cancelPlan(@RequestParam(value = "planId") long planId) {
        try {
        	return planRecordService.canelPlan(planId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @PostMapping(path = "/confirmedPlan")
    public boolean confirmedPlan(@RequestParam(value = "planId") long planId,
                                 @RequestParam(value = "worklyReportId") long worklyReportId) {
        try {
           return  planRecordService.confirmePlan(planId, worklyReportId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PostMapping(path = "/delayPlan")
    public boolean confirmedPlan(@RequestParam(value = "planId") long planId) {
        try {
            return planRecordService.delayPlan(planId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
