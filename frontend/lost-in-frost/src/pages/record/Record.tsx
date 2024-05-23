import styled from "styled-components";
import Navigation from "@/component/organisms/Navigation";
import HeaderImage from "@/component/organisms/HeaderImage";
import Body from "@/component/organisms/Body";
import PageTitle from "@/component/organisms/PageTitle";
import Footer from "@/component/organisms/Footer";
import { Route, Routes } from "react-router-dom";
import RecordList from "./RecordList";
import RecordSearch from "./RecordSearch";

const Record = () => {
  return (
    <RecordContainer>
      <Navigation />
      <HeaderImage height={200} />
      <Body width={1000} topHeight={280}>
        <PageTitle text="전적" />
        <Routes>
          <Route path="search" element={<RecordSearch />} />
          <Route path="list/:id" element={<RecordList />} />
        </Routes>
      </Body>
      <Footer />
    </RecordContainer>
  );
};

const RecordContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
`;

export default Record;
