package com.asiainfo.service.weeklyreport.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asiainfo.domain.entity.user.User;
import com.asiainfo.domain.entity.weeklyreport.ReportRecord;
import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;

/**
 * Created by eason on 2017/1/6.
 */
public interface IWeeklyReportService {
    WeeklyReport createWeeklyReport(String reportUserId);
    List<ReportRecord> findByCreateDateBetween(String reportUserId, long currentTime);
    WeeklyReport queryWeeklyReportByUserId(String userId);
    
    boolean submitWeeklyReport(long weeklyReportId) throws Exception;
    WeeklyReport queryWeeklyReportByUserIdAndWeekly(String userId, int weekly);
    WeeklyReport modifyWeeklyReport(WeeklyReport weeklyReport);
    List<ReportRecord> findByUserIdAndTime(String userId, long time);
    
    /**
     * 查询已经提交周报的用户列表
     * @param authorId 周报审核人的<i>userId</i>
     * @param pageable 分页信息
     * @return
     */
    Page<User> getReportUsers(String authorId, Pageable pageable);
}
