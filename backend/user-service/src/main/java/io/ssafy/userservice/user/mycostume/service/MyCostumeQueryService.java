package io.ssafy.userservice.user.mycostume.service;

import io.ssafy.userservice.global.response.Response;
import org.springframework.data.domain.Pageable;

public interface MyCostumeQueryService {

    Response<?> getMyCostumeList(String memberId, Pageable pageable);
    Response<?> getMyCostumeAllList(String memberId);
}
