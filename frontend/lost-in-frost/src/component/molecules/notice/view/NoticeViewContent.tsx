import CustomButton from "@/component/atoms/button/CustomButton";
import useNotice from "@/hook/useNotice";
import { isAdmin, parseDateAndTime } from "@/utils/formatter";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import NoticeViewCommentList from "./comment/NoticeViewCommentList";
import { DeleteNoticeDeleteParams, deleteNoticeDelete } from "@/apis/apiNotice";

interface NoticeViewContentProps {
  noticeSeq: number;
}

const NoticeViewContent = ({ noticeSeq }: NoticeViewContentProps) => {
  const navigate = useNavigate();
  const [
    title,
    content,
    memberId,
    memberNickname,
    createDatetime,
    commentCount,
    viewCount,
    onClickDelete,
    onClickUpdate,
  ] = useNotice(noticeSeq);

  const { date, time } = parseDateAndTime(createDatetime);

  const deleteNotice = async () => {
    try {
      const noticeDeleteParams: DeleteNoticeDeleteParams = {
        noticeSeq: noticeSeq,
      };
      const response = await deleteNoticeDelete(noticeDeleteParams);

      if (response && response.data.success) {
        navigate("/notice/list");
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <NoticeViewContentStyle>
      <NoticeTitle>[공지] {title}</NoticeTitle>
      <NoticeState>
        <NoticeStateNickname>{memberNickname}</NoticeStateNickname>
        <NoticeStateDatetime>{date + " " + time}</NoticeStateDatetime>
        <NoticeStateText>댓글 {commentCount}</NoticeStateText>
        <NoticeStateText>조회 {viewCount}</NoticeStateText>
      </NoticeState>
      <NoticeContent>{content}</NoticeContent>
      <NoticeController>
        {isAdmin() && (
          <CustomButton
            style="default"
            color="black"
            label="수정"
            size="large"
            onClick={() => navigate(`/notice/edit/${noticeSeq}`)}
          ></CustomButton>
        )}

        {isAdmin() && (
          <CustomButton style="default" color="black" label="삭제" size="large" onClick={deleteNotice}></CustomButton>
        )}

        <CustomButton
          style="default"
          color="black"
          label="목록으로"
          size="large"
          onClick={() => navigate("/notice/list")}
        ></CustomButton>
      </NoticeController>
      <NoticeViewCommentList noticeSeq={noticeSeq} />
    </NoticeViewContentStyle>
  );
};

const NoticeViewContentStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

const NoticeTitle = styled.div`
  width: 100%;
  height: 80px;
  background-color: var(--black-default);
  display: flex;
  align-items: center;
  justify-content: start;
  padding: 0px 30px 0px 30px;
  box-sizing: border-box;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 500;
  color: var(--white-default);
`;

const NoticeState = styled.div`
  width: 100%;
  height: 50px;
  background-color: var(--gray-light);
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
  padding: 0px 0px 0px 30px;
  box-sizing: border-box;
  border-top: 2px solid;
  border-bottom: 2px solid;
  border-color: var(--gray-dark);

  > *:nth-child(n + 3) {
    border-left: 2px solid;
    border-color: var(--black-light);
  }
`;

const NoticeStateNickname = styled.div`
  display: flex;
  align-items: center;
  justify-content: start;
  flex-grow: 1;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-light);
`;

const NoticeStateDatetime = styled.div`
  width: 250px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-light);
`;

const NoticeStateText = styled.div`
  width: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-light);
`;

const NoticeContent = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: start;
  padding: 30px 30px 30px 30px;
  box-sizing: border-box;
  border-bottom: 2px solid;
  border-color: var(--gray-dark);
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
  color: var(--black-default);
  white-space: pre;
`;

const NoticeController = styled.div`
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }
`;

export default NoticeViewContent;
