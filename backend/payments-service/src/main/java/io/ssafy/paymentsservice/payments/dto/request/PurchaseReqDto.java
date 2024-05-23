package io.ssafy.paymentsservice.payments.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "아이템 구매 요청 DTO")
public class PurchaseReqDto {

    @Schema(description = "코스튬 식별자", example = "5")
    private Long costumeSeq;

    @Schema(description = "재화 분류 = crystal, coin", example = "crystal")
    private String classification;
}
