package com.asm.tesfaeribank.controller;

import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.dto.CustomerDto;
import com.asm.tesfaeribank.service.AccountService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asm.tesfaeribank.service.CustomerService;
import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private AccountService accountService;


    @GetMapping
    public List<CustomerDto> getAllCustomers() {

        return customerService.getAllCustomers();

    }

    @GetMapping("/username/{username}")

    public ResponseEntity<CustomerDto> getCustomerByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(customerService.getCustomerByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable String id, @RequestBody CustomerDto customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/accounts")
    public List<AccountDto> getCustomerAccounts(@PathVariable String id) {

        return customerService.getAllAccounts(id);

    }

    @GetMapping("/{id}/accounts/{accountId}")
    public AccountDto getCustomerAccountById(@PathVariable String id, @PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }


    @PostMapping("/{id}/accounts")
    public void addAccountToCustomer(@PathVariable String id, @RequestBody AccountDto accountDto) {
        customerService.addAccountToCustomer(id, accountDto);
    }

    @DeleteMapping("/{id}/accounts/{accountId}")
    public void deleteAccountFromCustomer(@PathVariable String id, @PathVariable String accountId) {
        customerService.deleteAccountFromCustomer(id, accountId);
    }

    @PutMapping(value = "/{id}/accounts/{accountId}/deposit")

    public AccountDto deposit(@PathVariable String accountId, @RequestParam double amount) {
        return accountService.deposit(accountId, amount);
    }

    @PutMapping(value = "/{id}/accounts/{accountId}/withdraw")
    public AccountDto withdraw(@PathVariable String accountId, @RequestParam double amount) {
        return accountService.withdraw(accountId, amount);
    }

    @PutMapping(value = "/{id}/accounts/{fromAccountId}/transfer/{toAccountId}")
    public AccountDto transfer(@PathVariable String fromAccountId, @PathVariable String toAccountId, @RequestParam Double amount) {
        return accountService.transfer(fromAccountId, toAccountId, amount);
    }



}
