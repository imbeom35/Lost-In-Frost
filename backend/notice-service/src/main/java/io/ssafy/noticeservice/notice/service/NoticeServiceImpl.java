package io.ssafy.noticeservice.notice.service;

import io.ssafy.noticeservice.entity.Member;
import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.notice.dto.request.NoticeWriteReqDto;
import io.ssafy.noticeservice.notice.entity.Notice;
import io.ssafy.noticeservice.notice.repository.NoticeRepository;
import io.ssafy.noticeservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.noticeservice.global.response.Response.ERROR;
import static io.ssafy.noticeservice.global.response.Response.OK;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Response<?> writeNotice(NoticeWriteReqDto noticeWriteReqDto, String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        } else {
            noticeRepository.save(Notice.builder()
                    .title(noticeWriteReqDto.getTitle())
                    .content(noticeWriteReqDto.getContent())
                    .member(member)
                    .build());
            return OK(null);
        }
    }

    @Override
    public Response<?> updateNotice(Long noticeSeq, NoticeWriteReqDto noticeWriteReqDto, String memberId) {
        Notice notice = noticeRepository.findBySeqAndIsDeleted(noticeSeq, false).orElse(null);
        if (notice == null) {
            return ERROR("존재하지 않는 공지사항입니다.", HttpStatus.BAD_REQUEST);
        } else {
            notice.updateNotice(noticeWriteReqDto.getTitle(), noticeWriteReqDto.getContent());
            return OK(null);
        }
    }

    @Override
    public Response<?> deleteNotice(Long noticeSeq, String memberId) {
        Notice notice = noticeRepository.findBySeqAndIsDeleted(noticeSeq, false).orElse(null);
        if (notice == null) {
            return ERROR("존재하지 않는 공지사항입니다.", HttpStatus.BAD_REQUEST);
        } else {
            if (!notice.getMember().getId().equals(memberId)) {
                return ERROR("권한이 없습니다.", HttpStatus.FORBIDDEN);
            }
            notice.deleteNotice();
            noticeRepository.save(notice);
            return OK(null);
        }
    }
}
