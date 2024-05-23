package io.ssafy.noticeservice.notice.controller;


import io.micrometer.core.annotation.Timed;
import io.ssafy.noticeservice.global.response.Response;
import io.ssafy.noticeservice.global.security.enums.Role;
import io.ssafy.noticeservice.notice.dto.request.NoticeWriteReqDto;
import io.ssafy.noticeservice.notice.service.NoticeQueryService;
import io.ssafy.noticeservice.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static io.ssafy.noticeservice.global.response.Response.ERROR;
import static io.ssafy.noticeservice.global.response.Response.OK;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/notice")
@Tag(name = "공지사항 도메인", description = "공지사항 API")
public class NoticeController {


    private final NoticeQueryService noticeQueryService;
    private final NoticeService noticeService;


    @Timed(value = "notice.list", longTask = true)
    @Operation(summary = "공지사항 리스트 조회")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Response.class)), description = "공지사항 리스트 조회 성공"),
            @ApiResponse(responseCode = "404", description = "공지사항이 존재하지 않습니다.")
        }
    )
    @GetMapping("/list")
    public Response<?> getNoticeList (@PageableDefault Pageable pageable) {
        log.info("공지사항 리스트 조회");
        return noticeQueryService.getNoticeList(pageable);
    }

    @Timed(value = "notice.detail", longTask = true)
    @Operation(summary = "공지사항 상세 조회")
    @Parameter(name = "noticeSeq", description = "공지사항 번호", example = "1")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Response.class)), description = "공지사항 상세 조회 성공"),
            @ApiResponse(responseCode = "404", description = "공지사항이 존재하지 않습니다.")
        }
    )
    @GetMapping("/detail/{noticeSeq}")
    public Response<?> getNoticeDetail (@PathVariable Long noticeSeq) {
        log.info("공지사항 상세 조회 : {}", noticeSeq);
        return OK(noticeQueryService.getNoticeDetail(noticeSeq));
    }

    @Timed(value = "notice.write", longTask = true)
    @Operation(summary = "공지사항 작성")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Response.class)), description = "공지사항 작성 성공"),
            @ApiResponse(responseCode = "403", description = "관리자만 작성 가능합니다.")
        }
    )
    @PostMapping("/write")
    public Response<?> writeNotice(@AuthenticationPrincipal User user, @RequestBody NoticeWriteReqDto noticeWriteReqDto) {
        log.info("공지사항 작성");
        if (user.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(r -> r.equals(Role.ROLE_ADMIN.name()))){
            if (noticeWriteReqDto.getTitle().length() > 35) {
                return ERROR("제목은 35자 이내로 작성해주세요.", HttpStatus.BAD_REQUEST);
            }
            if (noticeWriteReqDto.getContent().length() > 9000) {
                return ERROR("내용은 9000자 이내로 작성해주세요.", HttpStatus.BAD_REQUEST);
            }
            if (noticeWriteReqDto.getTitle().isEmpty() || noticeWriteReqDto.getContent().isEmpty()) {
                return ERROR("빈칸으로 입력이 불가능합니다.", HttpStatus.BAD_REQUEST);
            }
            return OK(noticeService.writeNotice(noticeWriteReqDto, user.getUsername()));
        } else {
            return ERROR("관리자만 작성 가능합니다.", HttpStatus.FORBIDDEN);
        }

    }

    @Timed(value = "notice.update", longTask = true)
    @Operation(summary = "공지사항 수정")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Response.class)), description = "공지사항 수정 성공"),
            @ApiResponse(responseCode = "400", description = "공지사항 수정 실패"),
            @ApiResponse(responseCode = "403", description = "관리자만 수정 가능합니다.")
        }
    )
    @PutMapping("/update/{noticeSeq}")
    public Response<?> updateNotice(@PathVariable Long noticeSeq, @RequestBody NoticeWriteReqDto noticeWriteReqDto, @AuthenticationPrincipal User user) {
        log.info("공지사항 수정 : {}", noticeSeq);
        if (user.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(r -> r.equals(Role.ROLE_ADMIN.name()))){
            if (noticeWriteReqDto.getTitle().length() > 35) {
                return ERROR("제목은 35자 이내로 작성해주세요.", HttpStatus.BAD_REQUEST);
            }
            if (noticeWriteReqDto.getContent().length() > 9000) {
                return ERROR("내용은 9000자 이내로 작성해주세요.", HttpStatus.BAD_REQUEST);
            }
            if (noticeWriteReqDto.getTitle().isEmpty() || noticeWriteReqDto.getContent().isEmpty()) {
                return ERROR("빈칸으로 입력이 불가능합니다.", HttpStatus.BAD_REQUEST);
            }
            return OK(noticeService.updateNotice(noticeSeq, noticeWriteReqDto, user.getUsername()));
        } else {
            return ERROR("관리자만 수정 가능합니다.", HttpStatus.FORBIDDEN);
        }
    }

    @Operation(summary = "공지사항 삭제")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Response.class)), description = "공지사항 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "공지사항 삭제 실패"),
            @ApiResponse(responseCode = "403", description = "관리자만 삭제 가능합니다.")
        }
    )
    @DeleteMapping("/delete/{noticeSeq}")
    public Response<?> deleteNotice(@PathVariable Long noticeSeq, @AuthenticationPrincipal User user) {
        log.info("공지사항 삭제 : {}", noticeSeq);
        if (user.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(r -> r.equals(Role.ROLE_ADMIN.name()))){
            return OK(noticeService.deleteNotice(noticeSeq, user.getUsername()));
        } else {
            return ERROR("관리자만 삭제 가능합니다.", HttpStatus.FORBIDDEN);
        }
    }
}
