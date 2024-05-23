import styled from "styled-components";
import Navigation from "@/component/organisms/Navigation";
import HeaderImage from "@/component/organisms/HeaderImage";
import Body from "@/component/organisms/Body";
import PageTitle from "@/component/organisms/PageTitle";
import RankingContent from "@/component/molecules/ranking/RankingContent";
import Footer from "@/component/organisms/Footer";

const Ranking = () => {
  return (
    <RankingContainer>
      <Navigation />
      <HeaderImage height={200} />
      <Body width={1200} topHeight={280}>
        <PageTitle text="랭킹" />
        <RankingContent></RankingContent>
      </Body>
      <Footer />
    </RankingContainer>
  );
};

const RankingContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
`;

export default Ranking;
