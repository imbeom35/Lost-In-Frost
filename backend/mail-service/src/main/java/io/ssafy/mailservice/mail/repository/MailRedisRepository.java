package io.ssafy.mailservice.mail.repository;


import io.ssafy.mailservice.mail.entity.MailRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MailRedisRepository extends CrudRepository<MailRedis, String> {

    Optional<MailRedis> findByNickname(String nickname);
}
