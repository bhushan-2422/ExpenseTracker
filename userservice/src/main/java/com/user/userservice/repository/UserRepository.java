package com.user.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.userservice.entities.UserInfo;

@Repository
@EnableJpaRepositories
public interface UserRepository extends CrudRepository<UserInfo, String> {
    
    Optional<UserInfo> findByUserId(String userId);
}
