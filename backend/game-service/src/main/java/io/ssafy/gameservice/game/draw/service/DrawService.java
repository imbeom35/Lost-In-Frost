package io.ssafy.gameservice.game.draw.service;


import io.ssafy.gameservice.response.Response;

public interface DrawService {

    Response<?> getDraw(String memberId, String classification);

    String getDrawResult();
}
