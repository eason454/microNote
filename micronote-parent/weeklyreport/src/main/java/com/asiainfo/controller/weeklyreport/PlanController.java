
package com.asiainfo.controller.weeklyreport;
import com.asiainfo.domain.kara.KaraRequestObject;
import com.asiainfo.domain.kara.response.KaraField;
import com.asiainfo.domain.kara.response.KaraMessage;
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
    public KaraMessage queryNextWeekPlan(@RequestBody KaraRequestObject request){//@RequestHeader(value = "userId") long userId, , @RequestBody String body
        List<Plan> plans=planRecordService.queryNextWeekPlan(request.getUserId());
        StringBuffer content=new StringBuffer();
        for (Plan plan :
                plans) {
            content.append(plan.getContent()+",");
        }
        KaraField field=new KaraField();
        field.setTitle("下周计划");
        field.setValue(content.toString());
        List<KaraField> list=new ArrayList<KaraField>();
        list.add(field);
        KaraMessage message=MessageConstructor.constructMessageWithFields(CommonConst.KaraInfo.nextWeeklyInfo,list);
        return message;
    }

    /**
     * 提供给web，查询本周计划
     * @param userId
     * @return
     */
    @GetMapping(path="/queryThisWeekPlan")
    public List<Plan> queryThisWeekPlan(@RequestParam(value = "userId") String userId){
        return planRecordService.queryThisWeekPlan(userId);
    }

    /**
     * 提供给web，查询下周计划
     * @param userId
     * @return
     */
    @GetMapping(path="/queryNextWeekPlan")
    public List<Plan> queryNextWeekPlan(@RequestParam(value = "userId") String userId){
        return planRecordService.queryNextWeekPlan(userId);
    }

    @PostMapping(path = "/cancelPlan")
    public boolean cancelPlan(@RequestBody Plan plan) {
        try {
            planRecordService.canelPlan(plan.getPlanId());
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
    public boolean confirmedPlan(@RequestBody Plan plan) {
        try {
            planRecordService.delayPlan(plan.getPlanId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
