package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.UsersEntity;
import com.amigoscodelivestock_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<UsersEntity, Integer> {

}
