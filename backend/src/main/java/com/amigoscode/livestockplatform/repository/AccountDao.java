package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Integer> {
    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    Optional<AccountEntity> findByUsername(String username);
}
