package io.ssafy.noticeservice.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Schema(description = "공지사항 리스트 조회 응답 DTO")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResDto {

    @Schema(description = "공지사항 식별자")
    private Long seq;

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "공지사항 작성일")
    private Timestamp createDatetime;

    @Schema(description = "공지사항 조회수")
    private Integer viewCount;

    @Schema(description = "공지사항 댓글 수")
    private Long commentCount;
}
