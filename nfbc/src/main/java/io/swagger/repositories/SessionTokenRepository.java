package io.swagger.repositories;

import io.swagger.model.SessionToken;
import org.springframework.data.repository.CrudRepository;

public interface SessionTokenRepository extends CrudRepository<SessionToken, String> {
}
