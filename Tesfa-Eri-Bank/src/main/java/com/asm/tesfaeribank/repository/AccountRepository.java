package com.asm.tesfaeribank.repository;

import august.bank.app.bankproject.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
