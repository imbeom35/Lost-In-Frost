package io.ssafy.paymentsservice.payments.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "포인트 결제 내역 응답 DTO")
public class CrystalPaymentsHistoryResDto {

    @Schema(description = "포인트 사용 내역 식별자")
    private Long crystalPaymentsHistorySeq;

//    private
}
