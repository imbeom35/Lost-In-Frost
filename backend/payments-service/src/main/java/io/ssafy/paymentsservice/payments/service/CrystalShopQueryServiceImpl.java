package io.ssafy.paymentsservice.payments.service;

import io.ssafy.paymentsservice.payments.dto.response.CrystalShopListResDto;
import io.ssafy.paymentsservice.payments.repository.CrystalShopQueryRepository;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CrystalShopQueryServiceImpl implements CrystalShopQueryService{

    private final CrystalShopQueryRepository crystalShopQueryRepository;

    @Override
    public Response<?> getCrystalShopList(Pageable pageable) {
        Page<CrystalShopListResDto> list = crystalShopQueryRepository.getCrystalShopList(pageable);
        if (list.isEmpty()) {
            return ERROR("크리스탈 상점에 품목이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        } else {
            return OK(list);
        }
    }
}
