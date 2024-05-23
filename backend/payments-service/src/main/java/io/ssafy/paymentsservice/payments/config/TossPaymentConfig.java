package io.ssafy.paymentsservice.payments.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TossPaymentConfig {

    @Value("${spring.payment.toss.test_client}")
    private String testClient;

    @Value("${spring.payment.toss.test_secret}")
    private String testSecret;

    @Value("${spring.payment.toss.success_url}")
    private String successUrl;

    @Value("${spring.payment.toss.fail_url}")
    private String failUrl;

    public static final String URL = "https://api.tosspayments.com/v1/payments/";
}
