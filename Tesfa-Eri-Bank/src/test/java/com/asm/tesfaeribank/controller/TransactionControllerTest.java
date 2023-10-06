package com.asm.tesfaeribank.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.dto.TransactionDto;
import com.asm.tesfaeribank.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;


    @Test
    @Disabled("TODO: Complete this test")
    void testAddTransaction() throws Exception {

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/transactions/add/transaction")
                .contentType(MediaType.APPLICATION_JSON);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(transactionDto));
        MockMvcBuilders.standaloneSetup(transactionController).build().perform(requestBuilder);
    }


    @Test
    void testGetTransactionById() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        when(transactionService.getTransactionById(Mockito.<String>any())).thenReturn(transactionDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transactions/get/transaction/{transactionId}", "42");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"dateTime\":[1970,1,1,0,0],\"number\":\"42\",\"amount\":10.0,\"type\":\"Type\",\"from\":\"jane.doe@example"
                                        + ".org\",\"to\":\"alice.liddell@example.org\",\"balance\":10.0}"));
    }


    @Test
    void testDeleteTransaction() throws Exception {
        doNothing().when(transactionService).deleteTransactionById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/transactions/delete/transaction/{transactionId}", "42");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testDeleteTransaction2() throws Exception {
        doNothing().when(transactionService).deleteTransactionById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/transactions/delete/transaction/{transactionId}", "42");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testGetAllTransactions() throws Exception {
        when(transactionService.getAllTransaction()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/get/transactions");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetTransactionsByDateTime() throws Exception {
        when(transactionService.getTransactionByDateTime(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transactions/search/transactions/dateTime/{dateTime}", "2020-03-01");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetTransactionsByFromAccountId() throws Exception {
        when(transactionService.getTransactionByFromId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transactions/search/transactions/from/{accountId}", "42");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetTransactionsByNumber() throws Exception {
        when(transactionService.getTransactionByNumber(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transactions/search/transactions/number/{number}", "42");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetTransactionsByToAccountId() throws Exception {
        when(transactionService.getTransactionByToId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transactions/search/transactions/to/{accountId}", "42");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetTransactionsByType() throws Exception {
        when(transactionService.getTransactionByType(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/transactions/search/transactions/type/{type}", "Type");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateTransaction() throws Exception {


        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/transactions/update/transaction/{transactionId}", "42")
                .contentType(MediaType.APPLICATION_JSON);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10.0d);
        transactionDto.setBalance(10.0d);
        transactionDto.setDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transactionDto.setFrom("jane.doe@example.org");
        transactionDto.setId("42");
        transactionDto.setNumber("42");
        transactionDto.setTo("alice.liddell@example.org");
        transactionDto.setType("Type");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(transactionDto));
        MockMvcBuilders.standaloneSetup(transactionController).build().perform(requestBuilder);
    }
}

