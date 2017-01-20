package com.asiainfo.service.weeklyreport.interfaces;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import org.springframework.data.repository.query.Param;

/**
 * Created by eason on 2017/1/9.
 */
public interface IReportRecordService {
    ReportRecord saveRecord(ReportRecord reportRecord) throws Exception;
    boolean deleteReportRecordById(@Param("recordId") long recordId);
    ReportRecord modifyReportRecord(ReportRecord reportRecord);
}
