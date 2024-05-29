package org.geniuus.practice.auth.redis.repository;

import org.geniuus.practice.auth.redis.domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisTokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByRefreshToken(final String refreshToken);
}
