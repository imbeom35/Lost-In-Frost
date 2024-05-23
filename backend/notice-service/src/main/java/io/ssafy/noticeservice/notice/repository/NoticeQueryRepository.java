package io.ssafy.noticeservice.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.noticeservice.notice.dto.response.NoticeCommentListResDto;
import io.ssafy.noticeservice.notice.dto.response.NoticeDetailResDto;
import io.ssafy.noticeservice.notice.dto.response.NoticeListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.noticeservice.notice.entity.QNotice.notice;
import static io.ssafy.noticeservice.notice.entity.QNoticeComment.noticeComment;


@RequiredArgsConstructor
@Repository
@Slf4j
public class NoticeQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<NoticeListResDto> getNoticeList(Pageable pageable) {

        List<NoticeListResDto> list = jpaQueryFactory
                .select(Projections.constructor(NoticeListResDto.class,
                        notice.seq,
                        notice.title,
                        notice.createDatetime,
                        notice.viewCount,
                        JPAExpressions.select(noticeComment.count())
                                .from(noticeComment)
                                .where(noticeComment.notice.eq(notice))
                        ))
                .from(notice)
                .where(
                        notice.isDeleted.eq(false)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.seq.desc())
                .fetch();

        Long count = jpaQueryFactory
                .select(notice.count())
                .from(notice)
                .where(notice.isDeleted.eq(false))
                .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }

    public NoticeDetailResDto getNoticeDetail(Long noticeSeq) {

        return jpaQueryFactory
                .select(Projections.constructor(NoticeDetailResDto.class,
                        notice.seq,
                        notice.member.id,
                        notice.member.nickname,
                        notice.member.myCostume.costumeImage,
                        notice.title,
                        notice.content,
                        notice.createDatetime,
                        notice.viewCount,
                        JPAExpressions
                                .select(noticeComment.count())
                                .from(noticeComment)
                                .where(noticeComment.notice.eq(notice))
                ))
                .from(notice)
                .where(
                        notice.seq.eq(noticeSeq).and(
                                notice.isDeleted.eq(false))
                )
                .fetchOne();
    }

    public Page<NoticeCommentListResDto> getNoticeCommentList (Pageable pageable, Long noticeSeq, String memberId) {
        List<NoticeCommentListResDto> list = null;
        if (memberId != null) {
            list = jpaQueryFactory
                    .select(Projections.constructor(NoticeCommentListResDto.class,
                            noticeComment.member.id,
                            noticeComment.member.nickname,
                            noticeComment.member.myCostume.costumeImage,
                            noticeComment.seq,
                            noticeComment.content,
                            noticeComment.createDatetime,
                            JPAExpressions.selectOne()
                                    .from(noticeComment)
                                    .where(noticeComment.member.id.eq(memberId)
                                            .and(noticeComment.notice.seq.eq(noticeSeq))
                                            .and(noticeComment.notice.isDeleted.eq(false)))
                                    .exists()

                    ))
                    .from(noticeComment)
                    .where(
                            noticeComment.notice.seq.eq(noticeSeq).and(
                                    noticeComment.notice.isDeleted.eq(false))
                    )
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(noticeComment.seq.desc())
                    .fetch();
        } else {
            list = jpaQueryFactory
                    .select(Projections.constructor(NoticeCommentListResDto.class,
                            noticeComment.member.id,
                            noticeComment.member.nickname,
                            noticeComment.member.myCostume.costumeImage,
                            noticeComment.seq,
                            noticeComment.content,
                            noticeComment.createDatetime,
                            Expressions.constant(false)
                    ))
                    .from(noticeComment)
                    .where(
                            noticeComment.notice.seq.eq(noticeSeq).and(
                                    noticeComment.notice.isDeleted.eq(false))
                    )
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(noticeComment.seq.desc())
                    .fetch();
        }



            Long count = jpaQueryFactory
                    .select(noticeComment.count())
                    .from(noticeComment)
                    .where(noticeComment.notice.seq.eq(noticeSeq).and(
                            noticeComment.notice.isDeleted.eq(false))
                    )
                    .fetchFirst();

        return new PageImpl<>(list, pageable, count);
    }
}
