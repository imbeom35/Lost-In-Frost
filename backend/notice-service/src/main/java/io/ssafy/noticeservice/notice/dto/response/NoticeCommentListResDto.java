package io.ssafy.noticeservice.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Schema(description = "공지사항 댓글 리스트 응답 DTO")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCommentListResDto {

    @Schema(description = "회원 식별자")
    private String memberId;

    @Schema(description = "회원 닉네임")
    private String memberNickname;

    @Schema(description = "회원 사진")
    private String memberImage;

    @Schema(description = "댓글 식별자")
    private Long commentSeq;

    @Schema(description = "댓글 내용")
    private String content;

    @Schema(description = "댓글 작성일")
    private Timestamp createDatetime;
    
    @Schema(description = "내 댓글 여부")
    private boolean isMine;
}
