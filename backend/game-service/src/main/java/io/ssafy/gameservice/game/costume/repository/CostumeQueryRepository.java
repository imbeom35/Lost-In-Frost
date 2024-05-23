package io.ssafy.gameservice.game.costume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.gameservice.game.costume.dto.CostumeListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.gameservice.entity.QMyCostume.myCostume;
import static io.ssafy.gameservice.game.costume.entity.QCostume.costume;


@Repository
@RequiredArgsConstructor
@Slf4j
public class CostumeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<CostumeListResDto> getCostumeAllList() {
        return jpaQueryFactory
                .select(Projections.constructor(CostumeListResDto.class,
                        costume.seq,
                        costume.name,
                        costume.image,
                        costume.grade,
                        costume.coinPrice,
                        costume.crystalPrice,
                        Expressions.constant(false)
                ))
                .from(costume)
                .where(costume.isDeleted.eq(false))
                .fetch();
    }

    public Page<CostumeListResDto> getCostumeList(Pageable pageable, String memberId) {

        List<CostumeListResDto> list;

        if (memberId != null) {

            list = jpaQueryFactory
                    .select(Projections.constructor(CostumeListResDto.class,
                            costume.seq,
                            costume.name,
                            costume.image,
                            costume.grade,
                            costume.coinPrice,
                            costume.crystalPrice,
                            JPAExpressions.selectOne()
                                    .from(myCostume)
                                    .where(myCostume.costumeSeq.eq(costume.seq).and(myCostume.member.id.eq(memberId)))
                                    .exists()
                    ))
                    .from(costume)
                    .where(costume.isDeleted.eq(false))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        } else {
            list = jpaQueryFactory
                    .select(Projections.constructor(CostumeListResDto.class,
                            costume.seq,
                            costume.name,
                            costume.image,
                            costume.grade,
                            costume.coinPrice,
                            costume.crystalPrice,
                            Expressions.constant(false)
                    ))
                    .from(costume)
                    .where(costume.isDeleted.eq(false))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        Long count = jpaQueryFactory
                .select(costume.count())
                .from(costume)
                .where(costume.isDeleted.eq(false))
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }
}
