import { PostNoticeCommentWriteParams, postNoticeCommentWrite } from "@/apis/apiNotice";
import CustomButton from "@/component/atoms/button/CustomButton";
import { useEffect, useState } from "react";
import styled from "styled-components";

interface NoticeViewCommentListWriteProps {
  isLogin: boolean;
  comment: string;
  commentSubmit: () => void;
  onChangeHandler: (event: React.ChangeEvent<HTMLTextAreaElement>) => void;
}

const NoticeViewCommentListWrite = ({
  isLogin,
  comment,
  commentSubmit,
  onChangeHandler,
}: NoticeViewCommentListWriteProps) => {
  return (
    <NoticeViewCommentWriteStyle>
      <NoticeTextarea
        placeholder={isLogin ? "댓글을 입력해주세요." : "로그인이 필요합니다."}
        onChange={onChangeHandler}
        value={comment}
      />
      <Controller>
        <CommentCnt $isOver={comment.length > 200}>
          {comment.length > 200 ? "최대 글자수(200)을 초과했습니다." : `(${comment.length}/200)`}
        </CommentCnt>
        <CustomButton style="default" color="black" label="등록" size="large" onClick={commentSubmit} />
      </Controller>
    </NoticeViewCommentWriteStyle>
  );
};

const NoticeViewCommentWriteStyle = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: var(--gray-default);
  border: 1px solid;
  border-color: var(--gray-dark);
  padding: 20px;
  box-sizing: border-box;
  margin-top: 30px;
`;

const NoticeTextarea = styled.textarea`
  height: 150px;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  border: none;
  outline: none;
  overflow: auto;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 300;
  resize: none;
`;

const Controller = styled.div`
  width: 100%;
  height: 60px;
  display: flex;
  flex-direction: row;
  align-items: end;
  justify-content: space-between;
`;

const CommentCnt = styled.div<{ $isOver: boolean }>`
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 300;
  color: ${(props) => (props.$isOver ? "var(--system-red)" : "var(--black-default)")};
`;

export default NoticeViewCommentListWrite;
