package io.ssafy.userservice.user.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchNicknameResDto {

    private String memberId;

    private String memberNickname;

    private String memberImage;

    private int memberLevel;
}
