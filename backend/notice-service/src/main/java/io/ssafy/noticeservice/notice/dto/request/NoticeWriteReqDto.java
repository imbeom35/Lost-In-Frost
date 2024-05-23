package io.ssafy.noticeservice.notice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "공지사항 작성 요청 DTO")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeWriteReqDto {

        @Schema(description = "공지사항 제목")
        private String title;

        @Schema(description = "공지사항 내용")
        private String content;
}
