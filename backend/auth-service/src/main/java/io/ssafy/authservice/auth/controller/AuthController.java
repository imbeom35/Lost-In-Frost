package io.ssafy.authservice.auth.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.authservice.auth.dto.request.MemberJoinReqDto;
import io.ssafy.authservice.auth.dto.request.MemberLoginReqDto;
import io.ssafy.authservice.global.response.Response;
import io.ssafy.authservice.member.service.MemberService;
import io.ssafy.authservice.oauth2.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static io.ssafy.authservice.global.response.Response.ERROR;
import static io.ssafy.authservice.global.response.Response.OK;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/auth")
@Tag(name = "회원 도메인", description = "인증 API")
public class AuthController {

    private final MemberService memberService;

    @Timed(value = "auth.join", longTask = true)
    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상적으로 회원가입 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입된 회원이 있습니다.")
    })
    @PostMapping("/join")
    public Response<?> joinMember (@RequestBody MemberJoinReqDto memberJoinReqDto) {
        if (memberJoinReqDto.getNickname().length() < 2 || memberJoinReqDto.getNickname().length() > 10) {
            return ERROR("닉네임은 2자 이상 10자 이하로 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        log.debug("## 회원가입을 시도합니다! : {}", memberJoinReqDto);

        return memberService.joinMember(memberJoinReqDto);

    }


    @Timed(value = "auth.login", longTask = true)
    @Operation(summary = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인성공, 토큰을 response에 반환", content = @Content(schema = @Schema(implementation = UserResponseDto.TokenInfo.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다.")
    })
    @PostMapping("/login")
    public Response<?> loginMember (@RequestBody MemberLoginReqDto memberLoginReqDto, HttpServletResponse response) {
        log.debug("## 로그인을 시도합니다! : {}", memberLoginReqDto);
        return memberService.loginMember(memberLoginReqDto, response);
    }


    @Timed(value = "auth.oauth2.verify", longTask = true)
    @Operation(summary = "oauth2 코드 검증")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상적으로 인증되었습니다"),
            @ApiResponse(responseCode = "400", description = "등록된 회원이 아닙니다.")
    })
    @GetMapping("/oauth2/verify")
    public Response<?> verifyOauth2Code () {
        log.debug("## oauth2 코드 검증");
        return OK(null);
    }

    @Timed(value = "auth.validate.nickname", longTask = true)
    @Operation(summary = "닉네임 중복 검사")
    @Parameter(name = "nickname", description = "회원 닉네임", example = "테스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "이미 사용중인 닉네임입니다.")
    })
    @GetMapping("/validate/nickname/{nickname}")
    public Response<?> validateNickname (@PathVariable String nickname) {
        log.debug("## 닉네임 중복 검사 : {}", nickname);
        if (nickname.length() < 2 || nickname.length() > 10) {
            return ERROR("닉네임은 2자 이상 10자 이하로 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return memberService.validateNickname(nickname);
    }

    @Timed(value = "auth.validate.email", longTask = true)
    @Operation(summary = "이메일 중복 검사")
    @Parameter(name = "email", description = "회원 이메일", example = "test@test.com")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "이미 사용중인 이메일입니다.")
    })
    @GetMapping("/validate/email/{email}")
    public Response<?> validateEmail (@PathVariable String email) {
        log.debug("## 이메일 중복 검사 : {}", email);
        return memberService.validateEmail(email);
    }

    @GetMapping("/test")
    public Response<?> testForToken(@AuthenticationPrincipal User user) {
        log.debug("## 토큰 테스트 : {}", user);
        return OK(null);
    }

}
