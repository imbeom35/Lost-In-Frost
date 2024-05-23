package io.ssafy.userservice.user.member.respository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.userservice.user.member.dto.response.MemberInfoResDto;
import io.ssafy.userservice.user.member.dto.response.MemberRecordInfoResDto;
import io.ssafy.userservice.user.member.dto.response.SearchNicknameResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ssafy.userservice.user.member.entity.QMember.member;


@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MemberInfoResDto getMyInfo (String memberId) {
        return jpaQueryFactory
                .select(Projections.constructor(MemberInfoResDto.class,
                        member.email,
                        member.nickname,
                        member.level,
                        member.experience,
                        member.crystal,
                        member.coin,
                        member.myCostume.seq,
                        member.myCostume.costumeSeq,
                        member.myCostume.costumeName,
                        member.myCostume.costumeImage,
                        member.myCostume.costumeGrade,
                        member.message,
                        member.authProvider,
                        member.gamePlayCount,
                        member.successCount
                ))
                .from(member)
                .where(member.id.eq(memberId).and(member.isDeleted.eq(false)))
                .fetchOne();
    }

    public List<SearchNicknameResDto> searchNickname (String nickname) {
        return jpaQueryFactory.select(Projections.constructor(SearchNicknameResDto.class,
                member.id,
                member.nickname,
                member.myCostume.costumeImage,
                member.level))
                .from(member)
                .where(member.nickname.contains(nickname))
                .limit(5)
                .fetch();
    }

    public MemberRecordInfoResDto getMemberRecordInfo (String nickname) {
        return jpaQueryFactory.select(Projections.constructor(MemberRecordInfoResDto.class,
                member.id,
                member.nickname,
                member.myCostume.costumeImage,
                member.level,
                member.gamePlayCount,
                member.successCount))
                .from(member)
                .where(member.nickname.eq(nickname))
                .fetchOne();
    }
}
