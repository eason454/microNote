
package com.asiainfo.controller.weeklyreport;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.kara.KaraRequestObject;
import com.asiainfo.domain.kara.response.KaraField;
import com.asiainfo.domain.kara.response.KaraMessage;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;
import com.asiainfo.util.consts.CommonConst;
import com.asiainfo.util.kara.MessageConstructor;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class PlanController {
    private static Log logger=LogFactory.getLog(PlanController.class);
    @Autowired
    private IPlanRecordService planRecordService;
    @PostMapping(path = "/createWeeklyPlan")
    public Plan createWeeklyPlan(@RequestBody Plan plan  ) {
        return planRecordService.createWeeklyPlan(plan, plan.getWeek());
    }
    @PostMapping(path="/modifyWeeklyPlan")
    public boolean modifyWeeklyPlan(@RequestBody Plan plan){
        return planRecordService.modifyWeeklyPlan(plan);
    }
    @PostMapping(path="/deleteWeeklyPlan")
    public boolean deleteWeeklyPlan(@RequestBody Plan plan){
        return planRecordService.deleteWeeklyPlan(plan.getPlanId());
    }
    @PostMapping(path="/queryNextWeekPlan")
    public KaraMessage queryNextWeekPlan(@RequestBody KaraRequestObject request){//@RequestHeader(value = "userId") long userId, , @RequestBody String body
        List<Plan> plans=planRecordService.queryNextWeekPlan(request.getUserId());
        List<KaraField> list=new ArrayList<>();
        for (Plan plan :
                plans) {
            KaraField field=new KaraField();
            field.setTitle("");
            field.setValue(plan.getContent());
            field.setId(plan.getPlanId());
            list.add(field);
        }
        KaraMessage message=MessageConstructor.constructMessageWithFields(CommonConst.KaraInfo.nextWeeklyInfo,list);
        return message;
    }

    /**
     * 提供给web，查询本周计划
     * @return
     */
    @PostMapping(path="/queryThisWeekPlan")
    public KaraMessage queryThisWeekPlan(@RequestBody KaraRequestObject request){
        List<Plan> plans=planRecordService.queryThisWeekPlan(request.getUserId());
        List<KaraField> list=new ArrayList<>();
        for (Plan plan :
                plans) {
            KaraField field=new KaraField();
            field.setTitle("");
            field.setValue(plan.getContent());
            field.setId(plan.getPlanId());
            list.add(field);
        }
        KaraMessage message=MessageConstructor.constructMessageWithFields(CommonConst.KaraInfo.thisWeeklyInfo,list);
        return message;
    }

    /**
     * 提供给web，查询下周计划
     * @param userId
     * @return
     */
    @GetMapping(path="/queryNextWeekPlan/{user_id}")
    public List<Plan> queryNextWeekPlan(@PathVariable(value = "user_id") String userId){
        return planRecordService.queryNextWeekPlan(userId);
    }

    @PostMapping(path = "/cancelPlan/{plan_id}")
   
    public boolean cancelPlan(@PathVariable(value = "plan_id") long planId, @RequestBody String reason) {
        try {
        	JSONObject json = new JSONObject(reason);
            planRecordService.canelPlan(planId, json.getString("reason"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    
    @PostMapping(path = "/recoverPlan/{plan_id}")
    public boolean recoverPlan(@PathVariable(value = "plan_id") long planId) {
        try {
            planRecordService.recoverPlan(planId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @PostMapping(path = "/confirmedPlan/{plan_id}/{weekly_report_id}")
    public ReportRecord confirmedPlan(@PathVariable(value = "plan_id") long planId,
    		@PathVariable(value = "weekly_report_id") long weeklyReportId) throws Exception {
       return planRecordService.confirmePlan(planId, weeklyReportId);
    }

    @PostMapping(path = "/delayPlan/{plan_id}")
    public boolean confirmedPlan(@PathVariable(value = "plan_id") long planId) {
        try {
            planRecordService.delayPlan(planId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}

