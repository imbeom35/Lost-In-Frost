package io.ssafy.gameservice.game.record.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.gameservice.game.record.dto.GameRecordResDto;
import io.ssafy.gameservice.game.record.entity.QGameRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.gameservice.entity.QMember.member;
import static io.ssafy.gameservice.game.record.entity.QGameRecord.gameRecord;

@Repository
@RequiredArgsConstructor
@Slf4j
public class GameRecordQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<GameRecordResDto> getRecordList(String nickname, Pageable pageable) {
        List<GameRecordResDto> list = jpaQueryFactory
                .select(Projections.constructor(GameRecordResDto.class,
                        gameRecord.seq,
                        gameRecord.roomId,
                        gameRecord.member.id,
                        gameRecord.member.nickname,
                        gameRecord.member.myCostume.costumeImage,
                        gameRecord.finishTime,
                        gameRecord.score,
                        gameRecord.startAt,
                        gameRecord.isClear,
                        gameRecord.difficulty
                ))
                .from(gameRecord)
                .where(gameRecord.member.nickname.eq(nickname))
                .orderBy(gameRecord.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = jpaQueryFactory
                .select(gameRecord.count())
                .from(gameRecord)
                .where(gameRecord.member.nickname.eq(nickname))
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }


    public List<GameRecordResDto> getRecordDetailList (String roomId) {
        return jpaQueryFactory
                .select(Projections.constructor(GameRecordResDto.class,
                        gameRecord.seq,
                        gameRecord.roomId,
                        gameRecord.member.id,
                        gameRecord.member.nickname,
                        gameRecord.member.myCostume.costumeImage,
                        gameRecord.finishTime,
                        gameRecord.score,
                        gameRecord.startAt,
                        gameRecord.isClear,
                        gameRecord.difficulty
                ))
                .from(gameRecord)
                .where(gameRecord.roomId.eq(roomId))
                .orderBy(gameRecord.seq.desc())
                .fetch();
    }









}
