package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.microRecord.MicroRecord;
import com.asiainfo.domain.entity.weeklyreport.Plan;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.repository.microRecord.MicroRecordRepository;
import com.asiainfo.repository.weeklyreport.PlanRepository;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import com.asiainfo.util.CommonUtils;
import com.asiainfo.util.consts.CommonConst;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by eason on 2017/1/9.
 */
@Service
public class ReportRecordServiceImpl implements IReportRecordService {
    @Autowired
    ReportRecordRepository reportRecordRepository;

    @Autowired
    MicroRecordRepository microRecordRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public ReportRecord modifyReportRecord(ReportRecord reportRecord) {
        ReportRecord oldRecord = reportRecordRepository.findOne(reportRecord.getReportRecordId());
        if(oldRecord == null){
            return null;
        }
        String[] nullProperties = CommonUtils.getNullPropertyNames(reportRecord);
        BeanUtils.copyProperties(reportRecord, oldRecord, nullProperties);
        return reportRecordRepository.save(oldRecord);
    }

    @Override
    @Transactional
    public ReportRecord saveRecord(ReportRecord reportRecord) throws Exception {
        //在这里同步保存ReportRecord和MicroRecord
        MicroRecord microRecord = new MicroRecord();
        BeanUtils.copyProperties(reportRecord,microRecord);
        microRecordRepository.save(microRecord);
        return reportRecordRepository.save(reportRecord);
    }

    @Override
    @Transactional
    public ReportRecord deleteReportRecordById(@Param("recordId") long recordId) {
        /*
        1. If record is exists, delete it.
        2. Check if there are any plans related to this record.
        3. If have, reset the plan's state to "planning"
         */
        if(reportRecordRepository.exists(recordId)){
            ReportRecord reportRecord = reportRecordRepository.findOne(recordId);
            reportRecordRepository.delete(recordId);
            if(reportRecord.getPlan() != null){
                Plan plan = reportRecord.getPlan();
                plan.setState(CommonConst.PlanRecordState.PLANNING);
                planRepository.save(plan);
            }
            return reportRecord;
        }
        return null;
    }
}
