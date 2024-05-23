import styled from "styled-components";
import CoinImage from "@/asset/icon/Coin.png";
import CrystalImage from "@/asset/icon/Crystal.png";

interface PurchaseButtonProps {
  type: "COIN" | "CRYSTAL";
  price: number;
  isHave?: boolean;
  onClick?: (event: React.MouseEvent<HTMLElement>) => void;
}

const PurchaseButton = ({ type, price, isHave, onClick }: PurchaseButtonProps) => {
  let ButtonStyle;

  return (
    <PurchaseButtonStyle className={`style--${isHave ? "NONE" : type}`} onClick={onClick}>
      {type === "COIN" ? <Image src={CoinImage} $isHave={isHave} width={20} height={20}></Image> : null}
      {type === "CRYSTAL" ? <Image src={CrystalImage} $isHave={isHave} width={20} height={20}></Image> : null}
      <Text>{price.toLocaleString()}</Text>
    </PurchaseButtonStyle>
  );
};

const PurchaseButtonStyle = styled.div`
  height: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
  padding: 0px 30px 0px 10px;
  border: 2px solid;
  border-radius: 10px;

  > *:nth-child(n + 2) {
    margin-left: 5px;
  }

  &.style--COIN {
    border-color: var(--primary-dark);
    color: var(--primary-dark);
    cursor: pointer;

    &:hover {
      background-color: var(--primary-dark);
      color: var(--white-default);
    }
  }

  &.style--CRYSTAL {
    border-color: var(--secondary-dark);
    color: var(--secondary-dark);
    cursor: pointer;

    &:hover {
      background-color: var(--secondary-dark);
      color: var(--white-default);
    }
  }

  &.style--NONE {
    border-color: var(--gray-dark);
    color: var(--gray-dark);
  }
`;

const Image = styled.img<{ $isHave?: boolean }>`
  ${(props) => (props.$isHave === true ? "filter: grayscale(100%);" : null)}
`;

const Text = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 14px;
  font-weight: 700;
`;

export default PurchaseButton;
