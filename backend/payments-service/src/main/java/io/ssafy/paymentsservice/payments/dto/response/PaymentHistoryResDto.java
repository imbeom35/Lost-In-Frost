package io.ssafy.paymentsservice.payments.dto.response;

import io.ssafy.paymentsservice.payments.enums.PayType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "결제 내역 응답 DTO")
public class PaymentHistoryResDto {

    @Schema(description = "결제 식별자")
    private Long paymentSeq;
    
    @Schema(description = "결제 방식")
    private PayType paymentPayType;
    
    @Schema(description = "결제 금액")
    private Integer paymentAmount;
    
    @Schema(description = "결제 주문명")
    private String paymentOrderName;
    
    @Schema(description = "결제 주문번호")
    private String paymentOrderId;
    
    @Schema(description = "결제 성공 여부")
    private Boolean paymentPaySuccessStatus;
    
    @Schema(description = "크리스탈 상점 크리스탈 개수")
    private Integer crystalShopCrystalAmount;

    @Schema(description = "결제 승인 시간")
    private String paymentApprovedAt;
}
