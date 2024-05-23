package io.ssafy.mailservice.mail.repository;


import io.ssafy.mailservice.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {

    Optional<Mail> findByEmail(String email);
    Optional<Mail> findByMemberNickname(String nickname);
}
