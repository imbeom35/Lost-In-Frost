package io.ssafy.userservice.user.mycostume.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.userservice.user.mycostume.dto.MyCostumeResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.userservice.user.mycostume.entity.QMyCostume.myCostume;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyCostumeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<MyCostumeResDto> getMyCostumeList(String memberId, Pageable pageable) {

        log.debug("memberId : {}", memberId);

        List<MyCostumeResDto> list = jpaQueryFactory
                .select(Projections.constructor(MyCostumeResDto.class,
                        myCostume.seq,
                        myCostume.costumeSeq,
                        myCostume.costumeName,
                        myCostume.costumeImage,
                        myCostume.costumeGrade,
                        myCostume.purchaseHistorySeq.seq))
                .from(myCostume)
                .where(myCostume.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(myCostume.count())
                .from(myCostume)
                .where(myCostume.member.id.eq(memberId))
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }

    public List<MyCostumeResDto> getMyCostumeAllList(String memberId) {
        return jpaQueryFactory
                .select(Projections.constructor(MyCostumeResDto.class,
                        myCostume.seq,
                        myCostume.costumeSeq,
                        myCostume.costumeName,
                        myCostume.costumeImage,
                        myCostume.costumeGrade,
                        myCostume.purchaseHistorySeq.seq))
                .from(myCostume)
                .where(myCostume.member.id.eq(memberId))
                .orderBy(myCostume.seq.desc())
                .fetch();
    }
}
