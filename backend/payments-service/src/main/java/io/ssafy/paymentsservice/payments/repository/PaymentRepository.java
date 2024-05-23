package io.ssafy.paymentsservice.payments.repository;


import io.ssafy.paymentsservice.payments.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//
    Payment findByOrderId (String orderId);
    Payment findByPaymentKeyAndCustomer_Id (String paymentKey, String memberId);
}
