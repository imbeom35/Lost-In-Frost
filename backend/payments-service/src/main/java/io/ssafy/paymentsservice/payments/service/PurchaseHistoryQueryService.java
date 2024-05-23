package io.ssafy.paymentsservice.payments.service;


import io.ssafy.paymentsservice.response.Response;
import org.springframework.data.domain.Pageable;

public interface PurchaseHistoryQueryService {

    Response<?> getPurchaseHistoryList (String memberId, String classification, Pageable pageable);
}
