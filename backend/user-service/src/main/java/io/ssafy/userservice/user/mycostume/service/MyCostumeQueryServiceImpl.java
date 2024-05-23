package io.ssafy.userservice.user.mycostume.service;

import io.ssafy.userservice.global.response.Response;
import io.ssafy.userservice.user.member.entity.Member;
import io.ssafy.userservice.user.member.respository.MemberRepository;
import io.ssafy.userservice.user.mycostume.dto.MyCostumeResDto;
import io.ssafy.userservice.user.mycostume.repository.MyCostumeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.ssafy.userservice.global.response.Response.ERROR;
import static io.ssafy.userservice.global.response.Response.OK;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MyCostumeQueryServiceImpl implements MyCostumeQueryService{

    private final MyCostumeQueryRepository myCostumeQueryRepository;
    private final MemberRepository memberRepository;

    @Override
    public Response<?> getMyCostumeList(String memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            Page<MyCostumeResDto> list = myCostumeQueryRepository.getMyCostumeList(memberId, pageable);
            if (list.isEmpty()){
                return ERROR("보유한 코스튬이 없습니다.", HttpStatus.NOT_FOUND);
            } else {
                return OK(list);
            }
        } else {
            return ERROR("회원 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Response<?> getMyCostumeAllList(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            List<MyCostumeResDto> list = myCostumeQueryRepository.getMyCostumeAllList(memberId);
            if (list.isEmpty()){
                return ERROR("보유한 코스튬이 없습니다.", HttpStatus.NOT_FOUND);
            } else {
                return OK(list);
            }
        } else {
            return ERROR("회원 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
