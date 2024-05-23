package io.ssafy.authservice.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 7) // 7 일
public class TokenRedis {

    @Id
    private String memberId;

    @Indexed
    private String accessToken;

    private String refreshToken;

}
