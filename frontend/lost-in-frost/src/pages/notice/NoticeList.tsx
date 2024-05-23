import NoticeListContentList from "@/component/molecules/notice/list/NoticeListContentList";
import styled from "styled-components";

const NoticeList = () => {
  return (
    <NoticeListStyle>
      <NoticeListContentList />
    </NoticeListStyle>
  );
};

const NoticeListStyle = styled.div``;

export default NoticeList;
