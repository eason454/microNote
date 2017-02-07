package com.asiainfo.domain.entity.weeklyreport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlanRel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "plan_rel_id")
	private long id;
	
	@Column(name = "plan_id")
	private long planId;
	
	@Column(name = "related_plan_id")
	private long relatedPlanId;

	@Column(name = "relationship")
	private String relationship ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public long getRelatedPlanId() {
		return relatedPlanId;
	}

	public void setRelatedPlanId(long relatedPlanId) {
		this.relatedPlanId = relatedPlanId;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
}
