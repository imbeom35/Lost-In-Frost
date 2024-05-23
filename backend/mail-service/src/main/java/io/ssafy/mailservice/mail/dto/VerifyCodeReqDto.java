package io.ssafy.mailservice.mail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "메일 코드 인증 요청 DTO")
public class VerifyCodeReqDto {

    @Schema(description = "메일 주소", example = "test@test.com")
    private String email;

    @Schema(description = "인증 코드", example = "111111")
    private int code;

}
