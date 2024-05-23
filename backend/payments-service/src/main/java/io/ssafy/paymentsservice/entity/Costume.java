package io.ssafy.paymentsservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@Table(indexes = {
        @Index(name = "idx_costume_grade", columnList = "costume_grade")
})
public class Costume {

    @Id
    @Column(name = "costume_seq")
    @Comment("친구 요청 내역 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotNull
    @Column(name = "costume_name", length = 50)
    @Comment("코스튬 이름")
    private String name;

    @NotNull
    @Column(name = "costume_image")
    @Comment("코스튬 이미지")
    private String image;

    @Column(name = "costume_grade", length = 20)
    @Comment("코스튬 등급 : normal, epic, unique, legendary")
    @ColumnDefault(value = "'normal'")
    private String grade;

    @Column(name = "costume_coin_price")
    @Comment("코스튬 게임머니 가격")
    private Integer coinPrice;

    @Column(name = "costume_cystal_price")
    @Comment("코스튬 크리스탈 가격")
    private Integer crystalPrice;

    @Column(name = "costume_is_deleted")
    @ColumnDefault(value = "false")
    @Comment("코스튬 삭제 여부")
    private Boolean isDeleted;
}
