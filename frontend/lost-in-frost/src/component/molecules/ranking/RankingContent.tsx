import styled from "styled-components";
import RankingContentList from "./RankingContentList";

const RankingContent = () => {
  return (
    <RankingContentStyle>
      <RankingContentList />
    </RankingContentStyle>
  );
};

const RankingContentStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

export default RankingContent;
