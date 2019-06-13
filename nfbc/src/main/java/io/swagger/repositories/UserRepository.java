package io.swagger.repositories;

import io.swagger.model.Transaction;
import io.swagger.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<Transaction> findTransactionByUserId(long id);
}
