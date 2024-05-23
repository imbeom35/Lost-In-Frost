import { styled } from "styled-components";
import React from "react";
import navigationStore from "@/store/navigationStore";
import CustomButton from "@/component/atoms/button/CustomButton";
import { BASE_URL } from "@/constant/api";
import CoinImage from "@/asset/icon/Coin.png";
import CrystalImage from "@/asset/icon/Crystal.png";
import userStore from "@/store/userStore";
<<<<<<< HEAD
import { logout } from "@/utils/user";
=======
import { logout } from "@/utils/logout";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

interface LayerPopupProps {
  isVisible: boolean;
}

const LayerPopup = React.forwardRef<HTMLDivElement, LayerPopupProps>(({ isVisible }, ref) => {
  const {
    email,
    nickname,
    level,
    experience,
    crystal,
    coin,
    myCostumeSeq,
    costumeSeq,
    costumeName,
    costumeImage,
    costumeGrade,
    message,
    authProvider,
    gamePlayCount,
    successCount,
    setEmail,
    setNickname,
    setLevel,
    setExperience,
    setCrystal,
    setCoin,
    setMyCostumeSeq,
    setCostumeSeq,
    setCostumeName,
    setCostumeImage,
    setCostumeGrade,
    setMessage,
    setAuthProvider,
    setGamePlayCount,
    setSuccessCount,
    reset,
  } = userStore();

  return (
    <LayerPopupStyle ref={ref} $isVisible={isVisible} id="layerPopup">
      <ItemWrapper>
        <Item>
          <img src={CoinImage} height={20} width={20} />
          <Text>{coin.toLocaleString()}</Text>
        </Item>
        <Item>
          <img src={CrystalImage} height={20} width={20} />
          <Text>{crystal.toLocaleString()}</Text>
        </Item>
        <CustomButton
          style="default"
          color="primary"
          label="로그아웃"
          size="small"
          onClick={() => logout()}
          width="100%"
        />
      </ItemWrapper>
    </LayerPopupStyle>
  );
});

const LayerPopupStyle = styled.div<{ $isVisible: boolean }>`
  position: absolute;
  left: 0px;
  top: 60px;
  width: 150px;
  display: ${(props) => (props.$isVisible ? "flex" : "none")};
  flex-direction: column;
  align-items: end;
  justify-content: center;
  border-radius: 5px;
  background-color: var(--white-default);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  padding: 10px;
`;

const ItemWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  > *:nth-child(n + 2) {
    margin-top: 10px;
  }
`;

const Item = styled.div`
  width: 100%;
  display: flex;
  border-radius: 5px;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
`;

const Text = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
`;

export default LayerPopup;
