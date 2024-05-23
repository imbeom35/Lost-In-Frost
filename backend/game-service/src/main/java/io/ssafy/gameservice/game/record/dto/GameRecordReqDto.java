package io.ssafy.gameservice.game.record.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "게임 기록 저장 요청 DTO")
public class GameRecordReqDto {

    @Schema(description = "플레이 타임")
    private int finishTime;

    @Schema(description = "클리어 여부")
    private boolean isClear;

    @Schema(description = "방 식별자")
    private String roomId;

    @Schema(description = "게임 시작 시간")
    private String startAt;

    @Schema(description = "게임 난이도")
    private float difficulty;
}
