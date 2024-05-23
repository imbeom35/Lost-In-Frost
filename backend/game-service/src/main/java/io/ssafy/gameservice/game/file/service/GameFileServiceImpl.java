package io.ssafy.gameservice.game.file.service;

import io.ssafy.gameservice.game.file.entity.GameFile;
import io.ssafy.gameservice.game.file.repository.GameFileRepository;
import io.ssafy.gameservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.ssafy.gameservice.response.Response.ERROR;
import static io.ssafy.gameservice.response.Response.OK;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameFileServiceImpl implements GameFileService {

    private final GameFileRepository gameFileRepository;

    @Override
    public String getGameFileUrl(String type, Boolean useState) {
        GameFile gameFile = gameFileRepository.findByTypeAndUseState(type, useState).orElse(null);
        if (gameFile != null) {
            return "game/" + gameFile.getName() + "_" + gameFile.getVersion() + "." + gameFile.getExtension();
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> getVersion(String hash) {
        List<GameFile> gameFile = gameFileRepository.findAllByHash(hash);
        if (gameFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 파일입니다.");
        } else {
            for (GameFile file: gameFile) {
                if (file.isUseState()) {
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("최신 버전을 받아주세요!");
        }
    }
}
