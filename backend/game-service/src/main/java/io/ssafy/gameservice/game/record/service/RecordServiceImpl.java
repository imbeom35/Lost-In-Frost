package io.ssafy.gameservice.game.record.service;

import io.ssafy.gameservice.entity.Member;
import io.ssafy.gameservice.game.record.dto.FinishGameResDto;
import io.ssafy.gameservice.game.record.dto.GameRecordReqDto;
import io.ssafy.gameservice.game.record.entity.GameRecord;
import io.ssafy.gameservice.game.record.repository.GameRecordRepository;
import io.ssafy.gameservice.repository.MemberRepository;
import io.ssafy.gameservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.gameservice.response.Response.ERROR;
import static io.ssafy.gameservice.response.Response.OK;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecordServiceImpl implements RecordService{

    private final MemberRepository memberRepository;
    private final GameRecordRepository gameRecordRepository;

    @Transactional
    @Override
    public Response<?> startRecord(String memberId, GameRecordReqDto gameRecordReqDto) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        GameRecord tmp = GameRecord.builder()
                .roomId(gameRecordReqDto.getRoomId())
                .member(member)
                .finishTime(gameRecordReqDto.getFinishTime())
                .isClear(gameRecordReqDto.isClear())
                .difficulty(gameRecordReqDto.getDifficulty())
                .score(0)
                .startAt(gameRecordReqDto.getStartAt()).build();
        gameRecordRepository.save(tmp);
        return OK(null);
    }

    @Transactional
    @Override
    public Response<?> finishRecord(String memberId, GameRecordReqDto gameRecordReqDto) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }
        GameRecord gameRecord = gameRecordRepository.findByRoomIdAndMemberId(gameRecordReqDto.getRoomId(), memberId).orElse(null);
        if (gameRecord == null) {
            return ERROR("존재하지 않는 게임 기록입니다.", HttpStatus.BAD_REQUEST);
        }
        if (gameRecordReqDto.getFinishTime() > 1800) gameRecordReqDto.setFinishTime(1800);
        if (gameRecordReqDto.isClear()) {
            gameRecord.saveGameRecord(member, gameRecordReqDto.getFinishTime(), true, (int)(gameRecordReqDto.getFinishTime() * gameRecordReqDto.getDifficulty()) + 1200);
        } else {
            gameRecord.saveGameRecord(member, gameRecordReqDto.getFinishTime(), false, (int)(gameRecordReqDto.getFinishTime() * gameRecordReqDto.getDifficulty()));
        }

        gameRecordRepository.save(gameRecord);
        updateMemberInfo(member, gameRecordReqDto.getFinishTime(), gameRecordReqDto.isClear(), gameRecordReqDto.getDifficulty());

        return OK(new FinishGameResDto(member.getLevel(), member.getExperience(), member.getCoin()));
    }


    @Transactional
    public void updateMemberInfo(Member member, int finishTime, boolean isClear, float difficulty) {
        if (isClear) {
            member.setCoin(member.getCoin() + (int) (finishTime * difficulty) + 1200);
            member.setExperience(member.getExperience() + (int) (finishTime * difficulty) + 1200);
            member.updateSuccessCount();
        } else {
            member.setCoin(member.getCoin() + (int) (finishTime * difficulty));
            member.setExperience(member.getExperience() + (int) (finishTime * difficulty));
        }
        member.updateGamePlayCount();
        member.setLevel(member.getLevel() + (member.getExperience() / 100));
        member.setExperience(member.getExperience() % 100);
        memberRepository.save(member);

    }
}
