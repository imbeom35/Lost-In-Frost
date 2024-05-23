package io.ssafy.authservice.entity;


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
public class CrystalShop {

    @Id
    @Column(name = "crystal_shop_seq")
    @Comment("크리스탈 상점 식별자")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotNull
    @Column(name = "crystal_shop_crystal_name", length = 100)
    @Comment("크리스탈 이름")
    private String crystalName;

    @Column(name = "crystal_shop_crystal_amount")
    @Comment("크리스탈 개수")
    @ColumnDefault(value = "1")
    private int crystalAmount;

    @NotNull
    @Column(name = "crystal_shop_crystal_price")
    @Comment("크리스탈 가격")
    private int crystalPrice;

    @Column(name = "crystal_shop_image")
    @Comment("크리스탈 이미지")
    private String image;
}
