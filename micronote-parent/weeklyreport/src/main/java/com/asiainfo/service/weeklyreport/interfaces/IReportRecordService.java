package com.asiainfo.service.weeklyreport.interfaces;

import org.springframework.data.repository.query.Param;

/**
 * Created by eason on 2017/1/9.
 */
public interface IReportRecordService {
    boolean deleteReportRecordById(@Param("recordId") long recordId);
}
