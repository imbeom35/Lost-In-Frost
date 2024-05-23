package io.ssafy.paymentsservice.payments.service;


import io.ssafy.paymentsservice.payments.dto.request.PaymentReqDto;
import io.ssafy.paymentsservice.payments.dto.response.PaymentFinalResDto;
import io.ssafy.paymentsservice.payments.entity.Payment;
import io.ssafy.paymentsservice.response.Response;

public interface PaymentService {

    Response<?> requestTossPayment (Payment payment, String memberId, PaymentReqDto paymentReqDto);
    Response<?> tossPaymentSuccess (String paymentKey, String orderId, Integer amount, String memberId);
    Response<?> tossPaymentFail (String code, String message, String orderId);
    Response<?> tossPaymentCancelCrystal (String memberId, String paymentKey, String cancelReason);
    Response<?> tossPaymentCancel (String paymentKey, String cancelReason);
    Payment verifyPayment (String orderId, int amount, String memberId);
    PaymentFinalResDto requestPaymentAccept (String paymentKey, String orderId, int amount);;
}
