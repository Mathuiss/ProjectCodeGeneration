package io.swagger.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}