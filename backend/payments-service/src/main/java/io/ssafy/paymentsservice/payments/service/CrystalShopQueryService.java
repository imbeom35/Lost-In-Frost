package io.ssafy.paymentsservice.payments.service;

import io.ssafy.paymentsservice.response.Response;
import org.springframework.data.domain.Pageable;

public interface CrystalShopQueryService {

    Response<?> getCrystalShopList(Pageable pageable);
}
