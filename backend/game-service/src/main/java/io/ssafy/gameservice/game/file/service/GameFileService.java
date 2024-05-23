package io.ssafy.gameservice.game.file.service;

import io.ssafy.gameservice.response.Response;
import org.springframework.http.ResponseEntity;

public interface GameFileService {

    String getGameFileUrl(String type, Boolean useState);

    ResponseEntity<?> getVersion(String hash);
}
