package com.asm.tesfaeribank.service.impl;


import com.asm.tesfaeribank.domain.Customer;
import com.asm.tesfaeribank.dto.CustomerDto;

import com.asm.tesfaeribank.repository.CustomerRepository;
import com.asm.tesfaeribank.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


//    @Autowired
//    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        try {
            Customer customer = customerRepository.findById(id).get();
            return modelMapper.map(customer, CustomerDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        try {
            if (customerRepository.existsByUsername(customer.getUsername()) || customerRepository.existsByEmail(customer.getEmail())) {
                throw new RuntimeException("Username or Email already exists");
            } else {
                Customer customerEntity = modelMapper.map(customer, Customer.class);
                Customer savedCustomer = customerRepository.save(customerEntity);
                return modelMapper.map(savedCustomer, CustomerDto.class);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public CustomerDto updateCustomer(String id, CustomerDto customer) {
        try {
            Customer customerEntity = modelMapper.map(customer, Customer.class);
            customerEntity.setId(id);
            return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Customer not updated");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Customer not deleted");
        }
    }

//    @Override
//    public List<AccountDto> getAllAccounts(String id) {
//        try {
//            Customer customer = customerRepository.findById(id).get();
//            return customer.getAccounts().stream()
//                    .map(account -> modelMapper.map(account, AccountDto.class))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new RuntimeException("Customer not found");
//        }
//    }
//
//    @Override
//    public void addAccountToCustomer(String customerId, AccountDto accountDto) {
//        try {
//            Customer customer = customerRepository.findById(customerId).get();
//            Account account = modelMapper.map(accountDto, Account.class);
//            account = accountRepository.save(account);
//            customer.getAccounts().add(account);
//            customerRepository.save(customer);
//        } catch (Exception e) {
//            throw new RuntimeException("Customer not found");
//        }
//
//    }
//
//    @Override
//    public void deleteAccountFromCustomer(String customerId, String accountId) {
//
//        try {
//            Customer customer = customerRepository.findById(customerId).get();
//            Account account = accountRepository.findById(accountId).get();
//            customer.getAccounts().remove(account);
//            accountRepository.deleteById(accountId);
//            customerRepository.save(customer);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }

    @Override
    public CustomerDto getCustomerByUsername(String username) {
        try {
            Customer customer = customerRepository.findByUsername(username);
            return modelMapper.map(customer, CustomerDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Customer not found");
        }
    }


}
