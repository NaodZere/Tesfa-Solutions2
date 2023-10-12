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

import com.asm.tesfaeribank.domain.Transaction;
import com.asm.tesfaeribank.dto.TransactionDto;
import com.asm.tesfaeribank.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    //test
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;


    @Test
    void testAddTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Transaction>>any())).thenReturn(transaction);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        assertSame(transactionDto, transactionServiceImpl.addTransaction(transactionDto));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Transaction>>any());
    }


    @Test
    void testGetTransactionById() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any())).thenReturn(transactionDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(transactionDto, transactionServiceImpl.getTransactionById("42"));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any());
        verify(transactionRepository).findById(Mockito.<String>any());
    }



    @Test
    void testGetAllTransaction() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any())).thenReturn(transactionDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionRepository.findAll()).thenReturn(transactionList);
        assertEquals(1, transactionServiceImpl.getAllTransaction().size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any());
        verify(transactionRepository).findAll();
    }


    @Test
    void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Transaction>>any())).thenReturn(transaction);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        assertThrows(RuntimeException.class, () -> transactionServiceImpl.updateTransaction("42", transactionDto));
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }


    @Test
    void testDeleteTransactionById() {
        doNothing().when(transactionRepository).deleteById(Mockito.<String>any());
        transactionServiceImpl.deleteTransactionById("42");
        verify(transactionRepository).deleteById(Mockito.<String>any());
        assertTrue(transactionServiceImpl.getAllTransaction().isEmpty());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testGetTransactionByDateTime() {


        transactionServiceImpl.getTransactionByDateTime("2020-03-01");
    }


    @Test
    void testGetTransactionByNumber() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any())).thenReturn(transactionDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        Optional<List<Transaction>> ofResult = Optional.of(transactionList);
        when(transactionRepository.findByNumber(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals(1, transactionServiceImpl.getTransactionByNumber("42").size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any());
        verify(transactionRepository).findByNumber(Mockito.<String>any());
    }


    @Test
    void testGetTransactionByFromId() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any())).thenReturn(transactionDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        Optional<List<Transaction>> ofResult = Optional.of(transactionList);
        when(transactionRepository.findByFromId(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals(1, transactionServiceImpl.getTransactionByFromId("42").size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any());
        verify(transactionRepository).findByFromId(Mockito.<String>any());
    }



    @Test
    void testGetTransactionByToId() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any())).thenReturn(transactionDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        Optional<List<Transaction>> ofResult = Optional.of(transactionList);
        when(transactionRepository.findByToId(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals(1, transactionServiceImpl.getTransactionByToId("42").size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any());
        verify(transactionRepository).findByToId(Mockito.<String>any());
    }


    @Test
    void testGetTransactionByType() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any())).thenReturn(transactionDto);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setBalance(10.0d);
        transaction.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setFromId("42");
        transaction.setId("42");
        transaction.setNumber("42");
        transaction.setToId("42");
        transaction.setType("Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        Optional<List<Transaction>> ofResult = Optional.of(transactionList);
        when(transactionRepository.findByType(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals(1, transactionServiceImpl.getTransactionByType("Type").size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<TransactionDto>>any());
        verify(transactionRepository).findByType(Mockito.<String>any());
    }

}

