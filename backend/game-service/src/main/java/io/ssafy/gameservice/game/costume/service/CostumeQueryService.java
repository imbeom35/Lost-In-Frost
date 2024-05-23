package io.ssafy.gameservice.game.costume.service;

import io.ssafy.gameservice.response.Response;
import org.springframework.data.domain.Pageable;

public interface CostumeQueryService {

    Response<?> getCostumeAllList();
    Response<?> getCostumeList(Pageable pageable, String memberId);
}
