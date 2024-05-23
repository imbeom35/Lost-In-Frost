import { styled } from "styled-components";
import { ReactComponent as CostumeIcon } from "@/asset/icon/Costume.svg";

interface ShopTabProps {
  text: string;
  isActive: boolean;
  id: string;
  image: string;
  onClickHandler: (event: React.MouseEvent<HTMLElement>) => void;
}

const ShopTab = ({ text, isActive, id, image, onClickHandler }: ShopTabProps) => {
  return (
    <ShopTabStyle id={id} className={`state--${isActive}`} onClick={onClickHandler}>
      <CostumeIconStyle />
      <Text>{text}</Text>
    </ShopTabStyle>
  );
};

const ShopTabStyle = styled.div`
  height: 60px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 700;
  cursor: pointer;

  &.state--true {
    border-left: 8px solid;
    border-color: var(--secondary-default);
    color: var(--secondary-default);
    fill: var(--secondary-default);
  }

  &.state--false {
    border-left: 8px solid;
    border-color: var(--black-light);
    color: var(--white-default);
    fill: var(--white-default);
  }

  > *:nth-child(n + 2) {
    margin-left: 5px;
  }
`;

const CostumeIconStyle = styled(CostumeIcon)`
  width: 20px;
  height: 20px;
`;

const Text = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default ShopTab;
