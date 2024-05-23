package io.ssafy.rankservice.rank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_seq")
    @Comment("랭킹 식별자 및 랭크 순위")
    private Long seq;

    @Comment("회원 식별자")
    private String memberId;

    @Comment("회원 닉네임")
    private String memberNickname;

    @Comment("회원 코스튬")
    private String memberCostume;

    @Comment("회원 레벨")
    private int memberLevel;

    @Comment("회원 경험치")
    private int memberExperience;

    @Comment("회원 상태 메세지")
    private String memberMessage;

    @Comment("회원 게임 플레이 횟수")
    private int memberGamePlayCount;


    public void updateRanking(String memberId, String memberNickname, String memberCostume, int memberLevel, int memberExperience, String memberMessage, int memberGamePlayCount) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.memberCostume = memberCostume;
        this.memberLevel = memberLevel;
        this.memberExperience = memberExperience;
        this.memberMessage = memberMessage;
        this.memberGamePlayCount = memberGamePlayCount;
    }

}
