
package com.asiainfo.controller.weeklyreport;
import com.asiainfo.domain.response.KaraAttachment;
import com.asiainfo.domain.response.KaraField;
import com.asiainfo.domain.response.KaraMessage;
import com.asiainfo.util.consts.CommonConst;
import com.asiainfo.util.kara.MessageConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.service.weeklyreport.interfaces.IPlanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class PlanController {
    private static Log logger=LogFactory.getLog(PlanController.class);
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
    @PostMapping(path="/queryNextWeekPlan")
    public KaraMessage queryNextWeekPlan(@RequestParam Map<String,String> param,@RequestHeader Map<String,String> header,@RequestBody Map<String,String> body){//@RequestHeader(value = "userId") long userId, , @RequestBody String body
        logger.error("header:"+header.toString());
        logger.error("param:"+param.toString());
        logger.error("body:"+body.toString());
        List<Plan> plans=planRecordService.queryNextWeekPlan(1L);
        StringBuffer content=new StringBuffer();
        for (Plan plan :
                plans) {
            content.append(plan.getContent()+"\\n");
        }
        KaraField field=new KaraField();
        field.setTitle("下周计划");
        field.setValue(content.toString());
        List<KaraField> list=new ArrayList<KaraField>();
        list.add(field);
        KaraMessage message=MessageConstructor.constructMessageWithFields(list);
        return message;
    }
    @GetMapping(path="/queryThisWeekPlan")
    public List<Plan> queryThisWeekPlan(@RequestHeader(value = "userId") long userId){
        return planRecordService.queryThisWeekPlan(userId);
    }

    @PostMapping(path = "/cancelPlan")
    public boolean cancelPlan(@RequestParam(value = "planId") long planId) {
        try {
            planRecordService.canelPlan(planId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    @PostMapping(path = "/confirmedPlan")
    public boolean confirmedPlan(@RequestParam(value = "planId") long planId,
                                 @RequestParam(value = "planId") long worklyReportId) {
        try {
            planRecordService.confirmePlan(planId, worklyReportId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @PostMapping(path = "/delayPlan")
    public boolean confirmedPlan(@RequestParam(value = "planId") long planId) {
        try {
            planRecordService.delayPlan(planId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
