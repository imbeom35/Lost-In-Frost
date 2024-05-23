package io.ssafy.userservice.user.member.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.userservice.global.response.Response;
import io.ssafy.userservice.oauth2.dto.UserResponseDto;
import io.ssafy.userservice.user.member.dto.response.MemberInfoResDto;
import io.ssafy.userservice.user.member.service.MemberQueryService;
import io.ssafy.userservice.user.member.service.MemberService;
import io.ssafy.userservice.user.mypage.dto.request.MyPageUpdateReqDto;
import io.ssafy.userservice.user.mypage.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static io.ssafy.userservice.global.response.Response.ERROR;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user")
@Tag(name = "회원 도메인")
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;
    private final MyPageService myPageService;


    @Timed(value = "user.withdrawal", longTask = true)
    @Operation(summary = "회원탈퇴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상적으로 탈퇴되었습니다", content = @Content(schema = @Schema(implementation = UserResponseDto.TokenInfo.class))),
            @ApiResponse(responseCode = "400", description = "등록된 회원이 아닙니다.")
    })
    @DeleteMapping("/withdrawal")
    public Response<?> withdrawalMember (@AuthenticationPrincipal User user) {
        log.debug("## 회원 탈퇴 : {}", user.getUsername());
        return memberService.withdrawalMember(user.getUsername());
    }


    @Timed(value = "user.info", longTask = true)
    @Operation(summary = "회원 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MemberInfoResDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 회원입니다.")
    })
    @GetMapping("/info")
    public Response<?> getInfo (@AuthenticationPrincipal User user) {
        log.info("## 회원 정보 조회 : {}", user.getUsername());
        return memberQueryService.getMyInfo(user.getUsername());
    }

    @Timed(value = "user.coin", longTask = true)
    @Operation(summary = "유저 코인 보유량 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 코인 보유량 조회 성공"),
            @ApiResponse(responseCode = "400", description = "유저 코인 보유량 조회 실패"),
    })
    @GetMapping("/coin")
    public Response<?> getCoinAmount (@AuthenticationPrincipal User user) {
        log.info("## 유저의 코인을 조회합니다!! : {}", user.getUsername());
        return myPageService.getCoinAmount(user.getUsername());
    }

    @Timed(value = "user.crystal", longTask = true)
    @Operation(summary = "유저 크리스탈 보유량 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 크리스탈 보유량 조회 성공"),
            @ApiResponse(responseCode = "400", description = "유저 크리스탈 보유량 조회 실패"),
    })
    @GetMapping("/crystal")
    public Response<?> getCrystalAmount (@AuthenticationPrincipal User user) {
        log.info("## 유저의 크리스탈을 조회합니다!! : {}", user.getUsername());
        return myPageService.getCrystalAmount(user.getUsername());
    }

    @Timed(value = "user.amount", longTask = true)
    @Operation(summary = "유저의 코인과 크리스탈 보유량 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저의 코인과 크리스탈 보유량 조회 성공"),
            @ApiResponse(responseCode = "400", description = "유저의 코인과 크리스탈 보유량 조회 실패"),
    })
    @GetMapping("/amount")
    public Response<?> getCoinAndCrystalAmount (@AuthenticationPrincipal User user) {
        log.info("## 유저의 코인과 크리스탈을 조회합니다!! : {}", user.getUsername());
        return myPageService.getCoinAndCrystalAmount(user.getUsername());
    }

    @Timed(value = "user.info.update", longTask = true)
    @Operation(summary = "내 정보 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 정보 변경 성공"),
            @ApiResponse(responseCode = "400", description = "내 정보 변경 실패"),
    })
    @PutMapping("/info")
    public Response<?> updateMyInfo (@AuthenticationPrincipal User user, @RequestBody MyPageUpdateReqDto myPageUpdateReqDto) {
        log.info("## 내 정보 변경 : {}", myPageUpdateReqDto.toString());
        if (myPageUpdateReqDto.getNickname().length() < 2 || myPageUpdateReqDto.getNickname().length() > 10) {
            return ERROR("닉네임은 2자 이상 10자 이하로 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        if (myPageUpdateReqDto.getPassword().length() < 6 || myPageUpdateReqDto.getPassword().length() > 18) {
            return ERROR("비밀번호는 6자 이상 18자 이하로 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return myPageService.updateMyInfo(user.getUsername(), myPageUpdateReqDto.getNickname(), myPageUpdateReqDto.getPassword());
    }

    @Timed(value = "user.message.update", longTask = true)
    @Operation(summary = "상태메시지 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태메시지 변경 성공"),
            @ApiResponse(responseCode = "400", description = "상태메시지 변경 실패"),
    })
    @PutMapping("/message")
    public Response<?> updateMessage (@AuthenticationPrincipal User user, @RequestBody String message) {
        log.info("## 상태메시지 변경 : {}", message);
        if (message.length() > 12) {
            return ERROR("상태메시지는 12자 이하로 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return myPageService.updateMessage(user.getUsername(), message);
    }

    @Timed(value = "user.validate.password", longTask = true)
    @Operation(summary = "비밀번호 검증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호가 일치합니다."),
            @ApiResponse(responseCode = "400", description = "비밀번호가 일치하지 않습니다."),
    })
    @PostMapping("/validate-password")
    public Response<?> validatePassword (@AuthenticationPrincipal User user, @RequestBody String password) {
        log.info("## 비밀번호 검증 : {}", password);
        return myPageService.validatePassword(user.getUsername(), password);
    }

    @Timed(value = "user.search.nickname", longTask = true)
    @Operation(summary = "닉네임 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "닉네임 검색 성공"),
            @ApiResponse(responseCode = "400", description = "닉네임 검색 실패"),
            @ApiResponse(responseCode = "404", description = "닉네임 검색 결과가 없습니다."),
    })
    @GetMapping("/search/{nickname}")
    public Response<?> searchNickname (@PathVariable String nickname) {
        log.info("## 닉네임 검색 : {}", nickname);
        return memberQueryService.searchNickname(nickname);
    }

    @Timed(value = "user.info.record.nickname", longTask = true)
    @Operation(summary = "전적용 회원 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전적용 회원 정보 조회 성공"),
            @ApiResponse(responseCode = "400", description = "전적용 회원 정보 조회 실패"),
            @ApiResponse(responseCode = "404", description = "전적용 회원 정보 조회 결과가 없습니다."),
    })
    @GetMapping("/info/record/{nickname}")
    public Response<?> getMemberRecordInfo (@PathVariable String nickname) {
        log.info("## 전적용 회원 정보 조회 : {}", nickname);
        return memberQueryService.getMemberRecordInfo(nickname);
    }


}
