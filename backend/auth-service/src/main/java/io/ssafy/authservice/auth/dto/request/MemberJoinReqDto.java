package io.ssafy.authservice.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "회원가입 요청 DTO")
public class MemberJoinReqDto {

    @Schema(description = "회원 이메일", example = "test@test.com")
    private String email;
    @Schema(description = "회원 비밀번호", example = "1234")
    private String password;
    @Schema(description = "회원 닉네임", example = "테스트")
    private String nickname;

}
