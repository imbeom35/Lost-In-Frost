package io.ssafy.noticeservice.notice.service;

import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.notice.dto.response.NoticeCommentListResDto;
import io.ssafy.noticeservice.notice.entity.Notice;
import io.ssafy.noticeservice.notice.repository.NoticeQueryRepository;
import io.ssafy.noticeservice.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NoticeCommentQueryServiceImpl implements NoticeCommentQueryService{

    private final NoticeQueryRepository noticeQueryRepository;
    private final NoticeRepository noticeRepository;

    @Override
    public Response<?> getNoticeCommentList(Long noticeSeq, Pageable pageable, String memberId) {
        Notice notice = noticeRepository.findBySeqAndIsDeleted(noticeSeq, false).orElse(null);
        if (notice != null) {
            Page<NoticeCommentListResDto> noticeCommentList = noticeQueryRepository.getNoticeCommentList(pageable, noticeSeq, memberId);
            if (noticeCommentList.isEmpty()) {
                return Response.ERROR("댓글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
            } else {
                return Response.OK(noticeCommentList);
            }
        } else {
            return Response.ERROR("공지사항이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
