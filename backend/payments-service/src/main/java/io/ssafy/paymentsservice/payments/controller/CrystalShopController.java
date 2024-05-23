package io.ssafy.paymentsservice.payments.controller;


import io.micrometer.core.annotation.Timed;
import io.ssafy.paymentsservice.payments.service.CrystalShopQueryService;
import io.ssafy.paymentsservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/crystal-shop")
@Tag(name = "결제 도메인", description = "크리스탈 상점 API")
public class CrystalShopController {

    private final CrystalShopQueryService crystalShopQueryService;

    @Timed(value = "payments.crystal-shop", longTask = true)
    @Operation(summary = "크리스탈 샾 품목 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "크리스탈 샾 품목 조회 성공"),
            @ApiResponse(responseCode = "400", description = "크리스탈 샾 품목 조회 실패"),
            @ApiResponse(responseCode = "404", description = "크리스탈 샾 품목 조회 실패"),
    })
    @GetMapping("/list")
    public Response<?> getCrystalShopItemList (@PageableDefault Pageable pageable) {
        log.info("## 크리스탈 샾 품목을 조회합니다!!");
        return crystalShopQueryService.getCrystalShopList(pageable);
    }


}
