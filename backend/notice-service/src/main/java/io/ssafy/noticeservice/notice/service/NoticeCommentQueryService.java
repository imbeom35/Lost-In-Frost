package io.ssafy.noticeservice.notice.service;

import io.ssafy.noticeservice.global.response.Response;
import org.springframework.data.domain.Pageable;

public interface NoticeCommentQueryService {
    Response<?> getNoticeCommentList(Long noticeSeq, Pageable pageable, String memberId);
}
