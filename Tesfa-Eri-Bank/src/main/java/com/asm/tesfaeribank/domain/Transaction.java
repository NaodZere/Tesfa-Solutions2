package com.asm.tesfaeribank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    private LocalDateTime dateTime;

    private String number;


    private Double amount;


    private String type;
    private String fromId;


    private String toId;

    private Double balance;


}
