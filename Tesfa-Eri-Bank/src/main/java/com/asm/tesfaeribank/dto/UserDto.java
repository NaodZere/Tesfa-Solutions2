package com.asm.tesfaeribank.dto;

import com.asm.tesfaeribank.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private String id;

    private String username;

    private String email;


    private String password;

   // private List<Role> roles;
}
