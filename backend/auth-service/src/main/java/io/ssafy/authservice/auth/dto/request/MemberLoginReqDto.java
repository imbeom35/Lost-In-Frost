package io.ssafy.authservice.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "로그인 요청 DTO")
public class MemberLoginReqDto {

    @Schema(description = "회원 이메일", example = "admin@ssafy.com")
    private String email;
    @Schema(description = "회원 비밀번호", example = "c101")
    private String password;
}
