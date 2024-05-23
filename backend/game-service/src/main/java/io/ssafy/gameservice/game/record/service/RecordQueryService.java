package io.ssafy.gameservice.game.record.service;

import io.ssafy.gameservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



public interface RecordQueryService {

    Response<?> getRecordList(String nickname, Pageable pageable);

    Response<?> getRecordDetailList(String roomId);
}
