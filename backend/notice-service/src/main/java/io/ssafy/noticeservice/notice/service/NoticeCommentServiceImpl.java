package io.ssafy.noticeservice.notice.service;

import io.ssafy.noticeservice.entity.Member;
import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.notice.entity.Notice;
import io.ssafy.noticeservice.notice.entity.NoticeComment;
import io.ssafy.noticeservice.notice.repository.NoticeCommentRepository;
import io.ssafy.noticeservice.notice.repository.NoticeQueryRepository;
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
@Slf4j
@RequiredArgsConstructor
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentRepository noticeCommentRepository;
    private final NoticeQueryRepository noticeQueryRepository;
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Response<?> writeNoticeComment(String memberId, Long noticeSeq, String content) {
        Notice notice = noticeRepository.findBySeqAndIsDeleted(noticeSeq, false).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);

        if (notice == null || member == null) {
            return ERROR("댓글 작성에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        } else {
            noticeCommentRepository.save(NoticeComment.builder()
                    .member(member)
                    .notice(notice)
                    .content(content)
                    .build());
            return OK(null);
        }
    }

    @Override
    public Response<?> updateNoticeComment(Long noticeCommentSeq, String content, String memberId) {
        NoticeComment noticeComment = noticeCommentRepository.findById(noticeCommentSeq).orElse(null);
        if (noticeComment != null) {
            if (noticeComment.getMember().getId().equals(memberId)) {
                noticeComment.update(content);
                return OK(null);
            } else {
                return ERROR("댓글 수정에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return ERROR("댓글 수정에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Response<?> deleteNoticeComment(Long noticeCommentSeq, String memberId) {
        NoticeComment noticeComment = noticeCommentRepository.findById(noticeCommentSeq).orElse(null);
        if (noticeComment != null) {
            if (noticeComment.getMember().getId().equals(memberId)) {
                noticeCommentRepository.delete(noticeComment);
                return OK(null);
            } else {
                return ERROR("댓글 삭제에 실패하였습니다.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return ERROR("댓글 삭제에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
