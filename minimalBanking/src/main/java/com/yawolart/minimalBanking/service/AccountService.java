package com.yawolart.minimalBanking.service;

import com.yawolart.minimalBanking.dto.AccountInfoRespDto;
import com.yawolart.minimalBanking.dto.CreateAccountReqDto;
import com.yawolart.minimalBanking.dto.ModelDtoMapper;
import com.yawolart.minimalBanking.exceptions.FindAccountException;
import com.yawolart.minimalBanking.exceptions.InvalidAccountTypeException;
import com.yawolart.minimalBanking.exceptions.InvalidAmount;
import com.yawolart.minimalBanking.model.Account;
import com.yawolart.minimalBanking.model.Transaction;
import com.yawolart.minimalBanking.repository.AccountRepository;
import com.yawolart.minimalBanking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Transactional
    public AccountInfoRespDto openAccount(
            CreateAccountReqDto createAccountReqDto,
            int accountType
    ){
        if(accountType<0 || accountType>2){
            throw new InvalidAccountTypeException
                    ("Valid Account type: 0 - SAVING, 1 - CURRENT, " +
                     "2 - SALARY");
        }
        Account account = ModelDtoMapper.mapToAccount(
                createAccountReqDto,
                accountType
        );
        Account respAccount = accountRepository.save(account);
        return ModelDtoMapper.mapToAccountInfoRespDto(respAccount);
    }

    @Transactional
    public void closeAccount(Long id){

        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()){
            throw new FindAccountException("Account not found on" +
                    " this account no.");
        }
        accountRepository.deleteById(id);
    }

    @Transactional
    public AccountInfoRespDto deposit(
            Long id,
            BigDecimal amount
    ){
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmount("Deposit amount must be more than 0");
        }
        Account account = accountRepository.findLockedById(id)
                .orElseThrow(() -> new FindAccountException("Account not " +
                        "found on this account no."));
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                null,
                account,
                amount,
                LocalDateTime.now()
        );

        BigDecimal currentBalance = account.getBalance();
        account.setBalance(currentBalance.add(amount));

        transactionRepository.save(transaction);

        return ModelDtoMapper
                .mapToAccountInfoRespDto(account);
    }
}
