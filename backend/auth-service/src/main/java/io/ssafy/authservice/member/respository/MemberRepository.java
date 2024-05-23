package io.ssafy.authservice.member.respository;


import io.ssafy.authservice.member.entity.Member;
import io.ssafy.authservice.oauth2.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndAuthProviderAndIsDeleted(String email, AuthProvider authProvider, Boolean isDeleted);

    List<Member> findAllByEmailIs (String email);

    Optional<Member> findById (String id);

    Optional<Member> findByNicknameAndIsDeleted(String nickname, Boolean isDeleted);

    Optional<Member> findByEmail(String email);

    List<Member> findAllByOrderByLevelDescExperienceDesc();
}
