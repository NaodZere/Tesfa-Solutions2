package com.asm.tesfaeribank.service.impl;


import com.asm.tesfaeribank.domain.User;
import com.asm.tesfaeribank.dto.UserDto;
import com.asm.tesfaeribank.repository.UserRepository;
import com.asm.tesfaeribank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto create(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername()) || userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Username or Email already exists");
        }
        User user = modelMapper.map(userDto, User.class);


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User userSaved = userRepository.save(user);

            return modelMapper.map(userSaved, UserDto.class);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());

        }

    }

    @Override
    public List<UserDto> readAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto readById(String id) {
        try {
            User user = userRepository.findById(id).get();
            return modelMapper.map(user, UserDto.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDto update(String id, UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            user.setId(id);
            User userSaved = userRepository.save(user);
            return modelMapper.map(userSaved, UserDto.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            userRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public UserDto login(UserDto userDto) {
        try {


            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return userDto;
        } catch (RuntimeException e) {
            throw new RuntimeException("User Not Logged In");
        }
    }

    @Override
    public UserDto findByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username).get();
            return modelMapper.map(user, UserDto.class);
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException("User Not Found");
        }
    }


}