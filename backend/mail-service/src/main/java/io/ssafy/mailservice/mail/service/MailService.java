package io.ssafy.mailservice.mail.service;


import io.ssafy.mailservice.response.Response;

public interface MailService {

    Response<?> sendEmail(String email, String nickname);
    Response<?> reSendEmail(String email, String nickname);
    Response<?> verifyCode(String email, int code);
}
