package com.asiainfo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asiainfo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
