package io.ssafy.gameservice.game.record.repository;

import io.ssafy.gameservice.game.record.entity.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRecordRepository extends JpaRepository<GameRecord, String> {

    Optional<GameRecord> findByRoomIdAndMemberId(String roomId, String memberId);

    boolean existsByRoomId (String roomId);
}
