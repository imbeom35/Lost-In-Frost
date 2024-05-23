package io.ssafy.paymentsservice.payments.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.paymentsservice.payments.dto.response.PurchaseHistoryListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.paymentsservice.payments.entity.QPurchaseHistory.purchaseHistory;


@Repository
@RequiredArgsConstructor
@Slf4j
public class PurchaseHistoryQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<PurchaseHistoryListResDto> getPurchaseHistoryList(String memberId, Boolean classification, Pageable pageable) {
        List<PurchaseHistoryListResDto> list = jpaQueryFactory
                .select(Projections.constructor(PurchaseHistoryListResDto.class,
                        purchaseHistory.seq,
                        purchaseHistory.state,
                        purchaseHistory.quantity,
                        purchaseHistory.classification,
                        purchaseHistory.costumeSeq,
                        purchaseHistory.costumeName,
                        purchaseHistory.price,
                        purchaseHistory.costumeImage,
                        purchaseHistory.costumeGrade,
                        purchaseHistory.purchaseDatetime))
                .from(purchaseHistory)
                .where(
                        purchaseHistory.member.id.eq(memberId).and(purchaseHistory.classification.eq(classification))
                )
                .orderBy(purchaseHistory.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(purchaseHistory.count())
                .from(purchaseHistory)
                .where(
                        purchaseHistory.member.id.eq(memberId).and(purchaseHistory.classification.eq(classification))
                )
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }
}
