package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrdersEntity, Integer> {

}
