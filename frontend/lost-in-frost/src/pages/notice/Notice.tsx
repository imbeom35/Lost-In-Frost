import styled from "styled-components";
import Navigation from "@/component/organisms/Navigation";
import HeaderImage from "@/component/organisms/HeaderImage";
import Body from "@/component/organisms/Body";
import PageTitle from "@/component/organisms/PageTitle";
import Footer from "@/component/organisms/Footer";
import { Route, Routes } from "react-router-dom";
import NoticeList from "./NoticeList";
import NoticeViews from "./NoticeViews";
import NoticeWrite from "./NoticeWrite";
import NoticeEdit from "./NoticeEdit";

const Notice = () => {
  return (
    <NoticeContainer>
      <Navigation />
      <HeaderImage height={200} />
      <Body width={1200} topHeight={280}>
        <PageTitle text="공지사항" />
        <Routes>
          <Route path="list" element={<NoticeList />} />
          <Route path="views/:id" element={<NoticeViews />} />
          <Route path="write" element={<NoticeWrite />} />
          <Route path="edit/:id" element={<NoticeEdit />} />
        </Routes>
      </Body>
      <Footer />
    </NoticeContainer>
  );
};

const NoticeContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
`;

export default Notice;
