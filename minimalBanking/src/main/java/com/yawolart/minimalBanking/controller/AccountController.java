package com.yawolart.minimalBanking.controller;

import com.yawolart.minimalBanking.dto.AccountInfoRespDto;
import com.yawolart.minimalBanking.dto.CreateAccountReqDto;
import com.yawolart.minimalBanking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/newaccount")
    public ResponseEntity<AccountInfoRespDto> createAccount(
            @RequestBody
            CreateAccountReqDto reqAccount,

            @RequestParam
            int accountType
    ){
        AccountInfoRespDto respAccount;
        respAccount = accountService.createAccount(
                reqAccount,
                accountType
        );
        return ResponseEntity.ok(respAccount);
    }

}
