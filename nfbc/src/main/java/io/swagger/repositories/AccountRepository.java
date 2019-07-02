package io.swagger.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    // @Query("select * from Account where userId = ?1")
    public Iterable<Account> findAccountsByUserId(Long userId);
}