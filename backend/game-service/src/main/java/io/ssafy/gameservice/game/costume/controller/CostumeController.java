package io.ssafy.gameservice.game.costume.controller;


import io.micrometer.core.annotation.Timed;
import io.ssafy.gameservice.game.costume.dto.CostumeListResDto;
import io.ssafy.gameservice.game.costume.service.CostumeQueryService;
import io.ssafy.gameservice.game.costume.service.CostumeService;
import io.ssafy.gameservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/game/costume")
@Tag(name = "게임 도메인", description = "코스튬 API")
public class CostumeController {

    private final CostumeQueryService costumeQueryService;
    private final CostumeService costumeService;

    @Timed(value = "game.costume.all-list", longTask = true)
    @Operation(summary = "코스튬 리스트 모두 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CostumeListResDto.class))),
            @ApiResponse(responseCode = "404", description = "코스튬 정보를 찾을 수 없습니다.")
    })
    @GetMapping("/all-list")
    public Response<?> getCostumeAllList() {
        return costumeQueryService.getCostumeAllList();
    }

    @Timed(value = "game.costume.list", longTask = true)
    @Operation(summary = "코스튬 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CostumeListResDto.class))),
            @ApiResponse(responseCode = "404", description = "코스튬 정보를 찾을 수 없습니다.")
    })
    @GetMapping("/list")
    public Response<?> getCostumeList(@PageableDefault Pageable pageable, @AuthenticationPrincipal User user) {
        if (user != null) {
            return costumeQueryService.getCostumeList(pageable, user.getUsername());

        } else {
            return costumeQueryService.getCostumeList(pageable, null);

        }
    }

    @Timed(value = "game.costume.count", longTask = true)
    @Operation(summary = "코스튬 개수 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CostumeListResDto.class))),
            @ApiResponse(responseCode = "404", description = "코스튬 정보를 찾을 수 없습니다.")
    })
    @GetMapping("/count")
    public Response<?> getCostumeCount(@AuthenticationPrincipal User user) {
        return costumeService.getCostumeCount();
    }

}
