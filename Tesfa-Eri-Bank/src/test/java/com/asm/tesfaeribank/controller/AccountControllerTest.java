package com.asm.tesfaeribank.controller;

import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;


    @Test
    void testAddAccount() throws Exception {
        when(accountService.addAccount(Mockito.<AccountDto>any())).thenReturn(new AccountDto());

        AccountDto accountDto = new AccountDto();
        accountDto.setBalance(10.0d);
        accountDto.setId("42");
        accountDto.setTransactions(new ArrayList<>());
        accountDto.setType("Type");
        String content = (new ObjectMapper()).writeValueAsString(accountDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"type\":null,\"balance\":null,\"transactions\":null}"));
    }
}

