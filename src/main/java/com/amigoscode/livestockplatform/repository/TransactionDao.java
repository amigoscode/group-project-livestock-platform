package com.amigoscode.livestockplatform.repository;

import com.amigoscode.livestockplatform.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<TransactionsEntity, Integer> {

}
