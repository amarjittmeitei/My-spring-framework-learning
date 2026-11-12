package com.yawolart.minimalBanking.dto;

import com.yawolart.minimalBanking.enumaration.AccountType;
import com.yawolart.minimalBanking.model.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ModelDtoMapper {
    public static Account mapToAccount(
            CreateAccountReqDto reqAccount,
            int accountType
    ){
        Account account = new Account();
        account.setFirstName(reqAccount.getFirstName());
        account.setLastName(reqAccount.getLastName());
        account.setAddress(reqAccount.getAddress());
        account.setAccountType(AccountType.values()[accountType]);
        account.setBalance(new BigDecimal(0));
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }

    public static AccountInfoRespDto mapToAccountInfoRespDto(
            Account account
    ){
        AccountInfoRespDto accountInfoRespDto = new AccountInfoRespDto();
        accountInfoRespDto.setAccountNo(account.getId());
        accountInfoRespDto.setFirstName(account.getFirstName());
        accountInfoRespDto.setLastName(account.getLastName());
        accountInfoRespDto.setAddress(account.getAddress());
        accountInfoRespDto.setBalance(account.getBalance());
        accountInfoRespDto.setAccountType(account.getAccountType());
        return accountInfoRespDto;
    }
}
