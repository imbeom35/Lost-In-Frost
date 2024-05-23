import styled from "styled-components";
import useScroll from "@/hook/useScroll";
import NoticeViewCommentListItem from "./NoticeViewCommentListItem";
import NoticeViewCommentListWrite from "./NoticeViewCommentListWrite";
import { ReactComponent as ArrowDownIcon } from "@/asset/icon/Arrow-Down.svg";
import useCommentWrite from "@/hook/useCommentWrite";

interface NoticeViewCommentListProps {
  noticeSeq: number;
}

const NoticeViewCommentList = ({ noticeSeq }: NoticeViewCommentListProps) => {
  const [itemList, commentCnt, isEnd, onClickHandler, itemDelete, reset] = useScroll(noticeSeq, 5);
  const [isLogin, comment, commentSubmit, onChangeHandler] = useCommentWrite(noticeSeq);

  const commentSubmitAndReset = async () => {
    if (0 < comment.length && comment.length <= 200) {
      if (await commentSubmit()) {
        reset();
      }
    }
  };

  return (
    <NoticeViewCommentListStyle>
      <CommentTop>Comment {commentCnt}</CommentTop>
      <CommentList>
        {itemList.map((item, index) => (
          <NoticeViewCommentListItem item={item} itemDelete={itemDelete} key={index}></NoticeViewCommentListItem>
        ))}
        {!isEnd && (
          <Next>
            <ArrowDownIconStyle onClick={onClickHandler} />
          </Next>
        )}
      </CommentList>
      <NoticeViewCommentListWrite
        isLogin={isLogin}
        comment={comment}
        commentSubmit={commentSubmitAndReset}
        onChangeHandler={onChangeHandler}
      />
    </NoticeViewCommentListStyle>
  );
};

const NoticeViewCommentListStyle = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const ArrowDownIconStyle = styled(ArrowDownIcon)`
  width: 40px;
  height: 40px;
  border-radius: 50px;
  padding: 10px;
  box-sizing: border-box;
  cursor: pointer;
  background-color: var(--primary-default);

  &:hover {
    background-color: var(--primary-dark);
  }
`;

const CommentTop = styled.div`
  height: 50px;
  display: flex;
  flex-direction: row;
  align-items: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-default);
  background-color: var(--gray-light);
  border-top: 2px solid;
  border-bottom: 2px solid;
  border-color: var(--gray-dark);
  padding: 0px 30px 0px 30px;
  box-sizing: border-box;
`;

const CommentList = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const Next = styled.div`
  height: 60px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid;
  border-color: var(--gray-dark);
`;

export default NoticeViewCommentList;
