import styled from "styled-components";
import { GetCrystalShopListParams, getCrystalShopList } from "@/apis/apiPayment";
import { useEffect, useState } from "react";
import CrystalShopCard from "./ModalPaymentCard";

const ModalPayment = () => {
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [dataList, setDataList] = useState<any[]>([]);

  useEffect(() => {
    const initComponent = async () => {
      try {
        const params: GetCrystalShopListParams = {
          page: page,
          size: size,
        };
        const response = await getCrystalShopList(params);
        if (response.data.success) {
          console.log(response);
          setDataList(response.data.response.content);
        } else {
          alert("상점 데이터를 불러오는데 실패했습니다.");
        }
      } catch (err) {
        console.log(err);
      }
    };
    initComponent();
  }, []);

  return (
    <PaymentModalBodyStyle>
      <CardWrapper>
        {dataList.map((item, key) => (
          <CrystalShopCard
            key={key}
            state="active"
            image={item.crystalImage}
            crystalShopSeq={item.crystalShopSeq}
            name={item.crystalShopCrystalName}
            amount={item.crystalShopCrystalAmount}
            price={item.crystalShopCrystalPrice}
          />
        ))}
      </CardWrapper>
    </PaymentModalBodyStyle>
  );
};

const PaymentModalBodyStyle = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  background-color: var(--gray-default);
  border-top: 1px solid;
  border-color: var(--gray-deep);
`;

const CardWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: auto;
  gap: 20px;
  flex-direction: row;
`;

export default ModalPayment;
