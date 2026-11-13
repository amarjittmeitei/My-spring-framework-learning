package com.yawolart.minimalBanking.repository;

import com.yawolart.minimalBanking.model.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends
        JpaRepository<Account,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Account> findLockedById(Long id);

}
