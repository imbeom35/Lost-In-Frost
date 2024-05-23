package io.ssafy.authservice.repository;


import io.ssafy.authservice.entity.MailRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MailRedisRepository extends CrudRepository<MailRedis, String> {

    Optional<MailRedis> findByNickname(String nickname);
}
