package io.ssafy.userservice.user.mycostume.repository;


import io.ssafy.userservice.user.mycostume.entity.MyCostume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyCostumeRepository extends JpaRepository<MyCostume, Long> {

    Optional<MyCostume> findByMemberIdAndCostumeSeq(String memberId, Long costumeSeq);
    Optional<MyCostume> findByCostumeSeq(Long costumeSeq);
    Long countByMemberId(String memberId);
}
