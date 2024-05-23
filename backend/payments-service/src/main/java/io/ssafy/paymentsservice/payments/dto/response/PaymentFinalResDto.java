package io.ssafy.paymentsservice.payments.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "결제 최종 응답 dto")
public class PaymentFinalResDto {

    @Schema(description = "결제 성공 여부")
    private boolean isSuccess;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class paymentFail extends PaymentFinalResDto{

        @Schema(description = "결제 실패 코드")
        private String code;

        @Schema(description = "결제 실패 메시지")
        private String message;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class paymentSuccess extends PaymentFinalResDto{

        @Schema(description = "결제 고유 번호")
        private String orderId;

        @Schema(description = "결제 요청 시각")
        private String requestedAt;

        @Schema(description = "결제 승인 시각")
        private String approvedAt;

        @Schema(description = "결제 명")
        private String orderName;

        @Schema(description = "결제 방식")
        private String method;

        private EasyPayDto easyPay;

        private CardDto card;
    }



}

