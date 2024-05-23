package io.ssafy.paymentsservice.payments.service;

import io.ssafy.paymentsservice.response.Response;
import org.springframework.data.domain.Pageable;

public interface PaymentQueryService {

    Response<?> getPaymentHistory (Pageable pageable, String memberId);
    Response<?> getCrystalPurchaseHistory (Pageable pageable, String memberId);
}
