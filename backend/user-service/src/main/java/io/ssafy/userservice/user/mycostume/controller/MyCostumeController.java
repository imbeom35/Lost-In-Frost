package io.ssafy.userservice.user.mycostume.controller;


import io.micrometer.core.annotation.Timed;
import io.ssafy.userservice.global.response.Response;
import io.ssafy.userservice.user.mycostume.dto.MyCostumeResDto;
import io.ssafy.userservice.user.mycostume.service.MyCostumeQueryService;
import io.ssafy.userservice.user.mypage.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user/my-costume")
@Tag(name = "회원 도메인")
public class MyCostumeController {

    private final MyCostumeQueryService myCostumeQueryService;
    private final MyPageService myPageService;

    @Timed(value = "user.my-costume-list", longTask = true)
    @Operation(summary = "내 코스튬 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MyCostumeResDto.class))),
            @ApiResponse(responseCode = "404", description = "내 코스튬 정보를 찾을 수 없습니다.")
    })
    @GetMapping("/list")
    public Response<?> getMyCostumeList(@AuthenticationPrincipal User user, @PageableDefault Pageable pageable) {
        log.info("## 내 코스튬 리스트 조회 : {}", user.getUsername());
        return myCostumeQueryService.getMyCostumeList(user.getUsername(), pageable);
    }

    @Timed(value = "user.my-costume-all-list", longTask = true)
    @Operation(summary = "내 코스튬 리스트 모두 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MyCostumeResDto.class))),
            @ApiResponse(responseCode = "404", description = "내 코스튬 정보를 찾을 수 없습니다.")
    })
    @GetMapping("/all-list")
    public Response<?> getMyCostumeAllList (@AuthenticationPrincipal User user) {
        log.info("## 내 코스튬 리스트 모두 조회 : {}", user.getUsername());
        return myCostumeQueryService.getMyCostumeAllList(user.getUsername());
    }

    @Timed(value = "user.my-costume.update", longTask = true)
    @Operation(summary = "내 코스튬 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "내 코스튬 변경 성공"),
            @ApiResponse(responseCode = "400", description = "내 코스튬 변경 실패"),
            @ApiResponse(responseCode = "404", description = "내 코스튬 정보를 찾을 수 없습니다.")
    })
    @PutMapping("/{myCostumeSeq}")
    public Response<?> updateMyCostume(@AuthenticationPrincipal User user, @PathVariable Long myCostumeSeq) {
        log.info("## 내 코스튬 변경 : {}, {}", user.getUsername(), myCostumeSeq);
        return myPageService.updateMyCostume(user.getUsername(), myCostumeSeq);
    }

    @Timed(value = "user.my-costume.count", longTask = true)
    @Operation(summary = "내 코스튬 개수")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 코스튬 개수 조회 성공"),
            @ApiResponse(responseCode = "400", description = "내 코스튬 개수 조회 실패"),
    })
    @GetMapping("/count")
    public Response<?> getMyCostumeCount (@AuthenticationPrincipal User user) {
        log.info("## 내 코스튬 개수 조회 : {}", user.getUsername());
        return myPageService.getMyCostumeCount(user.getUsername());
    }


}
