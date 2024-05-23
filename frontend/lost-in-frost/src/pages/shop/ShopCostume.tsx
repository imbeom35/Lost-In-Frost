import ShopCostumeList from "@/component/molecules/shop/costume/ShopCostumeList";
import styled from "styled-components";

const ShopCostume = () => {
  return (
    <ShopCostumeStyle>
      <ShopCostumeList />
    </ShopCostumeStyle>
  );
};

const ShopCostumeStyle = styled.div`
  width: 100%;
  height: auto;
  padding: 50px 0px 50px 0px;
  border-top: 3px solid;
  border-bottom: 3px solid;
  border-color: var(--black-default);
`;

export default ShopCostume;
