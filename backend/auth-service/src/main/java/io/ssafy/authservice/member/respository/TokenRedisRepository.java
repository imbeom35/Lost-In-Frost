package io.ssafy.authservice.member.respository;

import io.ssafy.authservice.member.entity.TokenRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRedisRepository extends CrudRepository<TokenRedis, String> {

    Optional<TokenRedis> findByAccessToken(String accessToken);
    boolean existsByAccessToken(String accessToken);
}
