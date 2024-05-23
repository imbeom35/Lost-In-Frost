package io.ssafy.authservice.global.exception;

import io.jsonwebtoken.ExpiredJwtException;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
