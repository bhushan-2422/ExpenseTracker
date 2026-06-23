package org.example.repository;

import org.springframework.stereotype.Repository;
import org.example.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, String> {
    public UserInfo findByUsername(String username);
}
