package com.asiainfo.repository.weeklyreport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReport;

/**
 * Created by eason on 2017/1/6.
 */
@RepositoryRestResource(collectionResourceRel = "weeklyReports",path = "weeklyReports")
public interface WeeklyReportRepository extends JpaRepository<WeeklyReport,Long> {
    WeeklyReport findByReportUserId(String reportUserId);
}
