package io.ssafy.gameservice.game.file.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.gameservice.config.aws.S3Service;
import io.ssafy.gameservice.game.file.service.GameFileService;
import io.ssafy.gameservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/game/file")
@Tag(name = "게임 도메인", description = "게임 파일 API")
public class GameFIleController {

    private final S3Service s3Service;
    private final GameFileService gameFileService;

    @Timed(value = "game.file.launcher-download", longTask = true)
    @Operation(summary = "게임 런처 다운로드", description = "게임 런처 다운로드")
    @GetMapping("/launcher-download")
    public ResponseEntity<byte[]> getLauncherFile () throws IOException {
        String url = gameFileService.getGameFileUrl("launcher", true);
        log.info("## 런처 다운로드 : {}", url);
        return s3Service.getObject(url);
    }

    @Timed(value = "game.file.game-download", longTask = true)
    @Operation(summary = "게임 다운로드", description = "게임 다운로드")
    @GetMapping("/game-download")
    public ResponseEntity <byte[]> getGameExeFile () throws IOException {
        String url = gameFileService.getGameFileUrl("game", true);
        log.info("## 게임 다운로드 : {}", url);
        return s3Service.getObject(url);
    }

    @Timed(value = "game.file.version", longTask = true)
    @Operation(summary = "게임 버전 관리")
    @GetMapping("/version/{hash}")
    public ResponseEntity<?> getVersion(@PathVariable String hash) {
        log.info("## 게임 버전 관리 : {}", hash);
        return gameFileService.getVersion(hash);
    }




}
