package io.ssafy.gameservice.game.record.service;

import io.ssafy.gameservice.game.record.dto.GameRecordReqDto;
import io.ssafy.gameservice.response.Response;

public interface RecordService {

    Response<?> startRecord(String memberId, GameRecordReqDto gameRecordReqDto);

    Response<?> finishRecord(String memberId, GameRecordReqDto gameRecordReqDto);

}
