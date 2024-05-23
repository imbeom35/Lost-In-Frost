package io.ssafy.noticeservice.notice.service;

import io.ssafy.noticeservice.global.response.Response;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryService {

    Response<?> getNoticeList(Pageable pageable);
    Response<?> getNoticeDetail(Long noticeSeq);

}
