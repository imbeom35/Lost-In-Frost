package io.ssafy.userservice.user.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "게임 전적 관련 유저 정보 응답 DTO")
public class MemberRecordInfoResDto {

    @Schema(description = "회원 식별자")
    private String memberId;

    @Schema(description = "회원 닉네임")
    private String nickname;

    @Schema(description = "회원 이미지")
    private String memberImage;

    @Schema(description = "회원 레벨")
    private int level;

    @Schema(description = "게임 플레이 횟수")
    private int gamePlayCount;

    @Schema(description = "게임 성공 횟수")
    private int successCount;


}
