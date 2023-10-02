package com.asm.tesfaeribank.repository;



import com.asm.tesfaeribank.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends MongoRepository<Role, String> {




}
