package io.ssafy.paymentsservice.payments.dto.request;

import io.ssafy.paymentsservice.payments.entity.Payment;
import io.ssafy.paymentsservice.payments.enums.PayType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(name = "결제 요청 정보", description = "결제 요청 정보 DTO")
public class PaymentReqDto {

    @NotNull
    @Schema(name = "payType", description = "결제 타입", example = "CARD")
    private PayType payType;

    @NotNull
    @Schema(name = "amount", description = "결제 금액", example = "1000")
    private int amount;

    @NotNull
    @Schema(name = "orderName", description = "결제 상품명", example = "크리스탈")
    private String orderName;

    @Schema(name = "yourSuccessUrl", description = "결제 성공시 이동할 URL", example = "http://localhost:3000/toss-success")
    private String yourSuccessUrl;

    @Schema(name = "yourFailUrlL", description = "결제 실패시 이동할 URL", example = "http://localhost:3000/toss-fail")
    private String yourFailUrl;

    @Schema(name = "crystalShopSeq", description = "크리스탈 상점 식별자", example = "1")
    private Long crystalShopSeq;


    public Payment toEntity() {
        return Payment.builder()
                .payType(payType)
                .amount(amount)
                .orderName(orderName)
                .orderId(UUID.randomUUID().toString())
                .paySuccessStatus(false)
                .build();
    }

}
