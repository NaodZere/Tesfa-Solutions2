package com.asm.tesfaeribank.dto;

import com.asm.tesfaeribank.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;


    private List<Account> accounts;


}
