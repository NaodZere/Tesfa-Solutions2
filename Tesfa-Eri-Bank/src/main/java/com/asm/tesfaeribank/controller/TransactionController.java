//package com.asm.tesfaeribank.controller;
//
//import com.asm.tesfaeribank.dto.TransactionDto;
//import com.asm.tesfaeribank.service.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@CrossOrigin
//@RequestMapping("/transactions")
//@RestController
//public class TransactionController {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @PostMapping(value = "/add/transaction")
//    public TransactionDto addTransaction(@RequestBody TransactionDto transactionDto) {
//        return transactionService.addTransaction(transactionDto);
//    }
//
//    @GetMapping(value = "/get/transaction/{transactionId}")
//    public TransactionDto getTransactionById(@PathVariable String transactionId) {
//        return transactionService.getTransactionById(transactionId);
//    }
//
//    @GetMapping(value = "get/transactions")
//    public List<TransactionDto> getAllTransactions() {
//        return transactionService.getAllTransaction();
//    }
//
//    @PutMapping(value = "/update/transaction/{transactionId}")
//    public TransactionDto updateTransaction(@PathVariable String transactionId, @RequestBody TransactionDto transactionDto) {
//        return transactionService.updateTransaction(transactionId, transactionDto);
//    }
//
//    @DeleteMapping(value = "delete/transaction/{transactionId}")
//    public void deleteTransaction(@PathVariable String transactionId) {
//        transactionService.deleteTransactionById(transactionId);
//    }
//
//    @GetMapping(value = "search/transactions/dateTime/{dateTime}")
//    public List<TransactionDto> getTransactionsByDateTime(@PathVariable String dateTime) {
//        return transactionService.getTransactionByDateTime(dateTime);
//    }
//
//    @GetMapping(value = "search/transactions/from/{accountId}")
//    public List<TransactionDto> getTransactionsByFromAccountId(@PathVariable String accountId) {
//        return transactionService.getTransactionByFromId(accountId);
//    }
//
//    @GetMapping(value = "search/transactions/to/{accountId}")
//    public List<TransactionDto> getTransactionsByToAccountId(@PathVariable String accountId) {
//        return transactionService.getTransactionByToId(accountId);
//    }
//
//    @GetMapping(value = "search/transactions/number/{number}")
//    public List<TransactionDto> getTransactionsByNumber(@PathVariable String number) {
//        return transactionService.getTransactionByNumber(number);
//    }
//
//    @GetMapping(value = "search/transactions/type/{type}")
//    public List<TransactionDto> getTransactionsByType(@PathVariable String type) {
//        return transactionService.getTransactionByType(type);
//    }
//}
