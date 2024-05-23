package io.ssafy.gameservice.game.costume.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "코스튬 리스트 응답 DTO")
public class CostumeListResDto {

    @Schema(description = "코스튬 식별자", example = "1")
    private Long costumeSeq;

    @Schema(description = "코스튬 이름", example = "테스트")
    private String costumeName;

    @Schema(description = "코스튬 이미지", example = "테스트")
    private String costumeImage;

    @Schema(description = "코스튬 등급", example = "normal")
    private String costumeGrade;

    @Schema(description = "코스튬 코인 가격", example = "100")
    private Integer costumeCoinPrice;

    @Schema(description = "코스튬 크리스탈 가격", example = "100")
    private Integer costumeCrystalPrice;

    @Schema(description = "코스튬 보유 여부", example = "true")
    private boolean isHave;

}
