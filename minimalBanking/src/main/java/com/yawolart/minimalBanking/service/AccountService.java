package com.yawolart.minimalBanking.service;

import com.yawolart.minimalBanking.dto.AccountInfoRespDto;
import com.yawolart.minimalBanking.dto.CreateAccountReqDto;
import com.yawolart.minimalBanking.dto.ModelDtoMapper;
import com.yawolart.minimalBanking.exceptions.FindAccountException;
import com.yawolart.minimalBanking.exceptions.InvalidAccountTypeException;
import com.yawolart.minimalBanking.model.Account;
import com.yawolart.minimalBanking.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountInfoRespDto createAccount(
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
}
