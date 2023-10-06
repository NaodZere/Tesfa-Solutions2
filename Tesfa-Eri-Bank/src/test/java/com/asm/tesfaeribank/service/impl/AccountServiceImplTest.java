package com.asm.tesfaeribank.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.domain.Account;
import com.asm.tesfaeribank.domain.Transaction;
import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.repository.AccountRepository;
import com.asm.tesfaeribank.repository.TransactionRepository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TransactionRepository transactionRepository;


    @Test
    void testAddAccount() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> accountServiceImpl.addAccount(new AccountDto()));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Account>>any());
    }


    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        AccountDto accountDto = new AccountDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any())).thenReturn(accountDto);
        assertSame(accountDto, accountServiceImpl.getAccountById("3"));
        verify(accountRepository).findById(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any());
    }



    @Test
    void testGetAllAccounts() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountRepository.findAll()).thenReturn(accountList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any())).thenReturn(new AccountDto());
        assertEquals(1, accountServiceImpl.getAllAccounts().size());
        verify(accountRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any());
    }



    @Test
    void testUpdateAccount() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        Account account2 = new Account();
        account2.setBalance(10.0d);
        account2.setId("42");
        account2.setTransactions(new ArrayList<>());
        account2.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Account>>any())).thenReturn(account2);
        assertThrows(RuntimeException.class, () -> accountServiceImpl.updateAccount("42", new AccountDto()));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }




    @Test
    void testDeleteAccount() {
        doNothing().when(accountRepository).deleteById(Mockito.<String>any());
        accountServiceImpl.deleteAccount("42");
        verify(accountRepository).deleteById(Mockito.<String>any());
        assertTrue(accountServiceImpl.getAllAccounts().isEmpty());
    }




    @Test
    void testDeposit() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        Optional<Account> ofResult = Optional.of(account);

        Account account2 = new Account();
        account2.setBalance(10.0d);
        account2.setId("42");
        account2.setTransactions(new ArrayList<>());
        account2.setType("Type");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        AccountDto accountDto = new AccountDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any())).thenReturn(accountDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        assertSame(accountDto, accountServiceImpl.deposit("42", 10.0d));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }



    @Test
    void testWithdraw() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        Optional<Account> ofResult = Optional.of(account);

        Account account2 = new Account();
        account2.setBalance(10.0d);
        account2.setId("42");
        account2.setTransactions(new ArrayList<>());
        account2.setType("Type");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        AccountDto accountDto = new AccountDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any())).thenReturn(accountDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        assertSame(accountDto, accountServiceImpl.withdraw("42", 10.0d));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }


    @Test
    void testTransfer() {
        Account account = new Account();
        account.setBalance(10.0d);
        account.setId("42");
        account.setTransactions(new ArrayList<>());
        account.setType("Type");
        Optional<Account> ofResult = Optional.of(account);

        Account account2 = new Account();
        account2.setBalance(10.0d);
        account2.setId("42");
        account2.setTransactions(new ArrayList<>());
        account2.setType("Type");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        AccountDto accountDto = new AccountDto();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any())).thenReturn(accountDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        assertSame(accountDto, accountServiceImpl.transfer("42", "42", 10.0d));
        verify(accountRepository, atLeast(1)).save(Mockito.<Account>any());
        verify(accountRepository, atLeast(1)).findById(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<AccountDto>>any());
        verify(transactionRepository, atLeast(1)).save(Mockito.<Transaction>any());
    }


}

