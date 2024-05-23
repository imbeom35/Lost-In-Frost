package io.ssafy.paymentsservice.payments.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Schema(description = "아이템 구매 내역 조회 응답 DTO")
public class PurchaseHistoryListResDto {

    @Schema(description = "구매 내역 식별자", example = "1")
    private Long purchaseHistorySeq;

    @Schema(description = "구매 상태", example = "1")
    private Integer state;

    @Schema(description = "구매 수량", example = "1")
    private Integer quantity;

    @Schema(description = "재화 분류", example = "0")
    private Boolean classification;

    @Schema(description = "코스튬 식별자", example = "1")
    private Long costumeSeq;

    @Schema(description = "코스튬 이름", example = "man_business")
    private String costumeName;

    @Schema(description = "코스튬 가격", example = "1000")
    private Integer Price;

    @Schema(description = "코스튬 이미지", example = "man_business")
    private String costumeImage;

    @Schema(description = "코스튬 등급", example = "epic")
    private String costumeGrade;

    @Schema(description = "구매 일자", example = "2023-10-29 03:18:34.138995")
    private Timestamp purchaseDatetime;
}
