package com.asm.tesfaeribank.service.impl;

import com.asm.tesfaeribank.domain.Transaction;
import com.asm.tesfaeribank.dto.TransactionDto;
import com.asm.tesfaeribank.repository.TransactionRepository;
import com.asm.tesfaeribank.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionDto addTransaction(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        return transactionDto;
    }

    @Override
    public TransactionDto getTransactionById(String transactionId) {
        try{
            Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
            return modelMapper.map(transaction, TransactionDto.class);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDto> getAllTransaction() {
        try{
            List<Transaction> transactions = transactionRepository.findAll();
            return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).toList();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TransactionDto updateTransaction(String transactionId, TransactionDto transactionDto) {
        try{
            Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
            transaction.setId(transactionId);
            return modelMapper.map(transaction, TransactionDto.class);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteTransactionById(String transactionId) {
        try{
            transactionRepository.deleteById(transactionId);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDto> getTransactionByDateTime(String dateTime) {

        LocalDateTime dateTime1 = LocalDateTime.parse(dateTime);
        try {
            List<Transaction> transactions = transactionRepository.findByDateTime(dateTime1).orElse(null);
            return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).toList();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDto> getTransactionByNumber(String number) {

        try {
            List<Transaction> transactions = transactionRepository.findByNumber(number).orElse(null);
            return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).toList();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<TransactionDto> getTransactionByFromId(String id) {
       try {
            List<Transaction> transactions = transactionRepository.findByFromId(id).orElse(null);
            return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).toList();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
       }
    }

    @Override
    public List<TransactionDto> getTransactionByToId(String id) {
        try {
            List<Transaction> transactions = transactionRepository.findByToId(id).orElse(null);
            return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).toList();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDto> getTransactionByType(String type) {
        try {
            List<Transaction> transactions = transactionRepository.findByType(type).orElse(null);
            return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).toList();
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
//    withdraw
//    deposit
//    transfer
}
