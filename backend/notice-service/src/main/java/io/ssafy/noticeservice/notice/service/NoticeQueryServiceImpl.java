package io.ssafy.noticeservice.notice.service;

import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.notice.dto.response.NoticeDetailResDto;
import io.ssafy.noticeservice.notice.dto.response.NoticeListResDto;
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

import static io.ssafy.noticeservice.global.response.Response.ERROR;
import static io.ssafy.noticeservice.global.response.Response.OK;


@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeQueryServiceImpl implements NoticeQueryService{

    private final NoticeQueryRepository noticeQueryRepository;
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    @Override
    public Response<?> getNoticeList(Pageable pageable) {
        Page<NoticeListResDto> noticeList = noticeQueryRepository.getNoticeList(pageable);
        if (noticeList.isEmpty()) {
            return ERROR("공지사항이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        } else {
            return OK(noticeList);
        }
    }

    @Transactional
    @Override
    public Response<?> getNoticeDetail(Long noticeSeq) {
        Notice notice = noticeRepository.findBySeqAndIsDeleted(noticeSeq, false).orElse(null);
        if (notice == null) {
            return ERROR("공지사항이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
        NoticeDetailResDto noticeDetailResDto = noticeQueryRepository.getNoticeDetail(noticeSeq);
        notice.updateViewCount();
        noticeRepository.save(notice);
        if (noticeDetailResDto != null) {
            return OK(noticeDetailResDto);
        } else {
            return ERROR("공지사항이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

}
