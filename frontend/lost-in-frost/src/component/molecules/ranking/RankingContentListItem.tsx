import { costumeImageUrl } from "@/utils/formatter";
import styled from "styled-components";

interface RankingContentListItemProps {
  item: any;
}

const RankingContentListItem = ({ item }: RankingContentListItemProps) => {
  return (
    <RankingContentListItemStyle>
      <Domain $width={200}>{item.rankSeq}</Domain>
      <PlayerWrapper>
        <Profile src={costumeImageUrl("face", item.memberCostume)}></Profile>
        <Nickname>{item.memberNickname}</Nickname>
        <SpeechBubble>{item.memberMessage ? item.memberMessage : ". . ."}</SpeechBubble>
      </PlayerWrapper>
      <Domain $width={200}>{item.memberLevel}</Domain>
      <Domain $width={200}>{item.memberExperience}</Domain>
      <Domain $width={200}>{item.memberGamePlayCount}</Domain>
    </RankingContentListItemStyle>
  );
};

const RankingContentListItemStyle = styled.div`
  height: 100px;
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid;
  border-color: var(--black-default);
`;

const Domain = styled.div<{ $width?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : "100%")};
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-default);
`;

const PlayerWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
  box-sizing: border-box;

  > *:nth-child(n) {
    margin-left: 20px;
  }
`;

const Profile = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50px;
  background-color: var(--gray-light);
`;

const Nickname = styled.div`
  white-space: nowrap;
  display: flex;
  flex-direction: row;
  align-items: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
`;

const SpeechBubble = styled.div`
  position: relative;
  width: auto;
  min-width: 80px;
  height: 50px;
  display: flex;
  align-items: center;
  background: var(--gray-default);
  border-radius: 10px;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  padding: 0px 20px 0px 20px;
  margin-right: 20px;

  &:after {
    content: "";
    position: absolute;
    left: 5%;
    top: 50%;
    width: 0;
    height: 0;
    border: 15px solid transparent;
    border-right-color: var(--gray-default);
    border-left: 0;
    margin-top: -15px;
    margin-left: -15px;
  }
`;

export default RankingContentListItem;
