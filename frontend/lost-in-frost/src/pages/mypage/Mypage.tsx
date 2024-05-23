import styled from "styled-components";
import Navigation from "@/component/organisms/Navigation";
import HeaderImage from "@/component/organisms/HeaderImage";
import Body from "@/component/organisms/Body";
import PageTitle from "@/component/organisms/PageTitle";
import MypageState from "@/component/molecules/mypage/MypageState";
import MypagePannel from "@/component/molecules/mypage/MypagePannel";
import { Route, Routes } from "react-router-dom";
import MypagePayment from "./MypagePayment";
import MypageCostume from "./MypageCostume";
import MypageInfo from "./MypageInfo";
import Footer from "@/component/organisms/Footer";
import useMypagePannel from "@/hook/useMypagePannel";
<<<<<<< HEAD
import TossSuccess from "./TossSuccess";
=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

const Mypage = () => {
  const [tabList, currentTab, onclickHandler] = useMypagePannel();

  return (
    <MypageStyle>
      <Navigation />
      <HeaderImage height={200} />
      <Body width={1200} topHeight={280}>
        <PageTitle text="마이페이지" />
        <MypageState />
        <MypagePannel tabList={tabList} currentTab={currentTab} onClickHandler={onclickHandler}>
          <Routes>
            <Route path="info" element={<MypageInfo />} />
            <Route path="costume" element={<MypageCostume />} />
            <Route path="payment" element={<MypagePayment />} />
<<<<<<< HEAD
            <Route path="toss-success" element={<TossSuccess />} />
=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
          </Routes>
        </MypagePannel>
      </Body>
      <Footer />
    </MypageStyle>
  );
};

const MypageStyle = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
`;

export default Mypage;
