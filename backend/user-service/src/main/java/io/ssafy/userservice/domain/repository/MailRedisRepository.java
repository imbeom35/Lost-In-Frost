package io.ssafy.userservice.domain.repository;


import io.ssafy.userservice.domain.entity.MailRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MailRedisRepository extends CrudRepository<MailRedis, String> {

    Optional<MailRedis> findByNickname(String nickname);
}
