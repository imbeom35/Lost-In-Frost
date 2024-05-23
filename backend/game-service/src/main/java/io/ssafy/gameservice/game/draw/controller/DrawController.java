package io.ssafy.gameservice.game.draw.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.gameservice.game.draw.service.DrawService;
import io.ssafy.gameservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/game/draw")
@Tag(name="게임 도메인", description = "뽑기 API")
public class DrawController {

    private final DrawService drawService;

    @Timed(value = "game.draw", longTask = true)
    @Operation(summary = "뽑기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "뽑기 성공"),
            @ApiResponse(responseCode = "400", description = "뽑기 실패")
    })
    @GetMapping("/{classification}")
    public Response<?> getDraw(@AuthenticationPrincipal User user, @PathVariable String classification) {
        log.info("## 뽑기 : {}, {}", user.getUsername(), classification);
        if (classification.equals("crystal") || classification.equals("coin")) {
            return drawService.getDraw(user.getUsername(), classification);
        } else {
            return Response.ERROR("존재하지 않는 재화입니다.", HttpStatus.BAD_REQUEST);
        }
    }

}
