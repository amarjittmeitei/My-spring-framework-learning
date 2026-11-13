package com.yawolart.minimalBanking.service;

import com.yawolart.minimalBanking.dto.AccountInfoRespDto;
import com.yawolart.minimalBanking.dto.CreateAccountReqDto;
import com.yawolart.minimalBanking.dto.ModelDtoMapper;
import com.yawolart.minimalBanking.exceptions.FindAccountException;
import com.yawolart.minimalBanking.exceptions.InvalidAccountTypeException;
import com.yawolart.minimalBanking.exceptions.InvalidAmount;
import com.yawolart.minimalBanking.model.Account;
import com.yawolart.minimalBanking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

        BigDecimal currentBalance = account.getBalance();
        account.setBalance(currentBalance.add(amount));

        return ModelDtoMapper
                .mapToAccountInfoRespDto(account);
    }
}
