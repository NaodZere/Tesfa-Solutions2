package com.asm.tesfaeribank.controller;


import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "")
    public AccountDto addAccount(@RequestBody AccountDto accountDto) {

        return accountService.addAccount(accountDto);
    }

    @GetMapping(value = "/{accountId}")
    public AccountDto getAccountById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping(value = "/{accountId}")
    public AccountDto updateAccount(@PathVariable String accountId, @RequestBody AccountDto accountDto) {
        return accountService.updateAccount(accountId, accountDto);
    }

    @DeleteMapping(value = "/{accountId}")
    public void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }





}
