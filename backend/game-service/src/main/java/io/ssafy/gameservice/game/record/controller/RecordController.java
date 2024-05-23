package io.ssafy.gameservice.game.record.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.gameservice.game.record.dto.GameRecordReqDto;
import io.ssafy.gameservice.game.record.service.RecordQueryService;
import io.ssafy.gameservice.game.record.service.RecordService;
import io.ssafy.gameservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/game/record")
@Tag(name = "게임 기록", description = "게임 기록 API")
public class RecordController {

    private final RecordService recordService;
    private final RecordQueryService recordQueryService;

    @Timed(value = "game.record.start", longTask = true)
    @Operation(description = "게임 기록 시작")
    @PostMapping("/start")
    public Response<?> startRecord(@AuthenticationPrincipal User user, @RequestBody GameRecordReqDto gameRecordReqDto) {
        log.info("## 게임 시작 기록 : {}", gameRecordReqDto.toString());
        return recordService.startRecord(user.getUsername(), gameRecordReqDto);
    }

    @Timed(value = "game.record.finish", longTask = true)
    @Operation(description = "게임 기록 종료")
    @PutMapping("/finish")
    public Response<?> finishRecord(@AuthenticationPrincipal User user, @RequestBody GameRecordReqDto gameRecordReqDto) {
        log.info("## 게임 종료 기록 : {}", gameRecordReqDto.toString());
        return recordService.finishRecord(user.getUsername(), gameRecordReqDto);
    }

    @Timed(value = "game.record.list", longTask = true)
    @Operation(description = "게임 기록 리스트 조회")
    @GetMapping("/list/{nickname}")
    public Response<?> getRecordList(@PathVariable String nickname, @PageableDefault Pageable pageable) {
        log.info("## 게임 기록 리스트 조회 : {}", nickname);
        return recordQueryService.getRecordList(nickname, pageable);
    }


    @Timed(value = "game.record.detail-list", longTask = true)
    @Operation(description = "게임 기록 상세 리스트 조회")
    @GetMapping("/list/detail/{roomId}")
    public Response<?> getRecordDetailList(@PathVariable String roomId) {
        log.info("## 게임 기록 상세 리스트 조회 : {}", roomId);
        return recordQueryService.getRecordDetailList(roomId);
    }

}
