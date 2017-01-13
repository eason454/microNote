package com.asiainfo.repository.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Jerry on 2017/1/9.
 */
@RepositoryRestResource(collectionResourceRel = "plan",path = "plan")
public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findByReportUserIdAndEndDateGreaterThanAndStartDateLessThan(String userId,long beginDate, long endDate);

    /**
     * 获取下周计划
     * @param
     * @return
     */
    List<Plan> findByReportUserIdAndStartDateGreaterThan(String userId,long lastTimeThisWeek);

}
