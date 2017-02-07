package com.asiainfo.repository.weeklyreport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.asiainfo.domain.entity.weeklyreport.PlanRel;

/**
 * Created by Jerry on 2017/1/9.
 */
@RepositoryRestResource(collectionResourceRel = "planRel",path = "planRel")
public interface PlanRelRepository extends JpaRepository<PlanRel,Long> {
	
	List<PlanRel> findByRelatedPlanId(long relatedPlanId);
}
