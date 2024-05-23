package io.ssafy.userservice.user.mycostume.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "내 코스튬 응답 DTO")
public class MyCostumeResDto {

    @Schema(description = "내 코스튬 식별자", example = "1")
    private Long myCostumeSeq;

    @Schema(description = "코스튬 식별자", example = "1")
    private Long costumeSeq;

    @Schema(description = "코스튬 이름", example = "테스트")
    private String costumeName;

    @Schema(description = "코스튬 이미지", example = "테스트")
    private String costumeImage;

    @Schema(description = "코스튬 등급", example = "테스트")
    private String costumeGrade;

    @Schema(description = "구매 내역 식별자", example = "1")
    private Long purchaseHistorySeq;
}
