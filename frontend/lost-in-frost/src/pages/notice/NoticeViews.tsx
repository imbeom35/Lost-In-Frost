import NoticeViewContent from "@/component/molecules/notice/view/NoticeViewContent";
import { useEffect } from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";

const NoticeViews = () => {
  const { id } = useParams();

  return <NoticeViewsStyle>{id && <NoticeViewContent noticeSeq={Number(id)} />}</NoticeViewsStyle>;
};

const NoticeViewsStyle = styled.div``;

export default NoticeViews;
