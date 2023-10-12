package com.asm.tesfaeribank.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.domain.Account;
import com.asm.tesfaeribank.domain.Customer;
import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.dto.CustomerDto;
import com.asm.tesfaeribank.repository.AccountRepository;
import com.asm.tesfaeribank.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
//test

class CustomerServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private ModelMapper modelMapper;



    @Test
    void testGetAllCustomers() {
        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CustomerDto>>any())).thenReturn(customerDto);
        assertEquals(1, customerServiceImpl.getAllCustomers().size());
        verify(customerRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CustomerDto>>any());
    }


    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CustomerDto>>any())).thenReturn(customerDto);
        assertSame(customerDto, customerServiceImpl.getCustomerById("42"));
        verify(customerRepository).findById(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CustomerDto>>any());
    }

    @Test
    void testSaveCustomer() {
        when(customerRepository.existsByUsername(Mockito.<String>any())).thenReturn(true);

        CustomerDto customer = new CustomerDto();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.saveCustomer(customer));
        verify(customerRepository).existsByUsername(Mockito.<String>any());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setAccounts(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId("42");
        customer2.setLastName("Doe");
        customer2.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Customer>>any())).thenReturn(customer2);

        CustomerDto customer3 = new CustomerDto();
        customer3.setAccounts(new ArrayList<>());
        customer3.setEmail("jane.doe@example.org");
        customer3.setFirstName("Jane");
        customer3.setId("42");
        customer3.setLastName("Doe");
        customer3.setUsername("janedoe");
        assertThrows(RuntimeException.class, () -> customerServiceImpl.updateCustomer("42", customer3));
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(Mockito.<String>any());
        customerServiceImpl.deleteCustomer("42");
        verify(customerRepository).deleteById(Mockito.<String>any());
        assertTrue(customerServiceImpl.getAllCustomers().isEmpty());
    }

    @Test
    void testGetAllAccounts() {
        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertTrue(customerServiceImpl.getAllAccounts("42").isEmpty());
        verify(customerRepository).findById(Mockito.<String>any());
    }

    @Test
    void testAddAccountToCustomer() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAccounts(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId("42");
        customer2.setLastName("Doe");
        customer2.setUsername("janedoe");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Account account2 = new Account();
        account2.setBalance(10.0d);
        account2.setId("42");
        account2.setTransactions(new ArrayList<>());
        account2.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Account>>any())).thenReturn(account2);
        customerServiceImpl.addAccountToCustomer("42", new AccountDto());
        verify(accountRepository).save(Mockito.<Account>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findById(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Account>>any());
    }

    @Test
    void testDeleteAccountFromCustomer() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        Optional<Account> ofResult = Optional.of(account);
        doNothing().when(accountRepository).deleteById(Mockito.<String>any());
        when(accountRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        Optional<Customer> ofResult2 = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAccounts(new ArrayList<>());
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setId("42");
        customer2.setLastName("Doe");
        customer2.setUsername("janedoe");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        customerServiceImpl.deleteAccountFromCustomer("42", "42");
        verify(accountRepository).findById(Mockito.<String>any());
        verify(accountRepository).deleteById(Mockito.<String>any());
        verify(customerRepository).save(Mockito.<Customer>any());
        verify(customerRepository).findById(Mockito.<String>any());
    }


    @Test
    void testGetCustomerByUsername() {
        Customer customer = new Customer();
        customer.setAccounts(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId("42");
        customer.setLastName("Doe");
        customer.setUsername("janedoe");
        when(customerRepository.findByUsername(Mockito.<String>any())).thenReturn(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<CustomerDto>>any())).thenReturn(customerDto);
        assertSame(customerDto, customerServiceImpl.getCustomerByUsername("janedoe"));
        verify(customerRepository).findByUsername(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<CustomerDto>>any());
    }

}

