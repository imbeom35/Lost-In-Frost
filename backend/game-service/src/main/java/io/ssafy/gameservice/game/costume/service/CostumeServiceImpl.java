package io.ssafy.gameservice.game.costume.service;

import io.ssafy.gameservice.game.costume.repository.CostumeRepository;
import io.ssafy.gameservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.gameservice.response.Response.OK;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CostumeServiceImpl implements CostumeService{

    private final CostumeRepository costumeRepository;

    @Override
    public Response<?> getCostumeCount() {
        Long count = costumeRepository.count();
        return OK(count);
    }
}
