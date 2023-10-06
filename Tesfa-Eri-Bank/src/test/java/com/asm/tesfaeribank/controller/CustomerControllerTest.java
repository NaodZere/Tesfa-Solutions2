package com.asm.tesfaeribank.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.dto.AccountDto;
import com.asm.tesfaeribank.dto.CustomerDto;
import com.asm.tesfaeribank.service.AccountService;
import com.asm.tesfaeribank.service.CustomerService;
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

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @MockBean
    private AccountService accountService;

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;


    @Test
    void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetCustomerByUsername() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        when(customerService.getCustomerByUsername(Mockito.<String>any())).thenReturn(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/username/{username}",
                "janedoe");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\","
                                        + "\"accounts\":[]}"));
    }


    @Test
    void testGetCustomerById() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        when(customerService.getCustomerById(Mockito.<String>any())).thenReturn(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}", "42");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\","
                                        + "\"accounts\":[]}"));
    }


    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        when(customerService.updateCustomer(Mockito.<String>any(), Mockito.<CustomerDto>any())).thenReturn(customerDto);

        CustomerDto customerDto2 = new CustomerDto();
        customerDto2.setAccounts(new ArrayList<>());
        customerDto2.setEmail("jane.doe@example.org");
        customerDto2.setFirstName("Jane");
        customerDto2.setId("42");
        customerDto2.setLastName("Doe");
        customerDto2.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(customerDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customers/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\","
                                        + "\"accounts\":[]}"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/{id}", "42");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteCustomer2() throws Exception {
        doNothing().when(customerService).deleteCustomer(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customers/{id}", "42");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testAddAccountToCustomer() throws Exception {
        doNothing().when(customerService).addAccountToCustomer(Mockito.<String>any(), Mockito.<AccountDto>any());

        AccountDto accountDto = new AccountDto();
        accountDto.setBalance(10.0d);
        accountDto.setId("42");
        accountDto.setTransactions(new ArrayList<>());
        accountDto.setType("Type");
        String content = (new ObjectMapper()).writeValueAsString(accountDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers/{id}/accounts", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testDeleteAccountFromCustomer() throws Exception {
        doNothing().when(customerService).deleteAccountFromCustomer(Mockito.<String>any(), Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/customers/{id}/accounts/{accountId}", "42", "42");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testDeposit() throws Exception {
        when(accountService.deposit(Mockito.<String>any(), Mockito.<Double>any())).thenReturn(new AccountDto());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders
                .put("/customers/{id}/accounts/{accountId}/deposit", "42", "42");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("amount", String.valueOf(10.0d));
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"type\":null,\"balance\":null,\"transactions\":null}"));
    }


    @Test
    void testWithdraw() throws Exception {
        when(accountService.withdraw(Mockito.<String>any(), Mockito.<Double>any())).thenReturn(new AccountDto());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders
                .put("/customers/{id}/accounts/{accountId}/withdraw", "42", "42");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("amount", String.valueOf(10.0d));
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"type\":null,\"balance\":null,\"transactions\":null}"));
    }


    @Test
    void testTransfer() throws Exception {
        when(accountService.transfer(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Double>any()))
                .thenReturn(new AccountDto());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders
                .put("/customers/{id}/accounts/{fromAccountId}/transfer/{toAccountId}", "42", "42", "42");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("amount", String.valueOf(10.0d));
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"type\":null,\"balance\":null,\"transactions\":null}"));
    }


    @Test
    void testGetCustomerAccountById() throws Exception {
        when(accountService.getAccountById(Mockito.<String>any())).thenReturn(new AccountDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}/accounts/{accountId}",
                "42", "42");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"type\":null,\"balance\":null,\"transactions\":null}"));
    }

    @Test
    void testGetCustomerAccountById2() throws Exception {
        when(accountService.getAccountById(Mockito.<String>any())).thenReturn(new AccountDto());
        when(customerService.getAllAccounts(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}/accounts/{accountId}",
                "Uri Variables", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetCustomerAccounts() throws Exception {
        when(customerService.getAllAccounts(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/{id}/accounts", "42");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testSaveCustomer() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(new ArrayList<>());

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAccounts(new ArrayList<>());
        customerDto.setEmail("jane.doe@example.org");
        customerDto.setFirstName("Jane");
        customerDto.setId("42");
        customerDto.setLastName("Doe");
        customerDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(customerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

