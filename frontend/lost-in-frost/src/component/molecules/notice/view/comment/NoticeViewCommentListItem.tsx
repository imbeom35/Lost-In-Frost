import Pipeline from "@/component/atoms/common/Pipeline";
import { costumeImageUrl, parseDateAndTime } from "@/utils/formatter";
import styled from "styled-components";

interface NoticeViewCommentListItemProps {
  item: any;
  itemDelete: (commentSeq: number) => void;
}

const NoticeViewCommentListItem = ({ item, itemDelete }: NoticeViewCommentListItemProps) => {
  const { date, time } = parseDateAndTime(item.createDatetime);

  return (
    <NoticeViewCommentListItemStyle className={`isMine--${item.mine}`}>
      <ProfileIcon src={costumeImageUrl("face", item.memberImage)}></ProfileIcon>
      <Comment>
        <CommentTitleWrapper>
          <Nickname>{item.memberNickname}</Nickname>
          <Datetime>{date + " " + time}</Datetime>
          {item.mine && <Pipeline width={20} height={16} thickness={2} color={"var(--gray-dark)"} />}
          {item.mine && <ClickText onClick={() => itemDelete(item.commentSeq)}>삭제</ClickText>}
        </CommentTitleWrapper>
        <CommentContent>{item.content}</CommentContent>
      </Comment>
    </NoticeViewCommentListItemStyle>
  );
};

const NoticeViewCommentListItemStyle = styled.div`
  display: flex;
  flex-direction: row;
  padding: 20px 30px 20px 30px;
  border-bottom: 1px solid;
  border-color: var(--gray-dark);

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }

  &.isMine--true {
    background-color: var(--primary-shoal);
  }
`;

const ProfileIcon = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50px;
  background-color: var(--gray-light);
`;

const Comment = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const CommentTitleWrapper = styled.div`
  height: 30px;
  display: flex;
  align-items: center;
  flex-direction: row;
`;

const Nickname = styled.div`
  flex-grow: 1;
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--secondary-dark);
`;

const Datetime = styled.div`
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--gray-dark);
`;

const ClickText = styled.div`
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--gray-dark);
  cursor: pointer;

  &:hover {
    color: var(--black-default);
  }
`;

const CommentContent = styled.div`
  min-height: 30px;
  display: flex;
  align-items: center;
  flex-direction: row;
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--black-default);
`;

export default NoticeViewCommentListItem;
