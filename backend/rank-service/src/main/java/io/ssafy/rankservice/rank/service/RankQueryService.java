package io.ssafy.rankservice.rank.service;

import io.ssafy.rankservice.config.response.Response;
import org.springframework.data.domain.Pageable;

public interface RankQueryService {

    Response<?> getRankinList(Pageable pageable);
}
