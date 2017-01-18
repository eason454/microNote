package com.asiainfo.controller.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.domain.kara.KaraRequestObject;
import com.asiainfo.domain.kara.response.KaraField;
import com.asiainfo.domain.kara.response.KaraMessage;
import com.asiainfo.repository.weeklyreport.WeeklyReportRepository;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportService;
import com.asiainfo.util.consts.CommonConst;
import com.asiainfo.util.kara.MessageConstructor;
import com.asiainfo.util.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eason on 2017/1/9.
 */
@RestController
public class ReportRecordController {
    @Autowired
    IWeeklyReportService weeklyReportService;
    @Autowired
    WeeklyReportRepository weeklyReportRepository;
    @Autowired
    IReportRecordService reportRecordService;

    @RequestMapping(path = "/saveReportRecord", method = RequestMethod.POST)
    public KaraMessage saveReportRecord(@RequestBody KaraRequestObject requestObject){
        /*
        1、获取传入的UserId和Text内容
        2、检查周报是否存在，并获取ID
        3、构造数据对象并写入
         */
        //获取传入值
        String userId = requestObject.getUserId();
        String content = requestObject.getText();

        //定义Kara的返回结构
        KaraField karaField = new KaraField();
        List<KaraField> list = new ArrayList<KaraField>();
        List<ReportRecord> reportRecordList = new ArrayList<ReportRecord>();
        //处理userId为空的情况
        if(userId == null || "".equals(userId)){
            return null;
        }

        //查询该员工本周的周报对象
        WeeklyReport weeklyReport=  weeklyReportService.queryWeeklyReportByUserIdAndWeekly(userId, TimeUtil.getWeekOfYear());

        //构造写入对象并写入数据
        ReportRecord reportRecord = new ReportRecord();
        reportRecord.setContent(content);
        reportRecord.setReportUserId(userId);
        reportRecord.setStartDate(System.currentTimeMillis());
        reportRecord.setCreateDate(System.currentTimeMillis());
        reportRecord.setEndDate(System.currentTimeMillis());
        reportRecord.setWeeklyReport(weeklyReport);
        reportRecordService.modifyReportRecord(reportRecord);

        //数据写入到Kara返回结构中
        karaField.setTitle(CommonConst.KaraInfo.weeklyWork);
        karaField.setValue(content);
        list.add(karaField);

        //返回KaraMessage
        KaraMessage karaMessage = MessageConstructor.constructMessageWithFields(CommonConst.KaraInfo.saveRecordSuccess,list);
        return karaMessage;

    }

    @PostMapping(path = "/deleteRecord")
    public boolean deleteRecord(@RequestBody ReportRecord jsonObject){
        Long recordId = jsonObject.getReportRecordId();
        return reportRecordService.deleteReportRecordById(recordId);
    }

    @PostMapping(path = "/modifyRecord")
    public ReportRecord modifyReportRecord(@RequestBody ReportRecord reportRecord){
        return reportRecordService.modifyReportRecord(reportRecord);
    }

}
