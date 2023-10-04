package com.asm.tesfaeribank.service;


import com.asm.tesfaeribank.dto.AccountDto;

import com.asm.tesfaeribank.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(String id);
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto updateCustomer(String id, CustomerDto customer);
    void deleteCustomer(String id);



    List<AccountDto> getAllAccounts(String id);

    void addAccountToCustomer(String customerId, AccountDto accountDto);

    void deleteAccountFromCustomer(String customerId, String accountId);



    CustomerDto getCustomerByUsername(String username);
}
