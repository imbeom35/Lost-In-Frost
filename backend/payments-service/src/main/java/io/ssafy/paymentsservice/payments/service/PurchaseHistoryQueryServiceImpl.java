package io.ssafy.paymentsservice.payments.service;

import io.ssafy.paymentsservice.entity.Member;
import io.ssafy.paymentsservice.payments.dto.response.PurchaseHistoryListResDto;
import io.ssafy.paymentsservice.payments.repository.PurchaseHistoryQueryRepository;
import io.ssafy.paymentsservice.repository.MemberRepository;
import io.ssafy.paymentsservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.paymentsservice.response.Response.ERROR;
import static io.ssafy.paymentsservice.response.Response.OK;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PurchaseHistoryQueryServiceImpl implements PurchaseHistoryQueryService{

    private final MemberRepository memberRepository;
    private final PurchaseHistoryQueryRepository purchaseHistoryQueryRepository;

    @Override
    public Response<?> getPurchaseHistoryList(String memberId, String classification, Pageable pageable) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

        if (classification.equals("crystal")) {
            Page<PurchaseHistoryListResDto> list = purchaseHistoryQueryRepository.getPurchaseHistoryList(memberId, false, pageable);

            if (list.isEmpty()) {
                return ERROR("구매 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
            } else {
                return OK(list);
            }
        } else if (classification.equals("coin")) {
            Page<PurchaseHistoryListResDto> list = purchaseHistoryQueryRepository.getPurchaseHistoryList(memberId, true, pageable);
            if (list.isEmpty()) {
                return ERROR("구매 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
            } else {
                return OK(list);
            }
        } else {
            return ERROR("존재하지 않는 재화 분류입니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
