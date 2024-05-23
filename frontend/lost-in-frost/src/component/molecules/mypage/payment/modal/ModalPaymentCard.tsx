import styled from "styled-components";
import { ReactComponent as Box } from "@/asset/icon/Box.svg";
import CustomButton from "@/component/atoms/button/CustomButton";
import { postTossRequest } from "@/apis/apiPayment";
import { TOSS_FAIL_URL, TOSS_SUCCESS_URL } from "@/constant/api";
import { loadTossPayments } from "@tosspayments/sdk";
import { crystalShopImageUrl } from "@/utils/formatter";

interface ModalPaymentCardProps {
  state: "active" | "inactive";
  image: string;
  amount: number;
  name: string;
  price: number;
  crystalShopSeq: number;
}

const ModalPaymentCard = ({ state, image, amount, name, price, crystalShopSeq }: ModalPaymentCardProps) => {
  const goToss = async () => {
    try {
      const response = await postTossRequest({
        payType: "CARD",
        amount: price,
        orderName: name,
        yourSuccessUrl: TOSS_SUCCESS_URL,
        yourFailUrl: TOSS_FAIL_URL,
        crystalShopSeq: crystalShopSeq,
      });

      const data = response.data.response;
      const clientKey = process.env.TOSS_KEY;
      const tossPayments = await loadTossPayments(clientKey!);

      tossPayments
        .requestPayment("카드", {
          amount: data.amount,
          orderId: data.orderId,
          orderName: data.orderName,
          customerName: data.customerName,
          customerEmail: data.customerEmail,
          successUrl: data.successUrl,
          failUrl: data.failUrl,
        })
        .catch((error) => {
          console.log(error);
          if (error.code === "USER_CANCEL") {
            alert("결제를 취소하였습니다");
          } else if (error.code === "INVALID_CARD_COMPANY") {
            alert("유효하지 않은 접근입니다.");
          }
        });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <CrystalShopCardStyle>
      <Title>{name}</Title>
      <ImageBox>
        <img src={crystalShopImageUrl(image)} width={100} />
      </ImageBox>
      <Amount>{`${amount} 크리스탈`}</Amount>
      <CustomButton
        onClick={goToss}
        style="default"
        color="secondary"
        label={`${price}원`}
        size="small"
        width={"100%"}
      />
    </CrystalShopCardStyle>
  );
};

const CrystalShopCardStyle = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 5px;
  padding: 10px;
  width: 150px;
  background-color: var(--white-default);
`;

const Title = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
`;

const ImageBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Amount = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  font-family: Pretendard;
  font-size: 14px;
  font-weight: 500;
`;

export default ModalPaymentCard;
