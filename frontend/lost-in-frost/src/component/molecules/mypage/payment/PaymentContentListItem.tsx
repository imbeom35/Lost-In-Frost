import { styled } from "styled-components";
import { searchTypeId } from "@/type/payment";
import { GetPurchaseListResponse, GetTossListResponse } from "@/apis/apiPayment";
import { S3_URL } from "@/constant/api";
import { costumeImageUrl, parseDateAndTime } from "@/utils/formatter";

interface PaymentContentListItem {
  type: searchTypeId;
  item: any;
}

const PaymentContentListItem = ({ type, item }: PaymentContentListItem) => {
  let itemDate: string = "";
  let itemTime: string = "";
  if (type == "toss") {
    const { date, time } = parseDateAndTime(item.paymentApprovedAt);
    itemDate = date;
    itemTime = time;
  } else if (type === "crystal" || type === "coin") {
    const { date, time } = parseDateAndTime(item.purchaseDatetime);
    itemDate = date;
    itemTime = time;
  }

  return (
    <PaymentTossItemStyle>
      {type === "toss" && (
        <BoxWrapper>
          <Box>{item.paymentOrderName}</Box>
          <Box $width={500}>{item.crystalShopCrystalAmount}</Box>
          <Box $width={500}>{item.paymentAmount}</Box>
          <SmallBox $width={500}>
            <Text>{itemDate}</Text>
            <Text>{itemTime}</Text>
          </SmallBox>
          <Box $width={500}>{item.paymentPayType}</Box>
          <Box $width={500}>{item.paymentPaySuccessStatus ? "성공" : "실패"}</Box>
          <SmallBox $width={500}>{item.paymentOrderId}</SmallBox>
        </BoxWrapper>
      )}
      {(type === "crystal" || type === "coin") && (
        <BoxWrapper>
          <ImageBox>
            <ImageWrapper>
              <Image src={costumeImageUrl("body", item.costumeImage)} width={30} height={30}></Image>
            </ImageWrapper>
            {item.costumeName}
          </ImageBox>
          <Box $width={500}>{item.costumeGrade}</Box>
          <Box $width={500}>{item.price}</Box>
          <Box $width={500}>{item.purchaseDatetime}</Box>
          <Box $width={500}>{item.state === 1 ? "성공" : "실패"}</Box>
        </BoxWrapper>
      )}
    </PaymentTossItemStyle>
  );
};

const PaymentTossItemStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

const BoxWrapper = styled.div`
  display: flex;
  flex-direction: row;
  height: 40px;
  border-bottom: 1px solid;
  border-color: var(--black-default);

  &:hover {
    background-color: var(--gray-default);
  }
`;

const ImageWrapper = styled.div`
  height: 40px;
  width: 40px;
  padding: 5px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Image = styled.img`
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  background-color: var(--gray-light);
`;

const Box = styled.div<{ $width?: number }>`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: ${(props) => (props.$width ? `${props.$width}px` : "100%")};
  height: 100%;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;
  color: var(--black-default);
`;

const SmallBox = styled.div<{ $width?: number }>`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: ${(props) => (props.$width ? `${props.$width}px` : "100%")};
  height: 100%;
  font-family: Pretendard;
  font-size: 12px;
  font-weight: 500;
  color: var(--black-default);
`;

const ImageBox = styled.div<{ $width?: number }>`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: ${(props) => (props.$width ? `${props.$width}px` : "100%")};
  height: 100%;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;
  color: var(--black-default);
`;

const Text = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default PaymentContentListItem;
