package io.ssafy.userservice.user.member.service;

import io.ssafy.userservice.global.response.Response;
import io.ssafy.userservice.user.member.entity.Member;
import io.ssafy.userservice.user.member.respository.MemberQueryRepository;
import io.ssafy.userservice.user.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.userservice.global.response.Response.ERROR;
import static io.ssafy.userservice.global.response.Response.OK;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;

    @Override
    public Response<?> getMyInfo(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);

        if (member != null) {
            return OK(memberQueryRepository.getMyInfo(memberId));
        } else {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Response<?> searchNickname(String memberNickname) {
        return OK(memberQueryRepository.searchNickname(memberNickname));
    }

    @Override
    public Response<?> getMemberRecordInfo(String nickname) {
        Member member = memberRepository.findByNicknameAndIsDeleted(nickname, false).orElse(null);
        if (member != null) {
            return OK(memberQueryRepository.getMemberRecordInfo(nickname));
        } else {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
        }
    }
}
