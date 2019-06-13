package io.swagger.repositories;

import io.swagger.model.SessionToken;
import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionToken, String> {
}
