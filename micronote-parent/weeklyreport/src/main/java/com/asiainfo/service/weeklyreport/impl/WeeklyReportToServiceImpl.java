package com.asiainfo.service.weeklyreport.impl;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;
import com.asiainfo.repository.weeklyreport.WeeklyReportToRepository;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportToService;
import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportService;
import com.asiainfo.util.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eason on 2017/1/11.
 */
@Service
@Transactional
public class WeeklyReportToServiceImpl implements IWeeklyReportToService {
    @Autowired
    private WeeklyReportToRepository weeklyReportToRepository;
    @Autowired
    private IWeeklyReportService weeklyReportService;
    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public boolean saveWeeklyReportTo(WeeklyReportTo weeklyReportTo) {
        //保存汇报对象，并且修改本周周报
        weeklyReportToRepository.save(weeklyReportTo);
        WeeklyReport wr=weeklyReportService.queryWeeklyReportByUserIdAndWeekly(weeklyReportTo.getReportUserId(), TimeUtil.getWeekOfYear());
        if(wr!=null){
            wr.setAuditingUserId(weeklyReportTo.getAuditingUserId());
            weeklyReportService.modifyWeeklyReport(wr);
        }
        return true;
    }

    @Override
    public WeeklyReportTo findByReportUserId(String userId) {
        return weeklyReportToRepository.findByReportUserId(userId);
    }
}
