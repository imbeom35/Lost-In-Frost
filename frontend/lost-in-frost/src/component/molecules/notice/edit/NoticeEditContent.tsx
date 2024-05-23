import {
  GetNoticeDetailParams,
  PostNoticeWriteParams,
  PutNoticeUpdateParams,
  getNoticeDetail,
  postNoticeWrite,
  putNoticeUpdate,
} from "@/apis/apiNotice";
import CustomButton from "@/component/atoms/button/CustomButton";
import useInput from "@/hook/useInput";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

interface NoticeEditContentProps {
  noticeSeq: number;
}

const NoticeEditContent = ({ noticeSeq }: NoticeEditContentProps) => {
  const navigate = useNavigate();
  const [title, onChangeTitle, setTitle] = useInput("");
  const [content, onChangeContent, setContent] = useInput("");

  useEffect(() => {
    const initComponent = async () => {
      try {
        const noticeDetailParams: GetNoticeDetailParams = {
          noticeSeq: noticeSeq,
        };
        const response = await getNoticeDetail(noticeDetailParams);
        if (response && response.data.success) {
          setTitle(response.data.response.response.title);
          setContent(response.data.response.response.content);
        } else {
          alert(response.data.error.message);
        }
      } catch (err) {
        console.log(err);
      }
    };
    initComponent();
  }, []);

  const submit = async () => {
    try {
      const noticeUpdateParams: PutNoticeUpdateParams = {
        noticeSeq: noticeSeq,
        title: title,
        content: content,
      };
      const response = await putNoticeUpdate(noticeUpdateParams);

      if (response.data.success) {
        navigate(`/notice/views/${noticeSeq}`);
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <NoticeWriteContentStyle>
      <TitleContainer>
        <Title placeholder="제목을 입력해주세요." value={title} onChange={onChangeTitle} />
      </TitleContainer>
      <Content value={content} onChange={onChangeContent} />
      <Controller>
        <CustomButton style="default" color="black" label="취소" size="large" onClick={() => navigate(-1)} />
        <CustomButton style="default" color="primary" label="수정" size="large" onClick={submit} />
      </Controller>
    </NoticeWriteContentStyle>
  );
};

const NoticeWriteContentStyle = styled.div`
  display: flex;
  flex-direction: column;
  border-top: 2px solid;
  border-color: var(--black-default);
`;

const TitleContainer = styled.div`
  height: 80px;
  width: 100%;
  padding: 0px 20px 0px 20px;
  box-sizing: border-box;
  border-bottom: 1px solid;
  border-color: var(--gray-dark);
  background-color: var(--gray-light);
  display: flex;
  align-items: center;
`;

const Title = styled.input`
  height: 40px;
  width: 100%;
  border: 1px solid;
  border-color: var(--gray-dark);
  padding: 0px 10px 0px 10px;
  outline: none;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
`;

const Content = styled.textarea`
  height: 500px;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid;
  border-color: var(--gray-dark);
  outline: none;
  overflow: auto;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 300;
  resize: none;
`;

const Controller = styled.div`
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }
`;

export default NoticeEditContent;
