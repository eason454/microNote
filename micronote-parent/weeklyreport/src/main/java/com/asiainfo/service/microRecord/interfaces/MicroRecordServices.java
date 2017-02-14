package com.asiainfo.service.microRecord.interfaces;

import com.asiainfo.domain.entity.microRecord.MicroRecord;
import java.util.List;

/**
 * Created by Jerry on 2017/1/6.
 */
public interface MicroRecordServices {
    public List<MicroRecord> findByCreateDateBetween(long startDate, long endDate);
    public List<MicroRecord> findByReportUserId(String reportUserId);
}
