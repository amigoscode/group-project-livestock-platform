package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UsersEntity, Integer> {

}
