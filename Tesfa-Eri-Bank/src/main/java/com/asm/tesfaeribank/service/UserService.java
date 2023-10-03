package com.asm.tesfaeribank.service;



import com.asm.tesfaeribank.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto create(UserDto user);
    public List<UserDto> readAll();
    public UserDto readById(String id);
    public UserDto update(String id, UserDto userDto);
    public void deleteById(String id);

    public UserDto login(UserDto userDto);


    UserDto findByUsername(String username);
}
