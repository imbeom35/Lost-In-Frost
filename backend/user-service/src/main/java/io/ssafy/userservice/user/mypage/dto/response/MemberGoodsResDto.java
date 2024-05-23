package io.ssafy.userservice.user.mypage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "유저의 코인과 크리스탈 보유량 조회 응답 DTO")
@AllArgsConstructor
public class MemberGoodsResDto {
    
    @Schema(description = "유저의 크리스탈 보유량")
    private Integer crystal;
    
    @Schema(description = "유저의 코인 보유량")
    private int coin;
}
