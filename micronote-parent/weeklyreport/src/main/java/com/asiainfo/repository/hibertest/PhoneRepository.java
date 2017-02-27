package com.asiainfo.repository.hibertest;

import com.asiainfo.domain.hibtest.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eason on 2017/2/10.
 */
public interface PhoneRepository extends JpaRepository<Phone,Long> {
}
