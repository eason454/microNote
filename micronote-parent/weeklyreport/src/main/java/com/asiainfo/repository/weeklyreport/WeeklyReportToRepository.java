package com.asiainfo.repository.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.WeeklyReportTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by eason on 2017/1/6.
 */
@RepositoryRestResource(collectionResourceRel = "weeklyReportTos",path = "weeklyReportTos")
public interface WeeklyReportToRepository extends JpaRepository<WeeklyReportTo,Long> {
	
	public WeeklyReportTo findByReportUserId(String reportUserId);
	
}
