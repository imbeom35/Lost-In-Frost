package io.ssafy.noticeservice.notice.repository;


import io.ssafy.noticeservice.notice.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {

    NoticeComment findByMemberIdAndNoticeSeq(String memberId, Long noticeSeq);
}
