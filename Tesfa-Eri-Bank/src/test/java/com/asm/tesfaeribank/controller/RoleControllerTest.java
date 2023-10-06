package com.asm.tesfaeribank.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.dto.RoleDto;
import com.asm.tesfaeribank.service.RoleService;
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

@ContextConfiguration(classes = {RoleController.class})
@ExtendWith(SpringExtension.class)
class RoleControllerTest {
    @Autowired
    private RoleController roleController;

    @MockBean
    private RoleService roleService;


    @Test
    void testGetRoleById() throws Exception {
        when(roleService.getRoleById(Mockito.<String>any())).thenReturn(new RoleDto("42", "Name"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/roles/{id}", "42");
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":\"42\",\"name\":\"Name\"}"));
    }

    @Test
    void testGetRoleById2() throws Exception {
        when(roleService.getRoles()).thenReturn(new ArrayList<>());
        when(roleService.getRoleById(Mockito.<String>any())).thenReturn(new RoleDto("42", "Name"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/roles/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testCreateRole() throws Exception {
        when(roleService.addRole(Mockito.<RoleDto>any())).thenReturn(new RoleDto("42", "Name"));

        RoleDto roleDto = new RoleDto();
        roleDto.setId("42");
        roleDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(roleDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":\"42\",\"name\":\"Name\"}"));
    }


    @Test
    void testDeleteRole() throws Exception {
        doNothing().when(roleService).deleteRole(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/roles/{id}", "42");
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteRole2() throws Exception {
        doNothing().when(roleService).deleteRole(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/roles/{id}", "42");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testUpdateRole() throws Exception {
        when(roleService.updateRole(Mockito.<String>any(), Mockito.<RoleDto>any())).thenReturn(new RoleDto("42", "Name"));

        RoleDto roleDto = new RoleDto();
        roleDto.setId("42");
        roleDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(roleDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/roles/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":\"42\",\"name\":\"Name\"}"));
    }


    @Test
    void testGetRole() throws Exception {
        when(roleService.getRoles()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/roles");
        MockMvcBuilders.standaloneSetup(roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

