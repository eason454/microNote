package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.repository.weeklyreport.ReportRecordRepository;
import com.asiainfo.service.weeklyreport.interfaces.IReportRecordService;
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
    public boolean deleteReportRecordById(@Param("recordId") long recordId) {
        reportRecordRepository.delete(recordId);
        return true;
    }
}
