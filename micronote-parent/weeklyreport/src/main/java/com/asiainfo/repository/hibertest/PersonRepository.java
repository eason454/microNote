package com.asiainfo.repository.hibertest;

import com.asiainfo.domain.hibtest.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eason on 2017/2/10.
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
}
