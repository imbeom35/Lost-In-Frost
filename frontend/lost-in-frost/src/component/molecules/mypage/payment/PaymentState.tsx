import { styled } from "styled-components";
import CoinImage from "@/asset/icon/Coin.png";
import CrystalImage from "@/asset/icon/Crystal.png";
import CustomButton from "../../../atoms/button/CustomButton";
import { useEffect, useState } from "react";
import Modal from "@/component/molecules/common/Modal";
import ModalPayment from "@/component/molecules/mypage/payment/modal/ModalPayment";
import { useNavigate } from "react-router-dom";
import { getUserMyPageAmount } from "@/apis/apiUser";

const PaymentState = () => {
  const [coin, setCoin] = useState("0");
  const [crystal, setCrystal] = useState("0");
<<<<<<< HEAD
  const [isOpen, setIsOpen] = useState(false);
=======

  const navigate = useNavigate();
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

  useEffect(() => {
    const initComponent = async () => {
      try {
        const response = await getUserMyPageAmount();
        setCoin(response.data.response.coin);
        setCrystal(response.data.response.crystal);
      } catch (err) {
        console.log(err);
      }
    };
    initComponent();
  }, []);

<<<<<<< HEAD
=======
  const [isOpen, setIsOpen] = useState(false);

>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };

  const exitModal = () => {
    openModalHandler();
  };

  return (
    <PaymentStateStyle>
      <Line>
        <img src={CoinImage} width={40} height={40} />
        <Text>보유 코인 {coin.toLocaleString()}개</Text>
      </Line>
      <Line>
        <img src={CrystalImage} width={40} height={40} />
        <Text>보유 크리스탈 {crystal.toLocaleString()}개</Text>
<<<<<<< HEAD
        <CustomButton
          onClick={openModalHandler}
          style="default"
          color="secondary"
          label="충전하기"
          size="large"
        />
=======
        <CustomButton onClick={openModalHandler} style="default" color="secondary" label="충전하기" size="large" />
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
        {isOpen ? (
          <Modal onClickExit={exitModal} title="크리스탈 상점">
            <ModalPayment />
          </Modal>
        ) : null}
      </Line>
    </PaymentStateStyle>
  );
};

const PaymentStateStyle = styled.div`
  display: flex;
  flex-direction: column;
  > *:nth-child(n + 2) {
    margin-top: 10px;
  }
`;

const Line = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const Text = styled.div`
  height: 100%;
  margin-left: 10px;
  margin-right: 10px;
  display: flex;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
`;

export default PaymentState;
