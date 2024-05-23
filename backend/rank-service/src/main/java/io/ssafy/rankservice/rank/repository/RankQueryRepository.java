package io.ssafy.rankservice.rank.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.rankservice.rank.dto.RankingListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.rankservice.rank.entity.QRanking.ranking;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RankQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<RankingListResDto> getRanking(Pageable pageable) {

        List<RankingListResDto> list = jpaQueryFactory
                .select(Projections.constructor(RankingListResDto.class,
                        ranking.seq,
                        ranking.memberId,
                        ranking.memberNickname,
                        ranking.memberCostume,
                        ranking.memberLevel,
                        ranking.memberExperience,
                        ranking.memberMessage,
                        ranking.memberGamePlayCount
                        ))
                .from(ranking)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(ranking.count())
                .from(ranking)
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }
}
