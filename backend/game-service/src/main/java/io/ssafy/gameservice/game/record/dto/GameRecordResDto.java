package io.ssafy.gameservice.game.record.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게임 기록 정보 응답 DTO")
public class GameRecordResDto {

    @Schema(description = "게임 기록 식별자")
    private Long seq;
    
    @Schema(description = "방 식별자")
    private String roomId;
    
    @Schema(description = "회원 식별자")
    private String memberId;

    @Schema(description = "회원 닉네임")
    private String memberNickname;

    @Schema(description = "회원 코스튬")
    private String memberImage;

    @Schema(description = "플레이 타임")
    private Integer finishTime;

    @Schema(description = "플레이 점수")
    private Integer score;

    @Schema(description = "게임 시작 시간")
    private String startAt;

    @Schema(description = "게임 클리어 여부")
    private boolean isClear;

    @Schema(description = "게임 난이도")
    private float difficulty;
}
