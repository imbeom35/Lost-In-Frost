package io.ssafy.paymentsservice.payments.controller;


import io.micrometer.core.annotation.Timed;
import io.ssafy.paymentsservice.payments.dto.request.PaymentReqDto;
import io.ssafy.paymentsservice.payments.service.PaymentQueryService;
import io.ssafy.paymentsservice.payments.service.PaymentService;
import io.ssafy.paymentsservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/toss")
@Tag(name = "결제 도메인", description = "토스 API")
public class TossController {

    private final PaymentService paymentService;
    private final PaymentQueryService paymentQueryService;

    @Timed(value = "payments.toss.request", longTask = true)
    @Operation(summary = "토스 결제 요청")
    @PostMapping("/request")
    public Response<?> requestTossPayment (@RequestBody PaymentReqDto paymentReqDto, @AuthenticationPrincipal User user) {
        log.info("## 토스 결제 요청!! : {}", user.getUsername());
        return paymentService.requestTossPayment(paymentReqDto.toEntity(), user.getUsername(), paymentReqDto);
    }

    @Timed(value = "payments.toss.success", longTask = true)
    @Operation(summary = "토스 결제 성공시")
    @GetMapping("/success")
    public Response<?> tossPaymentsSuccess (@RequestParam String orderId, @RequestParam  String paymentKey, @RequestParam String amount, @AuthenticationPrincipal User user) {
        log.info("## 토스 결제 성공!!");
        return paymentService.tossPaymentSuccess(paymentKey, orderId, Integer.parseInt(amount), user.getUsername());
    }

    @Timed(value = "payments.toss.fail", longTask = true)
    @Operation(summary = "토스 결제 실패시")
    @GetMapping("/fail")
    public Response<?> tossPaymentsFail (@RequestParam String code, @RequestParam String message, @RequestParam String orderId) {
        log.info("## 토스 결제 실패!!");
        return paymentService.tossPaymentFail(code, message, orderId);
    }

    @Timed(value = "payments.toss.cancel", longTask = true)
    @Operation(summary = "토스 결제 취소시")
    @PostMapping("/cancel")
    public Response<?> tossPaymentsCancelPoint (@AuthenticationPrincipal User user, @RequestParam String paymentKey, @RequestParam String cancelReason) {
        log.info("## 토스 결제 취소!!");
        return paymentService.tossPaymentCancelCrystal(user.getUsername(), paymentKey, cancelReason);
    }

    @Timed(value = "payments.toss.history", longTask = true)
    @Operation(summary = "토스 결제 기록 조회")
    @GetMapping("/list")
    public Response<?> getPaymentsChargingHistory (@PageableDefault Pageable pageable, @AuthenticationPrincipal User user) {
        log.info("## 결제 기록을 조회합니다.");
        return paymentQueryService.getPaymentHistory(pageable, user.getUsername());
    }
}
