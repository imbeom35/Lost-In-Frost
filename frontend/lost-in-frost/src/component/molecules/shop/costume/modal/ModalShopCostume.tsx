import CustomButton from "@/component/atoms/button/CustomButton";
import CostumeCard from "@/component/molecules/common/CostumeCard";
import { CostumeCardProps } from "@/type/costume";
import { styled } from "styled-components";
import CoinIcon from "@/asset/icon/Coin.png";
import CrystalIcon from "@/asset/icon/Crystal.png";
import { PostPurchaseParams, postPurchase } from "@/apis/apiPayment";

interface AdditionalProps {
  purchaseType: "COIN" | "CRYSTAL";
  onClickCancel: () => void;
}

const ModalShopCostume = ({
  type,
  costumeSeq,
  grade,
  name,
  image,
  isHave,
  coinPrice,
  crystalPrice,
  purchaseType,
  onClickCancel,
}: CostumeCardProps & AdditionalProps) => {
  const thisCoinPrice = coinPrice ? coinPrice.toLocaleString() : "error";
  const thisCrystalPrice = crystalPrice ? crystalPrice.toLocaleString() : "error";

  const purchaseCostume = async () => {
    let classType;
    switch (purchaseType) {
      case "COIN":
        classType = "coin";
        break;
      case "CRYSTAL":
        classType = "crystal";
        break;
    }

    if (classType) {
      const params: PostPurchaseParams = {
        costumeSeq: String(costumeSeq),
        classification: classType,
      };

      try {
        const response = await postPurchase(params);
        if (response.data.success) {
          onClickCancel();
          location.href = "/shop/costume";
        } else {
          console.log(response);
          alert(response.data.error.message);
          onClickCancel();
        }
      } catch (err) {
        console.log(err);
      }
    }
  };

  return (
    <ModalShopCostumeStyle>
      <ModalBody>
        <CostumeCard type={type} costumeSeq={costumeSeq} grade={grade} image={image} name={name} />
        <Receipt>
          <ReceiptItemWrapper>
            <ReceiptItem>
              <ReceiptItemName>{name} ⨉ 1</ReceiptItemName>
              <ReceiptItemPrice>
                {purchaseType === "COIN" ? thisCoinPrice : null}
                {purchaseType === "CRYSTAL" ? thisCrystalPrice : null}
                {purchaseType === "COIN" ? <img src={CoinIcon} width={20} height={20} /> : null}
                {purchaseType === "CRYSTAL" ? <img src={CrystalIcon} width={20} height={20} /> : null}
              </ReceiptItemPrice>
            </ReceiptItem>
          </ReceiptItemWrapper>
          <ReciptTotal>
            <ReciptTotalName>Total</ReciptTotalName>
            <ReciptTotalPrice>
              {purchaseType === "COIN" ? thisCoinPrice : null}
              {purchaseType === "CRYSTAL" ? thisCrystalPrice : null}
              {purchaseType === "COIN" ? <img src={CoinIcon} width={20} height={20} /> : null}
              {purchaseType === "CRYSTAL" ? <img src={CrystalIcon} width={20} height={20} /> : null}
            </ReciptTotalPrice>
          </ReciptTotal>
        </Receipt>
      </ModalBody>
      <ModalFooter>
        <CustomButton style="default" color="black" label="취소" size="medium" onClick={onClickCancel}></CustomButton>
        <CustomButton
          style="default"
          color="primary"
          label="구매하기"
          size="medium"
          onClick={purchaseCostume}
        ></CustomButton>
      </ModalFooter>
    </ModalShopCostumeStyle>
  );
};

const ModalShopCostumeStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

const ModalBody = styled.div`
  display: flex;
  flex-direction: row;
  padding: 30px;
  background-color: var(--white-default);
  border-top: 1px solid;
  border-bottom: 1px solid;
  border-color: var(--gray-deep);

  > *:nth-child(n + 2) {
    margin-left: 20px;
  }
`;

const ModalFooter = styled.div`
  height: 50px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;
  padding: 0px 10px 0px 10px;

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }
`;

const Receipt = styled.div`
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const ReceiptItemWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const ReceiptItem = styled.div`
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
`;

const ReceiptItemName = styled.div`
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--gray-dark);
`;

const ReceiptItemPrice = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--black-default);

  > *:nth-child(n + 1) {
    margin-left: 10px;
  }
`;

const ReciptTotal = styled.div`
  height: 60px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  border-top: 1px solid;
  border-color: var(--gray-deep);
`;

const ReciptTotalName = styled.div`
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--black-default);
`;

const ReciptTotalPrice = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--black-default);

  > *:nth-child(n + 1) {
    margin-left: 10px;
  }
`;

export default ModalShopCostume;
