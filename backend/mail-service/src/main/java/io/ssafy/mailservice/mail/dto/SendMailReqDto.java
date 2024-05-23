package io.ssafy.mailservice.mail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "메일 전송 요청 DTO")
public class SendMailReqDto {

    @Schema(description = "메일 주소", example = "test@gmail.com")
    private String email;

    @Schema(description = "회원 닉네임", example = "test")
    private String memberNickname;
}
