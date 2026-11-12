package com.yawolart.minimalBanking.repository;

import com.yawolart.minimalBanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends
        JpaRepository<Account,Long> {

}
