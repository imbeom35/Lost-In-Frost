package io.ssafy.mailservice.mail.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Mail {

    @Id
    @Column(name = "mail_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("메일 식별자")
    private Long seq;

    @Column(name = "mail_email")
    @Comment("메일 주소")
    private String email;

    @Column(name = "mail_member_nickname")
    @Comment("회원 닉네임")
    private String memberNickname;

    @Column(name = "mail_code")
    @Comment("인증 코드")
    private int code;

    public void updateCode(int code) {
        this.code = code;
    }
}
