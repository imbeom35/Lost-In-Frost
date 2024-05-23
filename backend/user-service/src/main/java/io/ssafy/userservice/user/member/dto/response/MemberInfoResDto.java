package io.ssafy.userservice.user.member.dto.response;

import io.ssafy.userservice.oauth2.enums.AuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "회원 정보 응답 DTO")
public class MemberInfoResDto {

    @Schema(description = "회원 이메일", example = "test@test.com")
    private String email;

    @Schema(description = "회원 닉네임", example = "테스트")
    private String nickname;

    @Schema(description = "회원 레벨", example = "1")
    private int level;

    @Schema(description = "회원 경험치", example = "0")
    private int experience;

    @Schema(description = "회원 보유 크리스탈", example = "0")
    private int crystal;

    @Schema(description = "회원 보유 코인", example = "0")
    private int coin;

    @Schema(description = "내 코스튬 식별자", example = "1")
    private Long myCostumeSeq;

    @Schema(description = "코스튬 식별자", example = "1")
    private Long costumeSeq;

    @Schema(description = "코스튬 이름", example = "테스트")
    private String costumeName;

    @Schema(description = "코스튬 이미지", example = "테스트")
    private String costumeImage;

    @Schema(description = "코스튬 등급", example = "테스트")
    private String costumeGrade;

    @Schema(description = "상태 메시지", example = "안녕~")
    private String message;

    @Schema(description = "인증 플랫폼 제공자", example = "1")
    private AuthProvider authProvider;

    @Schema(description = "게임 플레이 횟수", example = "1")
    private int gamePlayCount;

    @Schema(description = "게임 승리 횟수", example = "1")
    private int successCount;
}
