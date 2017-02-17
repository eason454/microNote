package com.asiainfo.service.microRecord.impl;

import com.asiainfo.domain.entity.microRecord.MicroRecord;
import com.asiainfo.repository.microRecord.MicroRecordRepository;
import com.asiainfo.service.microRecord.interfaces.MicroRecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jerry on 2017/1/6.
 */
@Service
public class MicroRecordServicesImpl implements MicroRecordServices {
    @Autowired
    public MicroRecordRepository microRecordRepository;

    @Override
    public List<MicroRecord> findByCreateDateBetween(long startDate, long endDate) {
        return microRecordRepository.findByCreateDateBetweenOrderByCreateDateDesc(startDate, endDate);
    }

    @Override
    public List<MicroRecord> findByReportUserId(String reportUserId){
        return microRecordRepository.findByReportUserIdOrderByCreateDateAsc(reportUserId);
    }
}
