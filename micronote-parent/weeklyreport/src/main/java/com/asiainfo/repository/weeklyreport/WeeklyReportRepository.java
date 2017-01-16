package com.asiainfo.repository.weeklyreport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;

/**
 * Created by eason on 2017/1/6.
 */
@RepositoryRestResource(collectionResourceRel = "weeklyReports",path = "weeklyReports")
public interface WeeklyReportRepository extends JpaRepository<WeeklyReport,Long> {
    WeeklyReport findByReportUserId(String reportUserId);
    WeeklyReport findByReportUserIdAndWeekly(String userId, int weekly);
    /**
     * 按提交狀態和審核人查詢一個特定周的周報
     * @param auditingUserId
     * @param weekly
     * @param state
     * @param pageable
     * @return
     */
    Page<WeeklyReport> findByAuditingUserIdAndWeeklyAndState(String auditingUserId,int weekly, String state, Pageable pageable);
}
