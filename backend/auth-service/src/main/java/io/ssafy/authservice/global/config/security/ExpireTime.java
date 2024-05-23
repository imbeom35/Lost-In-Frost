package io.ssafy.authservice.global.config.security;

import org.springframework.beans.factory.annotation.Value;

public class ExpireTime {

//    public static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L; // 30분
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 3 * 1000L; // 3초
    public static final long REFRESH_TOKEN__EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일
}
