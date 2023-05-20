package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItemEntity, Integer> {

}
