import styled from "styled-components";
import RecordListContentListItem from "./RecordListContentListItem";
import RecordList from "@/hook/useRecordList";
import React from "react";

interface RecordListContentListProps {
  nickname: string;
}

const RecordListContentList = ({ nickname }: RecordListContentListProps) => {
  const [itemList, isOpenList, ref, openHandler] = RecordList(nickname, 10);

  return (
    <RecordListContentListStyle>
      <Top>
        <RoomBox>Room</RoomBox>
        <TimeBox>Time</TimeBox>
        <ScoreBox>Score</ScoreBox>
        <DateBox>Date</DateBox>
        <IconBox></IconBox>
      </Top>
      {itemList.map((item, index) => (
        <React.Fragment key={index}>
          <RecordListContentListItem
            ref={index === itemList.length - 1 ? ref : null}
            onClick={() => openHandler(item.roomId)}
            isOpen={isOpenList[index]}
            item={item}
            index={index}
          />
        </React.Fragment>
      ))}
    </RecordListContentListStyle>
  );
};

const RecordListContentListStyle = styled.div`
  display: flex;
  flex-direction: column;

  > *:nth-child(n + 2) {
    margin-top: 10px;
  }
`;

const Top = styled.div`
  padding: 10px 0px 10px 0px;
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  display: flex;
  flex-direction: row;
  background-color: var(--black-default);
  border: 3px solid;
  border-color: var(--black-default);
  border-radius: 15px;

  > *:nth-child(n + 2) {
    border-left: 1px solid;
    border-color: var(--white-default);
  }
`;

const RoomBox = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--white-default);
`;

const TimeBox = styled.div`
  width: 250px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--white-default);
`;

const ScoreBox = styled.div`
  width: 250px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--white-default);
`;

const DateBox = styled.div`
  width: 250px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--white-default);
`;

const IconBox = styled.div`
  width: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default RecordListContentList;
