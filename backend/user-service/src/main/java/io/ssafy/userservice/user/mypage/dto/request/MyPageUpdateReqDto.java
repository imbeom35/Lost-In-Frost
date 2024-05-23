package io.ssafy.userservice.user.mypage.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "마이페이지 수정 요청 DTO")
public class MyPageUpdateReqDto {

    @Schema(description = "유저의 닉네임", example = "테스트")
    private String nickname;

    @Schema(description = "유저의 비밀번호", example = "1234")
    private String password;
}
