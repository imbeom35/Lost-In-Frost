import styled from "styled-components";
import RecordListContentState from "./RecordListContentState";
import RecordListContentList from "./RecordListContentList";

interface RecordListContentProps {
  nickname: string;
}

const RecordListContent = ({ nickname }: RecordListContentProps) => {
  return (
    <RecordListContentStyled>
      <RecordListContentState nickname={nickname}></RecordListContentState>
      <RecordListContentList nickname={nickname}></RecordListContentList>
    </RecordListContentStyled>
  );
};

const RecordListContentStyled = styled.div`
  display: flex;
  flex-direction: column;

  > *:nth-child(n + 2) {
    margin-top: 40px;
  }
`;

export default RecordListContent;
