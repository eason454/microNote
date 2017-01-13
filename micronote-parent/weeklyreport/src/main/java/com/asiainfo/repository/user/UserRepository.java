package com.asiainfo.repository.user;

import com.asiainfo.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by eason on 2017/1/6.
 */
@RepositoryRestResource(collectionResourceRel = "users",path = "users")
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserNumber(String userNumber);
}
