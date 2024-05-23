package io.ssafy.paymentsservice.payments.service;

import io.ssafy.paymentsservice.entity.Member;
import io.ssafy.paymentsservice.payments.dto.response.ChargingHistoryDto;
import io.ssafy.paymentsservice.payments.dto.response.PaymentHistoryResDto;
import io.ssafy.paymentsservice.payments.repository.PaymentQueryRepository;
import io.ssafy.paymentsservice.repository.MemberRepository;
import io.ssafy.paymentsservice.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ssafy.paymentsservice.response.Response.ERROR;
import static io.ssafy.paymentsservice.response.Response.OK;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentQueryServiceImpl implements PaymentQueryService{

    private final MemberRepository memberRepository;
    private final PaymentQueryRepository paymentQueryRepository;

    @Override
    public Response<?> getPaymentHistory(Pageable pageable, String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("회원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }else {
            Page<PaymentHistoryResDto> list = paymentQueryRepository.getChargingHistory(pageable, memberId);
            if (list.isEmpty()) {
                return ERROR("결제 기록이 존재하지 않습니다", HttpStatus.NOT_FOUND);
            } else {
                return OK(list);
            }
        }
    }

    @Override
    public Response<?> getCrystalPurchaseHistory(Pageable pageable, String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("회원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        } else {
//            Page<ChargingHistoryDto> list = paymentQueryRepository.getCrystalPurchaseHistory(pageable, memberId);
            Page<ChargingHistoryDto> list = null;
            if (list.isEmpty()) {
                return ERROR("구매 기록이 존재하지 않습니다", HttpStatus.NOT_FOUND);
            } else {
                return OK(list);
            }
        }
    }
}
