package io.ssafy.gameservice.game.record.entity;

import io.ssafy.gameservice.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(indexes = {
        @Index(name = "idx_game_record_room_id", columnList = "game_record_room_id")
})
public class GameRecord {

    @Id
    @Comment("게임 기록 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_record_seq")
    private Long seq;

    @Comment("방 식별자")
    @Column(name = "game_record_room_id")
    private String roomId;

    @Comment("회원 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Comment("게임 기록 종료 시간")
    @Column(name = "game_record_finish_time")
    private int finishTime;

    @Comment("게임 클리어 여부")
    @Column(name = "game_record_is_clear")
    private boolean isClear;

    @Comment("게임 기록 시작 시간")
    @Column(name = "game_record_start_at")
    private String startAt;

    @Comment("게임 스코어")
    @Column(name = "game_record_score")
    private int score;

    @Comment("게임 난이도")
    @Column(name = "game_record_difficulty")
    private float difficulty;

    public void saveGameRecord(Member member, int finishTime, boolean isClear, int score) {
        this.member = member;
        this.finishTime = finishTime;
        this.isClear = isClear;
        this.score = score;
    }

}
