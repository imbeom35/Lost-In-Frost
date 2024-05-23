import SelectBox from "@/component/atoms/selectBox/SelectBox";
import useSelectBox from "@/hook/useSelectBox";
import { useEffect } from "react";
import { styled } from "styled-components";
import PaymentContentList from "./PaymentContentList";

const PaymentContent = () => {
  const [isActive, itemList, currentItem, setItemList, onSelectClickHandler] = useSelectBox();

  useEffect(() => {
    setItemList([
      { id: "toss", name: "결제내역" },
      { id: "crystal", name: "크리스탈" },
      { id: "coin", name: "코인" },
    ]);
  }, []);

  console.log("#PaymentContent - currentItem:", currentItem);

  return (
    <PaymentContentStyle>
      <SelectBoxWrapper>
        <SelectBox
          itemList={itemList}
          isActive={isActive}
          currentItem={currentItem}
          height={30}
          width={150}
          onClickHandler={onSelectClickHandler}
        ></SelectBox>
      </SelectBoxWrapper>
      <PaymentContentList currentType={currentItem} />
    </PaymentContentStyle>
  );
};

const PaymentContentStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

const SelectBoxWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: end;
`;

export default PaymentContent;
