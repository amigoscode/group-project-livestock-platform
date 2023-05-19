package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<CartEntity, Integer> {

}
