package io.ssafy.noticeservice.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Schema(description = "공지사항 상세 조회 응답 DTO")
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDetailResDto {

    @Schema(description = "공지사항 식별자")
    private Long seq;

    @Schema(description = "회원 식별자")
    private String memberId;

    @Schema(description = "회원 닉네임")
    private String memberNickname;

    @Schema(description = "회원 사진")
    private String memberImage;

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "공지사항 내용")
    private String content;

    @Schema(description = "공지사항 작성일")
    private Timestamp createDatetime;

    @Schema(description = "공지사항 조회수")
    private Integer viewCount;

    @Schema(description = "공지사항 댓글 수")
    private Long commentCount;


}
