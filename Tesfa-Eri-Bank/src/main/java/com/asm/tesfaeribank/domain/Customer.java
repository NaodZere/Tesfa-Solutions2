package com.asm.tesfaeribank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String username;


//    @DocumentReference
//    private List<Account> accounts;


}