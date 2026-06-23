package org.example.repository;

import java.util.Optional;

import org.example.entities.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer>{
    Optional<RefreshToken> findByToken(String token);    
}
