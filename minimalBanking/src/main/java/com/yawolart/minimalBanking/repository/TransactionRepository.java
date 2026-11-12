package com.yawolart.minimalBanking.repository;

import com.yawolart.minimalBanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends
        JpaRepository<Transaction,String> {

}
