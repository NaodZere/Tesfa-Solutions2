package com.asm.tesfaeribank.service.impl;

import com.asm.tesfaeribank.domain.Account;
import com.asm.tesfaeribank.domain.Transaction;
import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.repository.AccountRepository;
import com.asm.tesfaeribank.repository.TransactionRepository;
import com.asm.tesfaeribank.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        Account account1 = accountRepository.save(account);
        return modelMapper.map(account1, AccountDto.class);
    }

    @Override
    public AccountDto getAccountById(String idAccount) {
        try {
            Account account = accountRepository.findById(idAccount).get();
            return modelMapper.map(account, AccountDto.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        try {
            List<Account> accounts = accountRepository.findAll();
            return accounts.stream().map(acc -> (modelMapper.map(acc, AccountDto.class))).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public AccountDto updateAccount(String accountId, AccountDto accountDto) {
        try {
            Account account = modelMapper.map(accountDto, Account.class);
            account.setId(accountId);
            Account account1 = accountRepository.save(account);
            return modelMapper.map(account1, AccountDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void deleteAccount(String id) {
        try {
            accountRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

//    TODO: Implement the following methods

    @Override
    public AccountDto deposit(String accountId, Double amount) {
        try {
            Account account = accountRepository.findById(accountId).get();
            account.setBalance(account.getBalance() + amount);
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setBalance(account.getBalance());
            transaction.setType("deposit");
            transaction.setToId(account.getId());
            transaction.setDateTime(LocalDateTime.now());
            transaction = transactionRepository.save(transaction);
            account.getTransactions().add(transaction);
            return modelMapper.map(accountRepository.save(account), AccountDto.class);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public AccountDto withdraw(String accountId, Double amount) {
        try {
            Account account = accountRepository.findById(accountId).get();
            account.setBalance(account.getBalance() - amount);
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setType("withdraw");
            transaction.setBalance(account.getBalance());
            transaction.setFromId(account.getId());
            transaction.setDateTime(LocalDateTime.now());
            transaction = transactionRepository.save(transaction);
            account.getTransactions().add(transaction);
            return modelMapper.map(accountRepository.save(account), AccountDto.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public AccountDto transfer(String fromAccountId, String toAccountId, Double amount) {
        try {
            Account fromAccount = accountRepository.findById(fromAccountId).get();
            Account toAccount = accountRepository.findById(toAccountId).get();
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setType("transfer");
            transaction.setBalance(fromAccount.getBalance());
            transaction.setFromId(fromAccount.getId());
            transaction.setToId(toAccount.getId());
            transaction.setDateTime(LocalDateTime.now());
            transaction = transactionRepository.save(transaction);
            fromAccount.getTransactions().add(transaction);
            toAccount.getTransactions().add(transaction);
            accountRepository.save(toAccount);
            fromAccount = accountRepository.save(fromAccount);
            return modelMapper.map(fromAccount, AccountDto.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}