package io.ssafy.rankservice.rank.service;

import io.ssafy.rankservice.config.response.Response;
import io.ssafy.rankservice.rank.dto.RankingListResDto;
import io.ssafy.rankservice.rank.repository.RankQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.rankservice.config.response.Response.ERROR;
import static io.ssafy.rankservice.config.response.Response.OK;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RankQueryServiceImpl implements RankQueryService{

    private final RankQueryRepository rankQueryRepository;

    @Override
    public Response<?> getRankinList(Pageable pageable) {
        Page<RankingListResDto> list = rankQueryRepository.getRanking(pageable);
        if (list.isEmpty()) {
            return ERROR("랭킹이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        } else {
            return OK(list);
        }
    }
}
