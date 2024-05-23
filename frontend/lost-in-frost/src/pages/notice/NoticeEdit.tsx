import NoticeEditContent from "@/component/molecules/notice/edit/NoticeEditContent";
import { useParams } from "react-router-dom";
import styled from "styled-components";

const NoticeEdit = () => {
  const { id } = useParams();

  return <NoticeEditStyle>{id && <NoticeEditContent noticeSeq={Number(id)} />}</NoticeEditStyle>;
};

const NoticeEditStyle = styled.div``;

export default NoticeEdit;
