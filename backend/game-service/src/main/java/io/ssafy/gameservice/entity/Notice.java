package io.ssafy.gameservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@DynamicInsert
public class Notice{

    @Id
    @Comment("공지사항 식별자")
    @Column(name = "notice_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 식별자")
    private Member member;

    @NotNull
    @Column(name = "notice_title", length = 100)
    @Comment("공지사항 제목")
    private String title;

    @NotNull
    @Column(name = "notice_content", length = 10000)
    @Comment("공지사항 내용")
    private String content;

    @NotNull
    @Column(name = "notice_view_count")
    @Comment("공지사항 조회수")
    private int viewCount;

    @Column(name = "notice_is_deleted")
    @ColumnDefault("false")
    @Comment("공지사항 삭제여부")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "notice_create_datetime")
    @Comment("공지사항 작성 일자")
    private Timestamp createDatetime;

    @UpdateTimestamp
    @Column(name = "notice_update_datetime")
    @Comment("공지사항 수정 일자")
    private Timestamp updateDatetime;

    public void updateNotice(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
