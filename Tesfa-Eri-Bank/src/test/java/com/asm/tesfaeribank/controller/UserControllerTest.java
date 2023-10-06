package com.asm.tesfaeribank.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.dto.UserDto;
import com.asm.tesfaeribank.security.JwtGenerator;
import com.asm.tesfaeribank.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtGenerator jwtGenerator;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testReadById() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        when(userService.readById(Mockito.<String>any())).thenReturn(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{id}", "42");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"roles\":[]}"));
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }


    @Test
    void testDeleteById() throws Exception {
        doNothing().when(userService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{id}", "42");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testLogin() throws Exception {
        when(jwtGenerator.generateToken(Mockito.<Authentication>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"accessToken\":\"ABC123\"}"));
    }


    @Test
    void testLogin2() throws Exception {
        when(jwtGenerator.generateToken(Mockito.<Authentication>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any())).thenThrow(new RuntimeException("foo"));

        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"accessToken\":\"Error Parsing JWT\"}"));
    }


    @Test
    void testReadByUsername() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        when(userService.findByUsername(Mockito.<String>any())).thenReturn(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/username/{username}",
                "janedoe");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"roles\":[]}"));
    }


    @Test
    void testReadByUsername2() throws Exception {
        when(userService.findByUsername(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/username/{username}",
                "janedoe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testUpdateById() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        when(userService.update(Mockito.<String>any(), Mockito.<UserDto>any())).thenReturn(userDto);

        UserDto userDto2 = new UserDto();
        userDto2.setEmail("jane.doe@example.org");
        userDto2.setId("42");
        userDto2.setPassword("iloveyou");
        userDto2.setRoles(new ArrayList<>());
        userDto2.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"roles\":[]}"));
    }
}

