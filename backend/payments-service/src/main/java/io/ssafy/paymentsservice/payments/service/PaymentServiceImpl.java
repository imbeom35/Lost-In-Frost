package io.ssafy.paymentsservice.payments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ssafy.paymentsservice.entity.Member;
import io.ssafy.paymentsservice.payments.config.TossPaymentConfig;
import io.ssafy.paymentsservice.payments.dto.request.PaymentReqDto;
import io.ssafy.paymentsservice.payments.dto.response.PaymentFailDto;
import io.ssafy.paymentsservice.payments.dto.response.PaymentFinalResDto;
import io.ssafy.paymentsservice.payments.dto.response.PaymentResDto;
import io.ssafy.paymentsservice.payments.entity.CrystalShop;
import io.ssafy.paymentsservice.payments.entity.Payment;
import io.ssafy.paymentsservice.payments.repository.CrystalShopRepository;
import io.ssafy.paymentsservice.payments.repository.PaymentRepository;
import io.ssafy.paymentsservice.repository.MemberRepository;
import io.ssafy.paymentsservice.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.*;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.ssafy.paymentsservice.response.Response.ERROR;
import static io.ssafy.paymentsservice.response.Response.OK;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final TossPaymentConfig tossPaymentConfig;
    private final CrystalShopRepository crystalShopRepository;

    @Override
    public Response<?> requestTossPayment(Payment payment, String memberId, PaymentReqDto paymentReqDto) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return ERROR("해당 회원이 존재하지 않습니다", HttpStatus.BAD_REQUEST);
        } else {
            if (payment.getAmount() < 1000) {
                return ERROR("최소 결제 금액은 1000원입니다.", HttpStatus.BAD_REQUEST);
            }
            CrystalShop crystalShop = crystalShopRepository.findById(paymentReqDto.getCrystalShopSeq()).orElse(null);
            if (crystalShop == null) {
                return ERROR("해당 제품은 존재하지 않습니다", HttpStatus.BAD_REQUEST);
            }
            payment.setCrystalPayment(crystalShop, member);
            Payment tmp = paymentRepository.save(payment);
            PaymentResDto paymentResDto = tmp.toPaymentResDto();
            paymentResDto.setSuccessUrl(paymentReqDto.getYourSuccessUrl() == null ? tossPaymentConfig.getSuccessUrl() : paymentReqDto.getYourSuccessUrl());
            paymentResDto.setFailUrl(paymentReqDto.getYourFailUrl() == null ? tossPaymentConfig.getFailUrl() : paymentReqDto.getYourFailUrl());
            return OK(paymentResDto);
        }

    }

    @Override
    public Response<?> tossPaymentSuccess(String paymentKey, String orderId, Integer amount, String memberId) {
        Payment payment = verifyPayment(orderId, amount, memberId);
        if (payment == null) {
            return ERROR("결제 내역을 찾지 못했습니다", HttpStatus.BAD_REQUEST);
        } else {
            PaymentFinalResDto result = requestPaymentAccept(paymentKey, orderId, amount);
            if (result instanceof PaymentFinalResDto.paymentSuccess) {
                PaymentFinalResDto.paymentSuccess successResult = (PaymentFinalResDto.paymentSuccess) result;
                payment.approvePayment(paymentKey, result.isSuccess());
                payment.chargingCrystal(payment, payment.getCrystalShopCrystalAmount());
                payment.setCancelStatus(false);
                payment.setApprovedAt(successResult.getApprovedAt());
                paymentRepository.save(payment);
                return OK(result);
            } else {
                return ERROR(result.toString(), HttpStatus.BAD_REQUEST);
            }

        }

    }

    @Override
    public Response<?> tossPaymentFail(String code, String message, String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            return ERROR("존재하지 않는 결제입니다.", HttpStatus.NOT_FOUND);
        } else {
            payment.failPayment(message, false);
            return OK(PaymentFailDto.builder()
                    .errorCode(code)
                    .errorMessage(message)
                    .orderId(orderId));
        }
    }

    @Override
    public Response<?> tossPaymentCancelCrystal(String memberId, String paymentKey, String cancelReason) {
        Payment payment = paymentRepository.findByPaymentKeyAndCustomer_Id(paymentKey, memberId);
        if (payment == null) {
            return ERROR("결제가 존재하지 않습니다", HttpStatus.NOT_FOUND);
        }
        if (payment.getCustomer().getCrystal() >= payment.getAmount()) {
            payment.cancelPayment(cancelReason, true);
            return OK(tossPaymentCancel(paymentKey, cancelReason));
        } else {
            return ERROR("취소 금액이 보유 금액보다 큽니다", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Response<?> tossPaymentCancel(String paymentKey, String cancelReason) {
        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = getHeaders();
        JSONObject params = new JSONObject();
        params.put("cancelReason", cancelReason);
        String result = "";

//        return OK(restTemplate.postForObject(TossPaymentConfig.URL + paymentKey + "/cancel",
//                new HttpEntity<>(params, headers),
//                Map.class));
        return null;
    }

    @Override
    public Payment verifyPayment(String orderId, int amount, String memberId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            return null;
        } else {
            // 본인만 결제 가능 && 결제 금액이 일치 해야함
            if (payment.getAmount() != amount || !(payment.getCustomer().getId().equals(memberId))) {
                return null;
            }
        }
        return payment;
    }

    @Override
    public PaymentFinalResDto requestPaymentAccept(String paymentKey, String orderId, int amount) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodeBytes = encoder.encode((tossPaymentConfig.getTestSecret() + ":").getBytes());
        String authorizations = "Basic " + new String(encodeBytes, 0, encodeBytes.length);

        try {
            URL url = new URL(TossPaymentConfig.URL + paymentKey);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject obj = new JSONObject();
            obj.put("orderId", orderId);
            obj.put("amount", amount);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(obj.toString().getBytes());

            int code = connection.getResponseCode();
            boolean isSuccess = code == 200;

            InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

            Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            responseStream.close();

            ObjectMapper objectMapper = new ObjectMapper();
            PaymentFinalResDto.paymentSuccess paymentFinalResDto = objectMapper.readValue(jsonObject.toString(), PaymentFinalResDto.paymentSuccess.class);

            // 결제 성공 여부 확인
            if (isSuccess) {
                log.debug("## 결제 성공!!");
                log.debug(jsonObject.toString());
                paymentFinalResDto.setSuccess(true);
                return paymentFinalResDto;
            } else {
                log.debug("## 결제 실패!!");
                PaymentFinalResDto.paymentFail paymentStatus = objectMapper.readValue(jsonObject.toString(), PaymentFinalResDto.paymentFail.class);
                paymentFinalResDto.setSuccess(false);
                return paymentStatus;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

