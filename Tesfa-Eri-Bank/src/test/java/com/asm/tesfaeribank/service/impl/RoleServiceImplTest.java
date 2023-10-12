package com.asm.tesfaeribank.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.domain.Role;
import com.asm.tesfaeribank.dto.RoleDto;
import com.asm.tesfaeribank.repository.RoleRepository;

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

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;


    @Test
    void testGetRoles() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<RoleDto>>any())).thenReturn(new RoleDto("42", "Name"));

        Role role = new Role();
        role.setId("42");
        role.setName("Name");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(roleRepository.findAll()).thenReturn(roleList);
        assertEquals(1, roleServiceImpl.getRoles().size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<RoleDto>>any());
        verify(roleRepository).findAll();
    }


    @Test
    void testGetRoleById() {
        RoleDto roleDto = new RoleDto("42", "Name");

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<RoleDto>>any())).thenReturn(roleDto);

        Role role = new Role();
        role.setId("42");
        role.setName("Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(roleDto, roleServiceImpl.getRoleById("42"));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<RoleDto>>any());
        verify(roleRepository).findById(Mockito.<String>any());
    }

    @Test
    void testAddRole() {
        Role role = new Role();
        role.setId("42");
        role.setName("Name");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(role);

        Role role2 = new Role();
        role2.setId("42");
        role2.setName("Name");
        when(roleRepository.save(Mockito.<Role>any())).thenReturn(role2);
        assertThrows(RuntimeException.class, () -> roleServiceImpl.addRole(new RoleDto("42", "Name")));
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(roleRepository).save(Mockito.<Role>any());
    }

    @Test
    void testDeleteRole() {
        doNothing().when(roleRepository).deleteById(Mockito.<String>any());
        roleServiceImpl.deleteRole("42");
        verify(roleRepository).deleteById(Mockito.<String>any());
        assertTrue(roleServiceImpl.getRoles().isEmpty());
    }

    @Test
    void testUpdateRole() {
        Role role = new Role();
        role.setId("42");
        role.setName("Name");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Role>>any())).thenReturn(role);

        Role role2 = new Role();
        role2.setId("42");
        role2.setName("Name");
        when(roleRepository.save(Mockito.<Role>any())).thenReturn(role2);
        assertThrows(RuntimeException.class, () -> roleServiceImpl.updateRole("42", new RoleDto("42", "Name")));
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(roleRepository).save(Mockito.<Role>any());
    }


}

