package io.ssafy.gameservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class NoticeComment {

    @Id
    @Column(name = "notice_comment_seq")
    @Comment("공지사항 댓글 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 식별자")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_seq")
    @Comment("공지사항 식별자")
    private Notice notice;

    @NotNull
    @Column(name = "notice_comment_content")
    @Comment("공지사항 댓글 내용")
    private String content;

    @CreationTimestamp
    @Column(name = "notice_create_datetime")
    @Comment("공지사항 댓글 작성 일자")
    private Timestamp createDatetime;

    @UpdateTimestamp
    @Column(name = "notice_update_datetime")
    @Comment("공지사항 댓글 수정 일자")
    private Timestamp updateDatetime;

    public void update(String content) {
        this.content = content;
    }
}
