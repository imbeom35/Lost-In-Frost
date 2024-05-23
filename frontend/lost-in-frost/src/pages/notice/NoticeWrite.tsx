import NoticeWriteContent from "@/component/molecules/notice/write/NoticeWriteContent";
import styled from "styled-components";

const NoticeWrite = () => {
  return (
    <NoticeWriteStyle>
      <NoticeWriteContent />
    </NoticeWriteStyle>
  );
};

const NoticeWriteStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

export default NoticeWrite;
