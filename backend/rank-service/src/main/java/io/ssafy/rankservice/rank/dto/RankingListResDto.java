package io.ssafy.rankservice.rank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema( description = "랭킹 리스트 응답 DTO")
public class RankingListResDto {

    @Schema(description = "랭킹 식별자 및 랭크 순위")
    private Long rankSeq;

    @Schema(description = "회원 식별자")
    private String memberId;

    @Schema(description = "회원 닉네임")
    private String memberNickname;

    @Schema(description = "회원 코스튬")
    private String memberCostume;

    @Schema(description = "회원 레벨")
    private int memberLevel;

    @Schema(description = "회원 경험치")
    private int memberExperience;

    @Schema(description = "회원 상태 메세지")
    private String memberMessage;

    @Schema(description = "회원 게임 플레이 횟수")
    private int memberGamePlayCount;
}
