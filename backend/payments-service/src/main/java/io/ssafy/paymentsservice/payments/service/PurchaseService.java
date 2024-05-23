package io.ssafy.paymentsservice.payments.service;


import io.ssafy.paymentsservice.response.Response;

public interface PurchaseService {

    Response<?> purchaseItem (Long costumeSeq, String memberId, String classification);
}
