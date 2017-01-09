package com.asiainfo.repository.weeklyreport;

import com.asiainfo.domain.entity.weeklyreport.Plan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Jerry on 2017/1/9.
 */
@RepositoryRestResource(collectionResourceRel = "plan",path = "plan")
public interface PlanRepository {
    @Query("select p.plan_id,p.content,p.start_date,p.end_date,p.state from Plan p where p.end_date>?1 and p.start_date<?2")
    public List<Plan> findWeeklyPlans(@Param("beginDate") long beginDate,@Param("endDate") long endDate);
}
