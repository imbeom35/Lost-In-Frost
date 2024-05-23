package io.ssafy.rankservice.rank.repository;


import io.ssafy.rankservice.rank.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Ranking, Long> {


    Optional<Ranking> findByMemberId(String memberId);
}
