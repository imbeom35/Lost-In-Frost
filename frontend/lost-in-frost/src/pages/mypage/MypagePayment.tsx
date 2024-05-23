import styled from "styled-components";
import PaymentState from "@/component/molecules/mypage/payment/PaymentState";
import PaymentContent from "@/component/molecules/mypage/payment/PaymentContent";

const MypagePayment = () => {
  return (
    <MypagePaymentStyle>
      <PaymentState></PaymentState>
      <PaymentContent></PaymentContent>
    </MypagePaymentStyle>
  );
};

const MypagePaymentStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

export default MypagePayment;
