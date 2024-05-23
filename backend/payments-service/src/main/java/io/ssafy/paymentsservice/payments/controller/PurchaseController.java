package io.ssafy.paymentsservice.payments.controller;

import io.micrometer.core.annotation.Timed;
import io.ssafy.paymentsservice.payments.dto.request.PurchaseReqDto;
import io.ssafy.paymentsservice.payments.service.PurchaseHistoryQueryService;
import io.ssafy.paymentsservice.payments.service.PurchaseService;
import io.ssafy.paymentsservice.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static io.ssafy.paymentsservice.response.Response.OK;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/purchase")
@Tag(name = "결제 도메인", description = "아이템 구매 API")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseHistoryQueryService purchaseHistoryQueryService;

    @Timed(value = "payments.purchase", longTask = true)
    @Operation(summary = "아이템 구매")
    @PostMapping
    public Response<?> purchaseItem (@AuthenticationPrincipal User user, @RequestBody PurchaseReqDto purchaseReqDto) {
        log.info("## 아이템을 구매합니다!! : {}", purchaseReqDto.toString());
        return purchaseService.purchaseItem(purchaseReqDto.getCostumeSeq(), user.getUsername(), purchaseReqDto.getClassification());
    }

    @Timed(value = "payments.refund", longTask = true)
    @Operation(summary = "아이템 환불 관련 요청")
    @PutMapping("/refund")
    public Response<?> requestRefundItem (@AuthenticationPrincipal User user) {
        log.info("## 아이템 환불 관련 요청을 합니다!!");
        return OK(null);
    }

    @Timed(value = "payments.purchase-history", longTask = true)
    @Operation(summary = "아이템 구매 내역")
    @GetMapping("/list/{classification}")
    public Response<?> getItemPurchaseHistory (@AuthenticationPrincipal User user, @PathVariable String classification, @PageableDefault Pageable pageable) {
        log.info("## 아이템 구매 내역을 조회합니다!! : {}", classification);
        return purchaseHistoryQueryService.getPurchaseHistoryList(user.getUsername(), classification, pageable);
    }


}
