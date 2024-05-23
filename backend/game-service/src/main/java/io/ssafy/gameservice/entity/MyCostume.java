package io.ssafy.gameservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class MyCostume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_costume_seq")
    @Comment("내 코스튬 식별자")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Comment("회원 식별자")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_history_seq")
    @Comment("결제 내역 식별자")
    private PurchaseHistory purchaseHistorySeq;

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
