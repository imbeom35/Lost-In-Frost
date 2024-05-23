package io.ssafy.paymentsservice.payments.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "카드 응답 dto")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {

    @Schema(description = "회사명", example = "KB국민카드")
    String company; // 회사명

    @Schema(description = "카드번호", example = "1234-1234-1234-1234")
    String number; // 카드번호

    @Schema(description = "할부 개월", example = "0")
    String installmentPlanMonths; // 할부 개월

    String isInterestFree;

    @Schema(description = "승인번호", example = "12345678")
    String approveNo; // 승인번호

    @Schema(description = "카드 포인트 사용 여부", example = "N")
    String useCardPoint; // 카드 포인트 사용 여부

    @Schema(description = "카드 타입", example = "체크카드, 신용카드, 선불카드, 기프트카드, 기타")
    String cardType; // 카드 타입

    @Schema(description = "카드 발급사", example = "KB국민카드")
    String ownerType; // 소유자 타입

    @Schema(description = "승인 상태", example = "승인")
    String acquireStatus; // 승인 상태

    @Schema(description = "영수증 URL", example = "https://kapi.kakaopay.com/v1/payment/result/success")
    String receiptUrl; // 영수증 URL
}