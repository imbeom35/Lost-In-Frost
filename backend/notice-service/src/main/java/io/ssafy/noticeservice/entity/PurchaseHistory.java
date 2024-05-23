package io.ssafy.noticeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_history_seq")
    @Comment("구매 내역 식별자")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 식별자")
    private Member member;

    @NotNull
    @Column(name = "purchase_history_state")
    @Comment("구매 상태 = 1: 구매 완료, 2: 환불 처리중, 3: 환불 완료")
    private Integer state;

    @NotNull
    @Column(name = "purchase_history_quantity")
    @Comment("구매 수량")
    private int quantity;

    @NotNull
    @Column(name = "purchase_history_price")
    @Comment("구매 가격")
    private int price;

    @NotNull
    @Column(name = "purchase_history_classification", columnDefinition = "TINYINT(1) default 0")
    @Comment("재화 분류 = 0 : 크리스탈, 1 : 코인")
    private Boolean classification;

    @Column(name = "purchase_datetime")
    @Comment("구매 일자")
    @CreationTimestamp
    private Timestamp purchaseDatetime;

    @NotNull
    @Comment("코스튬 식별자")
    private Long costumeSeq;

    @NotNull
    @Column(length = 50)
    @Comment("코스튬 이름")
    private String costumeName;

    @NotNull
    @Comment("코스튬 이미지")
    private String costumeImage;

    @NotNull
    @Column(length = 20)
    @Comment("코스튬 등급")
    private String costumeGrade;
}
