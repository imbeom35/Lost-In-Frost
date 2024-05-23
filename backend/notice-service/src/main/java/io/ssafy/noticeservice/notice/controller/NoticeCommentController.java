package io.ssafy.noticeservice.notice.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.notice.service.NoticeCommentQueryService;
import io.ssafy.noticeservice.notice.service.NoticeCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static io.ssafy.noticeservice.global.response.Response.ERROR;
import static io.ssafy.noticeservice.global.response.Response.OK;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/notice/comment")
@Tag(name = "공지사항 도메인", description = "공지사항 댓글 API")
public class NoticeCommentController {

    private final NoticeCommentService noticeCommentService;
    private final NoticeCommentQueryService noticeCommentQueryService;

    @Timed(value = "notice.comment.write", longTask = true)
    @Operation(summary = "공지사항 댓글 작성")
    @Parameter(name = "noticeSeq", description = "공지사항 식별자", example = "1")
    @PostMapping("/write/{noticeSeq}")
    public Response<?> writeNoticeComment(@AuthenticationPrincipal User user, @PathVariable Long noticeSeq, @RequestBody String content) {
        log.info("## 공지사항 댓글 작성 : {}", noticeSeq);
        if (content.length() > 200) {
            return ERROR("댓글은 200자 이내로 작성해주세요.", HttpStatus.BAD_REQUEST);
        }
        if (content.isEmpty()) {
            return ERROR("댓글 내용을 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return noticeCommentService.writeNoticeComment(user.getUsername(), noticeSeq, content);
    }


    @Timed(value = "notice.comment.list", longTask = true)
    @Operation(summary = "공지사항 댓글 리스트 조회")
    @Parameter(name = "noticeSeq", description = "공지사항 식별자", example = "1")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "공지사항 댓글 리스트 조회 성공"),
            @ApiResponse(responseCode = "404", description = "공지사항이 존재하지 않습니다.")
        }
    )
    @GetMapping("/list/{noticeSeq}")
    public Response<?> getNoticeCommentList(@PathVariable Long noticeSeq, Pageable pageable, @AuthenticationPrincipal User user) {
        log.info("## 공지사항 댓글 리스트 조회 : {}", noticeSeq);
        if (user == null) {
            return noticeCommentQueryService.getNoticeCommentList(noticeSeq, pageable, null);
        } else {
            return noticeCommentQueryService.getNoticeCommentList(noticeSeq, pageable, user.getUsername());
        }
    }

    @Timed(value = "notice.comment.update", longTask = true)
    @Operation(summary = "공지사항 댓글 수정")
    @Parameter(name = "noticeCommentSeq", description = "공지사항 댓글 식별자", example = "1")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "공지사항 댓글 수정 성공"),
            @ApiResponse(responseCode = "400", description = "공지사항 댓글 수정 실패")
        }
    )
    @PutMapping("/update/{noticeCommentSeq}")
    public Response<?> updateNoticeComment(@PathVariable Long noticeCommentSeq, @RequestBody String content, @AuthenticationPrincipal User user) {
        log.info("## 공지사항 댓글 수정 : {}", noticeCommentSeq);
        if (content.length() > 200) {
            return ERROR("댓글은 200자 이내로 작성해주세요.", HttpStatus.BAD_REQUEST);
        }
        if (content.isEmpty()) {
            return ERROR("댓글 내용을 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return noticeCommentService.updateNoticeComment(noticeCommentSeq, content, user.getUsername());
    }

    @Timed(value = "notice.comment.delete", longTask = true)
    @Operation(summary = "공지사항 댓글 삭제")
    @Parameter(name = "noticeCommentSeq", description = "공지사항 댓글 식별자", example = "1")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "공지사항 댓글 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "공지사항 댓글 삭제 실패")
        }
    )
    @DeleteMapping("/delete/{noticeCommentSeq}")
    public Response<?> deleteNoticeComment(@PathVariable Long noticeCommentSeq, @AuthenticationPrincipal User user) {
        log.info("## 공지사항 댓글 삭제 : {}", noticeCommentSeq);
        return noticeCommentService.deleteNoticeComment(noticeCommentSeq, user.getUsername());
    }
}
