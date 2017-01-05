package com.asiainfo.repository.weeklyreport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.asiainfo.domain.entity.weeklyreport.ReportRecord;

/**
 * Created by eason on 2017/1/5.
 */
@RepositoryRestResource(collectionResourceRel = "finishedRecords",path = "finishedRecords")
public interface FinishedRecordRepository extends JpaRepository<ReportRecord,Long> {
}
