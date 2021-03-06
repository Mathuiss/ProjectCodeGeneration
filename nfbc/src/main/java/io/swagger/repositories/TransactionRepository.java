package io.swagger.repositories;

import io.swagger.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Iterable<Transaction> findTransactionByUserId(long id);
}
