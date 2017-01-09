package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import com.asiainfo.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eason on 2017/1/9.
 */
@Service
public class ReportRecordServiceImpl implements IReportRecordService {
    @Autowired
    private ReportRecordRepository reportRecordRepository;
    @Override
    public ReportRecord createWeeklyPlan(ReportRecord reportRecord) {
        return reportRecordRepository.save(reportRecord);
    }

    @Override
    public boolean modifyWeeklyPlan(ReportRecord reportRecord) {
        //目前修改只能是内容，结束时间
        ReportRecord oldReportRecord=reportRecordRepository.findOne(reportRecord.getReportRecordId());
        String[] ignoreList= CommonUtils.getNullPropertyNames(reportRecord);
        BeanUtils.copyProperties(reportRecord,oldReportRecord);
        reportRecordRepository.save(oldReportRecord);
        return true;
    }

    @Override
    public boolean deleteWeeklyPlan(long reportRecordId) {
        reportRecordRepository.delete(reportRecordId);
        return true;
    }
}
