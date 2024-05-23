package io.ssafy.paymentsservice.payments.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.paymentsservice.payments.dto.response.CrystalShopListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.paymentsservice.payments.entity.QCrystalShop.crystalShop;


@Repository
@RequiredArgsConstructor
@Slf4j
public class CrystalShopQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<CrystalShopListResDto> getCrystalShopList(Pageable pageable) {

        List<CrystalShopListResDto> list = jpaQueryFactory
                .select(Projections.constructor(CrystalShopListResDto.class,
                        crystalShop.seq,
                        crystalShop.crystalName,
                        crystalShop.crystalAmount,
                        crystalShop.crystalPrice,
                        crystalShop.image
                ))
                .from(crystalShop)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(crystalShop.count())
                .from(crystalShop)
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }
}
