import styled from "styled-components";
<<<<<<< HEAD
=======
import MainLogo from "@/asset/logo/MainLogo.png";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
import { ReactComponent as LoginSVG } from "@/asset/icon/Login.svg";
import { useNavigate } from "react-router-dom";
import { useEffect, useRef, useState } from "react";
import useNavigation from "@/hook/useNavigation";
import NavigationTab from "@/component/atoms/tab/NavigationTab";
import navigationStore from "@/store/navigationStore";
import LayerPopup from "./LayerPopup";
import { costumeImageUrl } from "@/utils/formatter";
import Modal from "../molecules/common/Modal";
import ModalGameStart from "../molecules/game/ModalGameStart";
import userStore from "@/store/userStore";

const Navigation = () => {
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

  const { currentTab, setCurrentTab } = navigationStore();
<<<<<<< HEAD
=======

>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  const [tabList, onClickHandler] = useNavigation();
  const [isPopupVisible, setIsPopupVisible] = useState<boolean>(false);
  const popupRef = useRef<any>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const handleOutsideClose = (e: { target: any }) => {
      if (isPopupVisible && !popupRef.current.contains(e.target)) setIsPopupVisible(false);
    };

    document.addEventListener("mousedown", handleOutsideClose);
    return () => document.removeEventListener("mousedown", handleOutsideClose);
<<<<<<< HEAD
  }, [isPopupVisible, localStorage.getItem("token")]);
=======
  }, [isPopupVisible, sessionStorage.getItem("token")]);

  const StartGame = () => {};
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

  const openModalHandler = () => {
    setIsModalOpen(!isModalOpen);
  };

  const exitModal = () => {
    openModalHandler();
  };

  return (
    <HeaderStyle height="80px">
      <TabList>
        {tabList.map((tab, index) =>
          tab.name === "Home" ? (
<<<<<<< HEAD
            <NeoStyle
              key={index}
              id={tab.name}
              $isActive={currentTab === "Home"}
              onClick={onClickHandler}
            >
              Lost In Frost
            </NeoStyle>
=======
            <Home key={index}>
              <MainLogoStyle src={MainLogo} id={tab.name} onClick={onClickHandler}></MainLogoStyle>
            </Home>
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
          ) : (
            <NavigationTab
              key={index}
              id={tab.name}
              width={150}
              label={tab.content}
              active={tab.name === currentTab}
              onClick={onClickHandler}
            />
          )
        )}
      </TabList>
      <Controller>
        {nickname.length === 0 ? (
          <Login onClick={() => navigate("/user/login")} className="active--true">
            <IconBox>
              <LoginSVG height={20} width={20}></LoginSVG>
            </IconBox>
            <LoginLabel>{"로그인"}</LoginLabel>
          </Login>
        ) : (
          <Login>
            <IconBox onClick={() => setIsPopupVisible(true)}>
              <Profile src={costumeImageUrl("face", costumeName)}></Profile>
              <LayerPopup isVisible={isPopupVisible} ref={popupRef}></LayerPopup>
            </IconBox>
            <LoginLabel onClick={() => setIsPopupVisible(true)}>{nickname}</LoginLabel>
          </Login>
        )}
        <GameStart onClick={openModalHandler}>GAME START</GameStart>
      </Controller>
      {isModalOpen ? (
        <Modal onClickExit={exitModal}>
          <ModalGameStart onClickExit={exitModal} />
        </Modal>
      ) : null}
    </HeaderStyle>
  );
};

const HeaderStyle = styled.div<{ height?: string }>`
  display: flex;
  position: fixed;
  z-index: 2;
  top: 0;
  left: 0;
  right: 0;
  align-items: stretch;
  justify-content: space-between;
  height: ${(props) => (props.height === "0px" ? "auto" : props.height)};
  background-color: var(--black-default);
`;

<<<<<<< HEAD
const NeoStyle = styled.div<{ $isActive: boolean }>`
  width: 250px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Neo;
  font-size: 30px;
  font-weight: 500;
  color: ${(props) => (props.$isActive ? "var(--primary-default)" : "var(--white-default)")};
  cursor: pointer;
`;

=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
const TabList = styled.div`
  display: flex;
  flex-direction: row;
  align-items: stretch;
`;

<<<<<<< HEAD
=======
const Home = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0px 30px 0px 30px;
`;

const MainLogoStyle = styled.img`
  width: 60px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;

  > * {
    pointer-events: none;
  }
`;

>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
const Controller = styled.div`
  display: flex;
  flex-direction: row;
  align-items: stretch;
  justify-content: center;
`;

const Login = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  padding: 0px 20px 0px 20px;

  &.active--true {
    &:hover {
      color: var(--primary-default);
    }
  }

  > :nth-child(n + 2) {
    margin-left: 10px;
  }
`;

const IconBox = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
`;

const LoginLabel = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--white-default);
  cursor: pointer;
`;

const Profile = styled.img`
  width: 40px;
  height: 40px;
  background-color: var(--gray-light);
  border-radius: 50%;
`;

const GameStart = styled.button`
  width: 260px;
  border: 0px;
  background-color: var(--primary-default);
  font-family: Pretendard;
  font-size: 32px;
  font-weight: 900;
  color: var(--black-default);
  cursor: pointer;

  &:hover {
    background-color: var(--primary-dark);
  }
`;

export default Navigation;
