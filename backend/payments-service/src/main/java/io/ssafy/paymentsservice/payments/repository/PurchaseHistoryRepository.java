package io.ssafy.paymentsservice.payments.repository;


import io.ssafy.paymentsservice.payments.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
