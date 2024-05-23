package io.ssafy.rankservice.rank.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.rankservice.rank.service.RankQueryService;
import io.ssafy.rankservice.config.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rank")
@Slf4j
@Tag(name = "랭크 API", description = "랭킹 API")
public class RankController {

    private final RankQueryService rankQueryService;

    @Timed(value = "rank.getRankingList", longTask = true)
    @Operation(summary = "랭킹 조회")
    @GetMapping("/list")
    public Response<?> getRankingList(@PageableDefault Pageable pageable) {
        return rankQueryService.getRankinList(pageable);
    }

}
