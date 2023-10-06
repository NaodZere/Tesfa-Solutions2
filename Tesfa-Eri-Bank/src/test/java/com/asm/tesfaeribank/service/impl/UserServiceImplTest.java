package com.asm.tesfaeribank.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asm.tesfaeribank.domain.User;
import com.asm.tesfaeribank.dto.UserDto;
import com.asm.tesfaeribank.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;



    @Test
    void testReadAll() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userServiceImpl.readAll().isEmpty());
        verify(userRepository).findAll();
    }


    @Test
    void testReadAll2() {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any())).thenReturn(userDto);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userServiceImpl.readAll().size());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any());
        verify(userRepository).findAll();
    }


    @Test
    void testReadById() {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any())).thenReturn(userDto);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(userDto, userServiceImpl.readById("42"));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any());
        verify(userRepository).findById(Mockito.<String>any());
    }




    @Test
    void testDeleteById() {
        doNothing().when(userRepository).deleteById(Mockito.<String>any());
        userServiceImpl.deleteById("42");
        verify(userRepository).deleteById(Mockito.<String>any());
    }




    @Test
    void testLogin() throws AuthenticationException {
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        assertSame(userDto, userServiceImpl.login(userDto));
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }



    @Test
    void testFindByUsername() {
        UserDto userDto = new UserDto();
        userDto.setEmail("jane.doe@example.org");
        userDto.setId("42");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUsername("janedoe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any())).thenReturn(userDto);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(userDto, userServiceImpl.findByUsername("janedoe"));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserDto>>any());
        verify(userRepository).findByUsername(Mockito.<String>any());
    }

}

