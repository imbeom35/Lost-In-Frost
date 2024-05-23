import { costumeImageUrl, parseDateAndTime, secondsToTime } from "@/utils/formatter";
import { ReactComponent as UpIcon } from "@/asset/icon/Arrow-Up.svg";
import { ReactComponent as DownIcon } from "@/asset/icon/Arrow-Down.svg";
import styled, { css, keyframes } from "styled-components";
import React, { forwardRef, useEffect, useState } from "react";

interface RecordListContentListItemProps {
  ref: ((node?: Element | null | undefined) => void) | null;
  item: any;
  isOpen: boolean;
  onClick: () => void;
  index: number;
}

const RecordListContentListItem = forwardRef<HTMLDivElement, RecordListContentListItemProps>((props, ref) => {
  const details = props.item.detail;
  const { date, time } = parseDateAndTime(props.item.startAt);
  const [isAnimating, setIsAnimating] = useState(false);

  // useEffect(() => {
  //   setIsAnimating(true);
  // }, []);

  // if (!isAnimating) {
  //   return null;
  // }

  return (
    <RecordListContentListItemStyle ref={ref} index={props.index} isAnimating={isAnimating}>
      <RecordListItem onClick={props.onClick} className={`state--${props.item.clear}`}>
        <RoomBox>
          <MyProfile src={costumeImageUrl("face", props.item.memberImage)}></MyProfile>
          {Array.isArray(details) &&
            details.map(
              (detail, index) =>
                detail.memberId !== props.item.memberId && (
                  <OtherProfile src={costumeImageUrl("face", detail.memberImage)} key={index}></OtherProfile>
                )
            )}
        </RoomBox>
        <TimeBox>{secondsToTime(props.item.finishTime)}</TimeBox>
        <ScoreBox>{props.item.score}</ScoreBox>
        <DateBox>
          <DateText>{date}</DateText>
          <DateText>{time}</DateText>
        </DateBox>
        <IconBox>{props.isOpen ? <UpIconStyle /> : <DownIconStyle />}</IconBox>
      </RecordListItem>
      {props.isOpen && (
        <RecordDetail>
          <RecordDetailTitle>
            <RecordDetailTitleBox>플레이어</RecordDetailTitleBox>
            <RecordDetailTitleBox $width={250}>Time</RecordDetailTitleBox>
            <RecordDetailTitleBox $width={250}>Score</RecordDetailTitleBox>
            <RecordDetailTitleBox $width={250}>Result</RecordDetailTitleBox>
          </RecordDetailTitle>
          {Array.isArray(details) &&
            details.map((detail, index) => (
              <RecordDetailItem key={index} className={`state--${detail.clear}`}>
                <RecordDetailItemBox>
                  <RecordDetailItemBoxPlayer>
                    <ProfileIcon src={costumeImageUrl("face", detail.memberImage)} />
                    <Nickname>{detail.memberNickname}</Nickname>
                  </RecordDetailItemBoxPlayer>
                </RecordDetailItemBox>
                <RecordDetailItemBox $width={250}>{secondsToTime(detail.finishTime)}</RecordDetailItemBox>
                <RecordDetailItemBox $width={250}>{detail.score}</RecordDetailItemBox>
                <RecordDetailItemBox $width={250}>{detail.clear ? "생존" : "사망"}</RecordDetailItemBox>
              </RecordDetailItem>
            ))}
        </RecordDetail>
      )}
    </RecordListContentListItemStyle>
  );
});

const slideInFromLeft = keyframes`
  0% {
    transform: translateX(-30px);
  }
  100% {
    transform: translateX(0);
  }
`;

const RecordListContentListItemStyle = styled.div<{ index: number; isAnimating: boolean }>`
  width: 100%;
  display: flex;
  flex-direction: column;

  > *:nth-child(n + 2) {
    margin-top: 10px;
  }

  /* ${({ index, isAnimating }) =>
    isAnimating
      ? css`
          animation: ${slideInFromLeft} 0.5s ease-in-out ${index * 0.1}s;
        `
      : ""} */
`;

const RecordListItem = styled.div`
  box-sizing: border-box;
  width: 100%;
  height: 120px;
  display: flex;
  flex-direction: row;
  align-items: center;
  border: 3px solid;
  border-color: var(--black-default);
  border-radius: 15px;
  cursor: pointer;
  overflow: hidden;

  > *:nth-child(n + 2) {
    border-left: 1px solid;
    border-color: var(--black-default);
  }

  &.state--true {
    background-color: var(--blue-default);
  }

  &.state--false {
    background-color: var(--red-default);
  }
`;

const RecordDetail = styled.div`
  box-sizing: border-box;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: var(--white-default);
  border: 3px solid var(--black-default);
  border-radius: 15px;
  overflow: hidden;
`;

const RecordDetailTitle = styled.div`
  width: 100%;
  height: 40px;
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  background-color: var(--black-default);
`;

const RecordDetailTitleBox = styled.div<{ $width?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : `100%`)};
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--white-default);
`;

const RecordDetailItem = styled.div`
  width: 100%;
  height: 80px;
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  background-color: var(--white-default);

  &.state--true {
    background-color: var(--blue-default);
  }

  &.state--false {
    background-color: var(--red-default);
  }
`;

const RecordDetailItemBox = styled.div<{ $width?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : `100%`)};
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-default);
`;

const RecordDetailItemBoxPlayer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
  margin-left: 20px;

  > *:nth-child(n + 2) {
    margin-left: 20px;
  }
`;

const ProfileIcon = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background-color: var(--gray-light);
`;

const Nickname = styled.div`
  display: flex;
  align-items: center;
  justify-content: start;
`;

const RoomBox = styled.div`
  height: 80px;
  width: 100%;
  display: flex;
  align-items: baseline;
  justify-content: start;
  box-sizing: border-box;

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }
`;

const TimeBox = styled.div`
  width: 250px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--black-default);
`;

const ScoreBox = styled.div`
  width: 250px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--black-default);
`;

const DateBox = styled.div`
  width: 250px;
  height: 60px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: var(--black-default);
`;

const DateText = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
`;

const IconBox = styled.div`
  width: 150px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const MyProfile = styled.img`
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: var(--gray-light);
  margin-left: 20px;
`;

const OtherProfile = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background-color: var(--gray-light);
`;

const UpIconStyle = styled(UpIcon)`
  padding: 10px;
  box-sizing: border-box;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 2px solid;
  border-color: var(--black-default);
`;

const DownIconStyle = styled(DownIcon)`
  padding: 10px;
  box-sizing: border-box;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 2px solid;
  border-color: var(--black-default);
`;

export default RecordListContentListItem;
