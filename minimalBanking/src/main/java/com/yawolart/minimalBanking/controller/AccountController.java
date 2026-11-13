package com.yawolart.minimalBanking.controller;

import com.yawolart.minimalBanking.dto.AccountInfoRespDto;
import com.yawolart.minimalBanking.dto.CreateAccountReqDto;
import com.yawolart.minimalBanking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/newaccount")
    public ResponseEntity<AccountInfoRespDto> openAccountController(
            @RequestBody
            CreateAccountReqDto reqAccount,

            @RequestParam
            int accountType
    ){
        AccountInfoRespDto respAccount;
        respAccount = accountService.openAccount(
                reqAccount,
                accountType
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respAccount);
    }

    @DeleteMapping("/closeaccount/{id}")
    public ResponseEntity<Boolean> closeAccountController(
            @PathVariable
            Long id
    ){
        accountService.closeAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/deposit")
    public ResponseEntity<AccountInfoRespDto> depositController(
            @RequestParam
            Long id,

            @RequestParam
            BigDecimal amount
    ){
        AccountInfoRespDto respDto = accountService.deposit(id,amount);
        return ResponseEntity.ok(respDto);
    }

}
