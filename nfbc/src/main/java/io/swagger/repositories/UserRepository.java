package io.swagger.repositories;

import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Iterable<Transaction> getTransactionById(Integer id);
}