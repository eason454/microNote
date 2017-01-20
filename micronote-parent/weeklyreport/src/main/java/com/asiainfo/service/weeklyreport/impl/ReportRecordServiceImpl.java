package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import com.asiainfo.util.CommonUtils;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * Created by eason on 2017/1/9.
 */
@Service
public class ReportRecordServiceImpl implements IReportRecordService {
    @Autowired
    ReportRecordRepository reportRecordRepository;

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
    public ReportRecord saveRecord(ReportRecord reportRecord) throws Exception {
        return reportRecordRepository.save(reportRecord);
    }

    @Override
    @Transactional
    public boolean deleteReportRecordById(@Param("recordId") long recordId) {
        if(reportRecordRepository.exists(recordId)){
            reportRecordRepository.delete(recordId);
            return true;
        }else{
            return false;
        }
    }
}
