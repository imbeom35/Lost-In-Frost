package io.ssafy.userservice.domain.entity;

import io.ssafy.userservice.domain.dto.PayType;
import io.ssafy.userservice.domain.dto.PaymentResDto;
import io.ssafy.userservice.user.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_seq")
    @Comment("결제 식별자")
    private Long seq;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_pay_type")
    @Comment("결제 방식")
    private PayType payType;

    @Column(name = "payment_amount")
    @Comment("결제 금액")
    private int amount;

    @Column(name = "payment_order_name")
    @Comment("결제 주문명")
    private String orderName;

    @Column(name = "payment_order_id")
    @Comment("결제 주문번호")
    private String orderId;

    @Column(name = "payment_pay_success_type")
    @Comment("결제 성공 여부")
    private Boolean paySuccessStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "member_id")
    @Comment("손님 식별자")
    private Member customer;

    @Column(name = "crystal_shop_seq")
    @Comment("크리스탈 상점 식별자")
    private Long crystalShopSeq;

    @Column(name = "crystal_shop_crystal_name")
    @Comment("크리스탈 상점 크리스탈 이름")
    private String crystalShopCrystalName;

    @Column(name = "crystal_shop_crystal_amount")
    @Comment("크리스탈 상점 크리스탈 개수")
    private int crystalShopCrystalAmount;

    @Column(name = "crystal_shop_crystal_price")
    @Comment("크리스탈 상점 크리스탈 가격")
    private int crystalShopCrystalPrice;

    @Column(name = "payment_payment_key")
    @Comment("결제 고유 키")
    private String paymentKey;

    @Column(name = "payment_fail_reason")
    @Comment("결제 실패 사유")
    private String failReason;

    @Column(name = "payment_cancel_status")
    @Comment("결제 취소 여부")
    private Boolean cancelStatus;

    @Column(name = "payment_cancel_reason")
    @Comment("결제 취소 사유")
    private String cancelReason;

    @Column(name = "payment_approvedAt")
    @Comment("결제 승인 시간")
    private String approvedAt;

    public void setCustomer(Member member) {
        this.customer = member;
    }

    public PaymentResDto toPaymentResDto() {
        return PaymentResDto.builder()
                .payType(payType.getDescription())
                .amount(amount)
                .orderName(orderName)
                .orderId(orderId)
                .customerEmail(customer.getEmail())
                .customerName(customer.getUsername())
                .createdAt(null)
                .cancelStatus(cancelStatus)
                .failReason(failReason)
                .build();

    }

    public void setCrystalPayment(CrystalShop crystalShop, Member member) {
        this.crystalShopSeq = crystalShop.getSeq();
        this.crystalShopCrystalName = crystalShop.getCrystalName();
        this.crystalShopCrystalAmount = crystalShop.getCrystalAmount();
        this.crystalShopCrystalPrice = crystalShop.getCrystalPrice();
        this.customer = member;
    }

    public void chargingCrystal(Payment payment, int amount) {
        this.customer.setCrystal(payment.getCustomer().getCrystal() + amount);
    }

    public void approvePayment(String paymentKey, boolean paySuccessStatus) {
        this.paymentKey = paymentKey;
        this.paySuccessStatus = paySuccessStatus;
    }

    public void failPayment(String failReason, boolean paySuccessStatus) {
        this.failReason = failReason;
        this.paySuccessStatus = paySuccessStatus;
    }

    public void cancelPayment (String cancelReason, boolean cancelStatus) {
        this.cancelReason = cancelReason;
        this.cancelStatus = cancelStatus;
        this.customer.setCrystal(this.customer.getCrystal() - this.amount);
    }

}
