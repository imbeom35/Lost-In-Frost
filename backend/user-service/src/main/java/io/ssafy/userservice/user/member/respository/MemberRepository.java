package io.ssafy.userservice.user.member.respository;


import io.ssafy.userservice.oauth2.enums.AuthProvider;
import io.ssafy.userservice.user.member.entity.Member;
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