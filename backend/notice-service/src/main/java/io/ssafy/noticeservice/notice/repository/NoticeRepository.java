package io.ssafy.noticeservice.notice.repository;


import io.ssafy.noticeservice.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<Notice> findBySeqAndIsDeleted(Long noticeSeq, boolean isDeleted);
}
