package io.ssafy.gameservice.game.costume.service;

import io.ssafy.gameservice.game.costume.dto.CostumeListResDto;
import io.ssafy.gameservice.game.costume.repository.CostumeQueryRepository;
import io.ssafy.gameservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.ssafy.gameservice.response.Response.ERROR;
import static io.ssafy.gameservice.response.Response.OK;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CostumeQueryServiceImpl implements CostumeQueryService{

    private final CostumeQueryRepository costumeQueryRepository;

    @Override
    public Response<?> getCostumeAllList() {
        List<CostumeListResDto> list = costumeQueryRepository.getCostumeAllList();
        if (list.isEmpty()) {
            return ERROR("코스튬 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        } else {
            return OK(list);
        }
    }

    @Override
    public Response<?> getCostumeList(Pageable pageable, String memberId) {
        Page<CostumeListResDto> list = costumeQueryRepository.getCostumeList(pageable, memberId);
        if (list != null) {
            return OK(list);
        } else {
            return ERROR("코스튬 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
