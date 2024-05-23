import { styled } from "styled-components";

interface PaymentContentListHeaderProps {
  type: string;
}

const PaymentContentListHeader = ({ type }: PaymentContentListHeaderProps) => {
  return (
    <PaymentContentListHeaderStyle>
      {type === "toss" && (
        <ColuumnName>
          <ColumnNameText>상품명</ColumnNameText>
          <ColumnNameText $width={500}>크리스탈</ColumnNameText>
          <ColumnNameText $width={500}>금액</ColumnNameText>
          <ColumnNameText $width={500}>날짜</ColumnNameText>
          <ColumnNameText $width={500}>결제수단</ColumnNameText>
          <ColumnNameText $width={500}>상태</ColumnNameText>
          <ColumnNameText $width={500}>주문번호</ColumnNameText>
        </ColuumnName>
      )}

      {(type === "crystal" || type == "coin") && (
        <ColuumnName>
          <ColumnNameText>코스튬</ColumnNameText>
          <ColumnNameText $width={500}>등급</ColumnNameText>
          <ColumnNameText $width={500}>금액</ColumnNameText>
          <ColumnNameText $width={500}>날짜</ColumnNameText>
          <ColumnNameText $width={500}>상태</ColumnNameText>
        </ColuumnName>
      )}
    </PaymentContentListHeaderStyle>
  );
};

const PaymentContentListHeaderStyle = styled.div``;

const ColuumnName = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 40px;
  background-color: var(--black-default);
`;

const ColumnNameText = styled.div<{ $width?: number }>`
  display: flex;
  align-items: center;
  justify-content: center;
  width: ${(props) => (props.$width ? `${props.$width}px` : "100%")};
  height: 100%;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--white-default);
`;

export default PaymentContentListHeader;
