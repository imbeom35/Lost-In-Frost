package io.ssafy.paymentsservice.payments.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargingHistoryDto {

    private Long paymentHistorySeq;
    private Integer amount;
    private String orderName;
    private Boolean isPaySuccessStatus;
}
