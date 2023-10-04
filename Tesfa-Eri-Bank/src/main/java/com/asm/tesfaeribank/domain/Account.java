//package com.asm.tesfaeribank.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.DocumentReference;
//
//import java.util.List;
//
//@Document(collection = "accounts")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Account {
//
//    @Id
//    private String id;
//
//    private String type;
//
//    private Double balance;
//
//    @DocumentReference(lazy = true)
//    @JsonIgnore
//    private List<Transaction> transactions;
//
//}
