package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

}
