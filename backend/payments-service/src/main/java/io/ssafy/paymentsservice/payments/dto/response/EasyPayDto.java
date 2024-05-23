package io.ssafy.paymentsservice.payments.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "간편 결제 응답 dto")
public class EasyPayDto {

    @Schema(description = "간편 결제 제공자", example = "토스페이")
    private String provider;

    @Schema(description = "결제 금액", example = "1000")
    private int amount;

    @Schema(description = "할인 금액", example = "0")
    private int discountAmount;
}
