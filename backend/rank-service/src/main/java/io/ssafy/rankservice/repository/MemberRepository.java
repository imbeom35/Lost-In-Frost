package io.ssafy.rankservice.repository;


import io.ssafy.rankservice.entity.Member;
import io.ssafy.rankservice.enums.AuthProvider;
import io.ssafy.rankservice.enums.Role;
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

    // 관리자가 아닌 회원만 조회하는 메서드
    List<Member> findByRoleIsNotOrderByLevelDescExperienceDesc(Role role);
}
