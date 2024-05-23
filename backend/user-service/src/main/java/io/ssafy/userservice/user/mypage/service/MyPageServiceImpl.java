package io.ssafy.userservice.user.mypage.service;

import io.ssafy.userservice.domain.entity.MailRedis;
import io.ssafy.userservice.domain.repository.MailRedisRepository;
import io.ssafy.userservice.global.response.Response;
import io.ssafy.userservice.user.member.entity.Member;
import io.ssafy.userservice.user.member.respository.MemberRepository;
import io.ssafy.userservice.user.mycostume.entity.MyCostume;
import io.ssafy.userservice.user.mycostume.repository.MyCostumeRepository;
import io.ssafy.userservice.user.mypage.dto.response.MemberGoodsResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static io.ssafy.userservice.global.response.Response.ERROR;
import static io.ssafy.userservice.global.response.Response.OK;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MyPageServiceImpl implements MyPageService{

    private final MemberRepository memberRepository;
    private final MyCostumeRepository myCostumeRepository;
    private final MailRedisRepository mailRedisRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public Response<?> getCoinAmount(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        return OK(member.getCoin());
    }

    @Transactional(readOnly = true)
    @Override
    public Response<?> getCrystalAmount(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        return OK(member.getCrystal());
    }

    @Transactional(readOnly = true)
    @Override
    public Response<?> getCoinAndCrystalAmount(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        return OK(new MemberGoodsResDto(member.getCrystal(), member.getCoin()));
    }

    @Override
    public Response<?> updateMyCostume(String memberId, Long myCostumeSeq) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

        MyCostume myCostume = myCostumeRepository.findById(myCostumeSeq).orElse(null);
        if (myCostume == null) {
            return ERROR("존재하지 않는 코스튬입니다.", HttpStatus.BAD_REQUEST);
        }

        if (Objects.equals(member.getMyCostume().getSeq(), myCostumeSeq)) {
            return ERROR("현재 착용중인 코스튬입니다.", HttpStatus.BAD_REQUEST);
        } else {
            member = member.setMyCostume(myCostume, member);
            memberRepository.save(member);
            return OK(null);
        }
    }

    @Override
    public Response<?> updateMyInfo(String memberId, String nickname, String password) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

        if (nickname != null) {
            Member memberNickname = memberRepository.findByNicknameAndIsDeleted(nickname, false).orElse(null);
            MailRedis mailRedis = mailRedisRepository.findByNickname(nickname).orElse(null);
            if (memberNickname != null || mailRedis != null) {
                return ERROR("이미 존재하는 닉네임입니다.", HttpStatus.BAD_REQUEST);
            }
            member.updateNickname(nickname);
            memberRepository.save(member);
        }

        if (password != null) {
            String encryptPassword = passwordEncoder.encode(password);
            member.updatePassword(encryptPassword);
            memberRepository.save(member);
        }

        if (nickname == null && password == null) {
            return ERROR("변경할 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }

        return OK("회원정보가 정상적으로 변경되었습니다.");
    }

    @Override
    public Response<?> validatePassword(String memberId, String password) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        if (passwordEncoder.matches(password, member.getPassword())) {
            return OK("비밀번호가 일치합니다.");
        } else {
            return ERROR("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Response<?> getMyCostumeCount(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        Long count = myCostumeRepository.countByMemberId(memberId);
        return OK(count);
    }

    @Override
    public Response<?> updateMessage(String memberId, String message) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        member.updateMessage(message);
        memberRepository.save(member);
        return OK(null);
    }
}
