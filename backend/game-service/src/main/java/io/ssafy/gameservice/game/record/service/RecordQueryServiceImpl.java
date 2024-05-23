package io.ssafy.gameservice.game.record.service;

import io.ssafy.gameservice.entity.Member;
import io.ssafy.gameservice.game.record.dto.GameRecordResDto;
import io.ssafy.gameservice.game.record.entity.GameRecord;
import io.ssafy.gameservice.game.record.repository.GameRecordQueryRepository;
import io.ssafy.gameservice.game.record.repository.GameRecordRepository;
import io.ssafy.gameservice.repository.MemberRepository;
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
@Transactional
public class RecordQueryServiceImpl implements RecordQueryService{

    private final MemberRepository memberRepository;
    private final GameRecordRepository gameRecordRepository;
    private final GameRecordQueryRepository gameRecordQueryRepository;


    @Override
    public Response<?> getRecordList(String nickname, Pageable pageable) {
        Member member = memberRepository.findByNicknameAndIsDeleted(nickname, false).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
        }
        Page<GameRecordResDto> list = gameRecordQueryRepository.getRecordList(nickname, pageable);
        if (list.isEmpty()) {
            return ERROR("해당 유저의 기록이 없습니다.", HttpStatus.NOT_FOUND);
        } else {
            return OK(list);
        }
    }

    @Override
    public Response<?> getRecordDetailList(String roomId) {
        boolean flag = gameRecordRepository.existsByRoomId(roomId);
        if (!flag) {
            return ERROR("존재하지 않는 게임 기록입니다.", HttpStatus.NOT_FOUND);
        }
        List<GameRecordResDto> list = gameRecordQueryRepository.getRecordDetailList(roomId);
        if (list.isEmpty()) {
            return ERROR("해당 유저의 기록이 없습니다.", HttpStatus.NOT_FOUND);
        } else {
            return OK(list);
        }
    }
}
