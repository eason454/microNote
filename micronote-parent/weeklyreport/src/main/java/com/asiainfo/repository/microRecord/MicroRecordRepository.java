package com.asiainfo.repository.microRecord;

import com.asiainfo.domain.entity.microRecord.MicroRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by eason on 2017/1/6.
 */
@RepositoryRestResource(collectionResourceRel = "microRecords",path = "microRecords")
public interface MicroRecordRepository extends JpaRepository<MicroRecord,Long> {
    public List<MicroRecord> findByCreateDateBetweenOrderByCreateDateDesc(@Param("startDate") long startDate, @Param("endDate") long endDate);
}
