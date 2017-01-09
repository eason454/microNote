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
    @Query(name = "select p.plan_id,p.content,p.start_date,p.end_date,p.state from plan p where p.end_date>?1 or p.start_date<?2")
    public List<Plan> findByStartDateAndEndDate(@Param("beginDate") long beginDate,@Param("endDate") long endDate);

//    public List<Plan> findByEndDateGreaterThanOrStartDateLessThan(@Param("beginDate") long beginDate, @Param("endDate") long endDate);
    List<Plan> findByEndDateGreaterThanAndStartDateLessThan(@Param("beginDate") long beginDate, @Param("endDate") long endDate);
    @Query
    List<Plan> findByNextWeeklyPlan();

}
