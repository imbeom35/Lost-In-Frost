import { calculateHoursAgo, costumeImageUrl, getCurrentDate, parseDateAndTime } from "@/utils/formatter";
import styled from "styled-components";
import { ReactComponent as MessageIcon } from "@/asset/icon/Message.svg";
import { ReactComponent as ViewIcon } from "@/asset/icon/View.svg";
import { useNavigate } from "react-router-dom";

interface NoticeListContentListItemProps {
  item: any;
}

const NoticeListContentListItem = ({ item }: NoticeListContentListItemProps) => {
  const { date, time } = parseDateAndTime(item.createDatetime);
  const navigate = useNavigate();

  return (
    <NoticeListContentListItemStyle onClick={() => navigate(`/notice/views/${item.seq}`)}>
      <Type>공지</Type>
      <Title>{item.title}</Title>
      <IconBox>
        <MessageIconStyle></MessageIconStyle>
        <IconText>{item.commentCount}</IconText>
      </IconBox>
      <IconBox>
        <ViewIconStyle></ViewIconStyle>
        <IconText>{item.viewCount}</IconText>
      </IconBox>
      <DateText>{date === getCurrentDate() ? `${calculateHoursAgo(item.createDatetime)}시간 전` : date}</DateText>
    </NoticeListContentListItemStyle>
  );
};

const NoticeListContentListItemStyle = styled.div`
  height: 60px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
  border-bottom: 1px solid;
  border-color: var(--black-default);
  cursor: pointer;

  &:hover {
    background-color: var(--gray-light);
  }
`;

const Type = styled.div`
  width: 100px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid;
  border-color: var(--black-default);
  border-radius: 50px;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
`;

const Title = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  padding: 0px 40px 0px 40px;
`;

const IconBox = styled.div`
  width: 150px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
`;

const MessageIconStyle = styled(MessageIcon)`
  width: 25px;
  height: 25px;
  fill: var(--gray-dark);
  padding: 3px;
  box-sizing: border-box;
`;

const ViewIconStyle = styled(ViewIcon)`
  width: 25px;
  height: 25px;
  fill: var(--gray-dark);
`;

const IconText = styled.div`
  padding: 0px 5px 0px 5px;
  flex-grow: 1;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
  color: var(--gray-dark);
`;

const DateText = styled.div`
  width: 200px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
  color: var(--gray-dark);
`;

export default NoticeListContentListItem;
