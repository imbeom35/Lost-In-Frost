package io.ssafy.gameservice.game.record.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "게임 기록 저장 응답 DTO")
public class FinishGameResDto {

    private int level;

    private int experience;

    private int coin;
}
