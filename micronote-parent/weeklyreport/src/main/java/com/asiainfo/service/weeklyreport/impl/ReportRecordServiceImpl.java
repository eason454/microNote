package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
import com.asiainfo.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint;
import org.springframework.stereotype.Service;

/**
 * Created by eason on 2017/1/9.
 */
@Service
public class ReportRecordServiceImpl implements IReportRecordService {
    @Autowired
    ReportRecordRepository reportRecordRepository;

    @Override
    public ReportRecord createReportRecord(ReportRecord reportRecord) {
        return reportRecordRepository.save(reportRecord);
    }

    @Override
    public boolean modifyReportRecord(ReportRecord reportRecord) {
        /*
        1、根据ID获取到旧数据记录
        2、过滤空值
        3、将新值传入到旧的对象里面
        4、调用Repository保存
         */
        ReportRecord oldRecord = reportRecordRepository.findOne(reportRecord.getReportRecordId());
        String[] nullProperties = CommonUtils.getNullPropertyNames(reportRecord);
        BeanUtils.copyProperties(reportRecord,oldRecord,nullProperties);
        reportRecordRepository.save(oldRecord);
        return false;
    }

    @Override
    public boolean deleteReportRecord(long reportRecordId) {
        reportRecordRepository.delete(reportRecordId);
        return true;
    }
}
