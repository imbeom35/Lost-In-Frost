package io.ssafy.paymentsservice.payments.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.paymentsservice.payments.dto.response.PaymentHistoryResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.paymentsservice.payments.entity.QPayment.payment;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PaymentQueryRepository {


    private final JPAQueryFactory jpaQueryFactory;

    public Page<PaymentHistoryResDto> getChargingHistory (Pageable pageable, String memberId) {

        List<PaymentHistoryResDto> list = jpaQueryFactory
                .select(Projections.constructor(PaymentHistoryResDto.class,
                        payment.seq,
                        payment.payType,
                        payment.amount,
                        payment.orderName,
                        payment.orderId,
                        payment.paySuccessStatus,
                        payment.crystalShopCrystalAmount,
                        payment.approvedAt
                        ))
                .from(payment)
                .where(
                        payment.customer.id.eq(memberId).and(payment.paySuccessStatus.eq(true))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(payment.approvedAt.desc())
                .fetch();

        Long count = jpaQueryFactory
                .select(payment.count())
                .from(payment)
                .where(payment.customer.id.eq(memberId))
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }

//    public Page<>

}
