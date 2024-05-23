package io.ssafy.userservice.global.config.security;

public class ExpireTime {

    public static final long ACCESS_TOKEN_EXPIRE_TIME = 6 * 60 * 60 * 1000L;               //6시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;     //7일
}
