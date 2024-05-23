package io.ssafy.noticeservice.notice.service;


import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.notice.dto.request.NoticeWriteReqDto;

public interface NoticeService {
    Response<?> writeNotice(NoticeWriteReqDto noticeWriteReqDto, String memberId);
    Response<?> updateNotice(Long noticeSeq, NoticeWriteReqDto noticeWriteReqDto, String memberId);
    Response<?> deleteNotice(Long noticeSeq, String memberId);
}
