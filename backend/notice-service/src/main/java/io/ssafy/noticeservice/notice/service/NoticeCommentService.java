package io.ssafy.noticeservice.notice.service;


import io.ssafy.noticeservice.global.response.Response;

public interface NoticeCommentService {

    Response<?> writeNoticeComment (String memberId, Long noticeSeq, String content);
    Response<?> updateNoticeComment (Long noticeCommentSeq, String content, String memberId);
    Response<?> deleteNoticeComment (Long noticeCommentSeq, String memberId);
}
