import { PutUserMyCostume, PutUserMyCostumeParams } from "@/apis/apiUser";
import GradeBadge from "@/component/atoms/badge/GradeBadge";
import SystemBadge from "@/component/atoms/badge/SystemBadge";
import PurchaseButton from "@/component/atoms/button/PurchaseButton";
import Modal from "@/component/molecules/common/Modal";
import navigationStore from "@/store/navigationStore";
import { CostumeCardProps } from "@/type/costume";
import { costumeImageUrl } from "@/utils/formatter";
import { useState } from "react";
import { styled } from "styled-components";
import ModalShopCostume from "../shop/costume/modal/ModalShopCostume";
import { useNavigate } from "react-router-dom";
import userStore from "@/store/userStore";

<<<<<<< HEAD
const CostumeCard = ({
  type,
  costumeSeq,
  grade,
  name,
  image,
  isHave,
  coinPrice,
  crystalPrice,
}: CostumeCardProps) => {
=======
const CostumeCard = ({ type, costumeSeq, grade, name, image, isHave, coinPrice, crystalPrice }: CostumeCardProps) => {
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  const { myCostumeSeq, setMyCostumeSeq, setCostumeName } = userStore();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [purchaseType, setPurchaseType] = useState<"COIN" | "CRYSTAL">("COIN");
  const navigate = useNavigate();

  const changeCostume = async (event: React.MouseEvent<HTMLElement>) => {
    if (type === "MYPAGE" && costumeSeq) {
      try {
        const params: PutUserMyCostumeParams = {
          myCostumeSeq: costumeSeq,
        };
        const response = await PutUserMyCostume(params);

        if (response.data.success) {
          setMyCostumeSeq(costumeSeq);
          setCostumeName(name);
        } else {
          alert(response.data.error.message);
        }
      } catch (err) {
        console.log(err);
      }
    }
  };

  const openModalHandler = () => {
    setIsModalOpen(!isModalOpen);
  };

  const exitModal = () => {
    openModalHandler();
  };

  const purchaseOfCoin = () => {
<<<<<<< HEAD
    if (localStorage.getItem("token") === null) {
=======
    if (sessionStorage.getItem("token") === null) {
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      alert("로그인 후 이용해주세요");
      navigate("/user/login");
    } else {
      setPurchaseType("COIN");
      openModalHandler();
    }
  };

  const purchaseOfCyristal = () => {
<<<<<<< HEAD
    if (localStorage.getItem("token") === null) {
=======
    if (sessionStorage.getItem("token") === null) {
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      alert("로그인 후 이용해주세요");
      navigate("/user/login");
    } else {
      setPurchaseType("CRYSTAL");
      openModalHandler();
    }
  };

  return (
    <CostumeCardStyle>
      <CardWrapper $type={type} $isHave={isHave} onClick={changeCostume}>
        {type === "MYPAGE" && costumeSeq === myCostumeSeq ? (
          <State>
            <SystemBadge state="red">장착</SystemBadge>
          </State>
        ) : null}
        {type === "SHOP" && isHave ? (
          <State>
            <SystemBadge state="green">보유</SystemBadge>
          </State>
        ) : null}
        <ImageWrapper>
          <Image src={costumeImageUrl("body", image)} $isHave={isHave}></Image>
        </ImageWrapper>
        <ContentWrapper>
          <GradeBadge grade={isHave === true ? "none" : grade}>{grade}</GradeBadge>
          <ContentText>{name}</ContentText>
        </ContentWrapper>
      </CardWrapper>
<<<<<<< HEAD
      {coinPrice && (
        <PurchaseButton type="COIN" isHave={isHave} price={coinPrice} onClick={purchaseOfCoin} />
      )}
      {crystalPrice && (
        <PurchaseButton
          type="CRYSTAL"
          isHave={isHave}
          price={crystalPrice}
          onClick={purchaseOfCyristal}
        />
=======
      {coinPrice && <PurchaseButton type="COIN" isHave={isHave} price={coinPrice} onClick={purchaseOfCoin} />}
      {crystalPrice && (
        <PurchaseButton type="CRYSTAL" isHave={isHave} price={crystalPrice} onClick={purchaseOfCyristal} />
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      )}
      {isModalOpen && !isHave && (
        <Modal title="코스튬 구매" onClickExit={exitModal}>
          <ModalShopCostume
            purchaseType={purchaseType}
            type={"PURCHASE"}
            costumeSeq={costumeSeq}
            grade={grade}
            image={image}
            name={name}
            coinPrice={coinPrice}
            crystalPrice={crystalPrice}
            onClickCancel={exitModal}
          ></ModalShopCostume>
        </Modal>
      )}
    </CostumeCardStyle>
  );
};

const CostumeCardStyle = styled.div`
  display: flex;
  flex-direction: column;
  > *:nth-child(n + 2) {
    margin-top: 5px;
  }
`;

const CardWrapper = styled.div<{ $type?: string; $isHave?: boolean }>`
  position: relative;
  width: 200px;
  height: 280px;
  border-radius: 10px;
  border: 2px solid;
<<<<<<< HEAD
  border-color: ${(props) =>
    props.$isHave === true ? "var(--gray-dark)" : "var(--black-default)"};
=======
  border-color: ${(props) => (props.$isHave === true ? "var(--gray-dark)" : "var(--black-default)")};
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  overflow: hidden;
  display: flex;
  flex-direction: column;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  background-color: var(--white-default);
  ${(props) => (props.$type === "MYPAGE" ? "cursor: pointer;" : null)}
`;

const State = styled.div`
  position: absolute;
  top: 10px;
  right: 10px;
  transform: translate(0, 0);
`;

const ImageWrapper = styled.div`
  width: 100%;
  height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--gray-light);
`;

const Image = styled.img<{ $isHave?: boolean }>`
  width: 200px;
  height: auto;
  display: flex;
  ${(props) => (props.$isHave === true ? "filter: grayscale(100%);" : null)}
`;

const ContentWrapper = styled.div`
  height: 100%;
  padding: 0px 20px 0px 20px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: center;

  > *:nth-child(n + 2) {
    margin-top: 5px;
  }
`;

const ContentText = styled.div`
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500px;
  color: var(--black-default);
`;

export default CostumeCard;
