package io.ssafy.mailservice.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@Getter
@AllArgsConstructor
@RedisHash(value = "mail", timeToLive = 60 * 5)
public class MailRedis {

    @Id
    private String email;

    @Indexed
    private String nickname;

    private int code;

    public void updateCode(int code) {
        this.code = code;
    }
}
