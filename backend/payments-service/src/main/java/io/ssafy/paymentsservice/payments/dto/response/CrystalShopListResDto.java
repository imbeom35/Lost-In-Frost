package io.ssafy.paymentsservice.payments.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "크리스탈 상점 리스트 응답 DTO")
public class CrystalShopListResDto {

    @Schema(name = "크리스탈 상점 식별자", example = "1")
    private Long crystalShopSeq;

    @Schema(name = "크리스탈 이름", example = "크리스탈 한 묶음")
    private String crystalShopCrystalName;

    @Schema(name = "크리스탈 개수", example = "100")
    private Integer crystalShopCrystalAmount;

    @Schema(name = "크리스탈 가격", example = "10000")
    private Integer crystalShopCrystalPrice;

    @Schema(name = "크리스탈 이미지", example = "CrystalShop1")
    private String crystalImage;
}
