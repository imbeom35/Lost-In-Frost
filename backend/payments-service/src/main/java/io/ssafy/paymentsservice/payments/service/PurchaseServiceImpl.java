package io.ssafy.paymentsservice.payments.service;

import io.ssafy.paymentsservice.entity.Costume;
import io.ssafy.paymentsservice.entity.Member;
import io.ssafy.paymentsservice.entity.MyCostume;
import io.ssafy.paymentsservice.payments.entity.PurchaseHistory;
import io.ssafy.paymentsservice.payments.repository.PurchaseHistoryRepository;
import io.ssafy.paymentsservice.repository.CostumeRepository;
import io.ssafy.paymentsservice.repository.MemberRepository;
import io.ssafy.paymentsservice.repository.MyCostumeRepository;
import io.ssafy.paymentsservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.paymentsservice.response.Response.ERROR;
import static io.ssafy.paymentsservice.response.Response.OK;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final MemberRepository memberRepository;
    private final CostumeRepository costumeRepository;
    private final MyCostumeRepository myCostumeRepository;

    @Override
    public Response<?> purchaseItem(Long costumeSeq, String memberId, String classification) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Costume costume = costumeRepository.findById(costumeSeq).orElse(null);
        MyCostume myCostume = myCostumeRepository.findByMemberIdAndCostumeSeq(memberId, costumeSeq).orElse(null);
        
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다!!", HttpStatus.BAD_REQUEST);
        }

        if (costume == null) {
            return ERROR("존재하지 않는 코스튬입니다!!", HttpStatus.BAD_REQUEST);
        }

        if (myCostume != null){
            return ERROR("이미 구매한 코스튬입니다!!", HttpStatus.BAD_REQUEST);
        }

        // 크리스탈
        if (classification.equals("crystal")) {
            if (member.getCrystal() < costume.getCrystalPrice()) {
                return ERROR("보유 크리스탈이 부족합니다!!", HttpStatus.BAD_REQUEST);
            }

            PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                    .member(member)
                    .state(1)
                    .quantity(1)
                    .price(costume.getCrystalPrice())
                    .classification(false)
                    .costumeSeq(costumeSeq)
                    .costumeName(costume.getName())
                    .costumeImage(costume.getImage())
                    .costumeGrade(costume.getGrade())
                    .build();

            purchaseHistoryRepository.save(purchaseHistory);

            MyCostume tmp = MyCostume.builder()
                    .member(member)
                    .purchaseHistorySeq(purchaseHistory)
                    .costumeSeq(costumeSeq)
                    .costumeName(costume.getName())
                    .costumeImage(costume.getImage())
                    .costumeGrade(costume.getGrade())
                    .build();
            myCostumeRepository.save(tmp);
            member.useCrystal(costume.getCrystalPrice());
            memberRepository.save(member);

            return OK(null);
        }
        // 코인
        else if (classification.equals("coin")) {
            if (member.getCoin() < costume.getCoinPrice()) {
                return ERROR("보유 코인이 부족합니다!!", HttpStatus.BAD_REQUEST);
            }

            PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                    .member(member)
                    .state(1)
                    .quantity(1)
                    .price(costume.getCrystalPrice())
                    .classification(true)
                    .costumeSeq(costumeSeq)
                    .costumeName(costume.getName())
                    .costumeImage(costume.getImage())
                    .costumeGrade(costume.getGrade())
                    .build();

            purchaseHistoryRepository.save(purchaseHistory);

            MyCostume tmp = MyCostume.builder()
                    .member(member)
                    .purchaseHistorySeq(purchaseHistory)
                    .costumeSeq(costumeSeq)
                    .costumeName(costume.getName())
                    .costumeImage(costume.getImage())
                    .costumeGrade(costume.getGrade())
                    .build();

            myCostumeRepository.save(tmp);
            member.useCoin(costume.getCoinPrice());
            memberRepository.save(member);
            return OK(null);
        } else {
            return ERROR("존재하지 않는 재화입니다!!", HttpStatus.BAD_REQUEST);
        }

    }
}
