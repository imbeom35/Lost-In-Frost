package io.ssafy.gameservice.game.draw.service;

import io.ssafy.gameservice.entity.Member;
import io.ssafy.gameservice.entity.MyCostume;
import io.ssafy.gameservice.game.costume.entity.Costume;
import io.ssafy.gameservice.game.costume.repository.CostumeRepository;
import io.ssafy.gameservice.game.draw.dto.response.DrawResDto;
import io.ssafy.gameservice.repository.MemberRepository;
import io.ssafy.gameservice.repository.MyCostumeRepository;
import io.ssafy.gameservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import static io.ssafy.gameservice.response.Response.ERROR;
import static io.ssafy.gameservice.response.Response.OK;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DrawServiceImpl implements DrawService{

    private final MemberRepository memberRepository;
    private final CostumeRepository costumeRepository;
    private final MyCostumeRepository myCostumeRepository;


    @Override
    public Response<?> getDraw(String memberId, String classification) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

        if (classification.equals("crystal")) {
            if (member.getCrystal() < 20) {
                return ERROR("크리스탈이 부족합니다.", HttpStatus.BAD_REQUEST);
            }
            member.useCrystal(20);
        } else {
            if (member.getCoin() < 2000) {
                return ERROR("코인이 부족합니다.", HttpStatus.BAD_REQUEST);
            }
            member.useCoin(2000);
        }
        memberRepository.save(member);

        List<Costume> costumeList = costumeRepository.findAllByGrade(getDrawResult());
        DrawResDto drawResDto = new DrawResDto();
        if (costumeList.isEmpty()) {
            return ERROR("뽑기 결과가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        } else {
            Random random = new Random();
            int randomIdx = random.nextInt(costumeList.size());

            Costume costume = costumeList.get(randomIdx);
            MyCostume myCostume = myCostumeRepository.findByMemberIdAndCostumeSeq(memberId, costume.getSeq()).orElse(null);

            if (myCostume == null) {
                myCostume = MyCostume.builder()
                        .costumeSeq(costume.getSeq())
                        .costumeName(costume.getName())
                        .costumeImage(costume.getImage())
                        .costumeGrade(costume.getGrade())
                        .member(member)
                        .build();
                myCostumeRepository.save(myCostume);


                return OK(drawResDto.entityToDto(myCostume));
            } else {
                return ERROR(drawResDto.entityToDto(myCostume).toString(), HttpStatus.ALREADY_REPORTED);
            }

        }
    }



    @Override
    public String getDrawResult() {
        RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("SecureRandom");
        RandomGenerator randomGenerator = factory.create();

        double randomNumber = randomGenerator.nextDouble(0.1,1.0);
        double[] probabilities = {0.7, 0.18, 0.1, 0.02};
        double cumulativeProbability = 0.0;

        int selectedValue = -1;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomNumber <= cumulativeProbability) {
                selectedValue = i;
                break;
            }
        }

        return switch (selectedValue) {
            case 0 -> "normal";
            case 1 -> "epic";
            case 2 -> "unique";
            case 3 -> "legendary";
            default -> null;
        };

    }


}
