package com.asiainfo.repository.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Jerry on 2017/1/9.
 */
@RepositoryRestResource(collectionResourceRel = "plan",path = "plan")
public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findByEndDateGreaterThanAndStartDateLessThan(@Param("beginDate") long beginDate, @Param("endDate") long endDate);

    /**
     * 获取下周计划
     * @param time
     * @return
     */
    List<Plan> findByReportUserIdAndStartDateGreaterThan(long userId,long lastTimeThisWeek);

    /**
     * 获取本周计划
     * @param userId
     * @param startTimeThisWeek
     * @param endTimeThisWeek
     * @return
     */
    List<Plan> findByEndDateGreaterThanAndStartDateLessThan(long userId,long startTimeThisWeek,long endTimeThisWeek);

}
