import styled from "styled-components";
import Pagination from "../../common/Pagination";
import usePagination from "@/hook/usePagination";
import CustomButton from "@/component/atoms/button/CustomButton";
import { useNavigate } from "react-router-dom";
import NoticeListContentListItem from "./NoticeListContentListItem";
import { isAdmin } from "@/utils/formatter";

const NoticeListContentList = () => {
  const navigate = useNavigate();
  const [
    type,
    setType,
    itemList,
    pageGroup,
    currentPage,
    hasPrevPage,
    hasNextPage,
    hasPrevPageGroup,
    hasNextPageGroup,
    onClickHandler,
  ] = usePagination("notice-list", 10, 20);

  return (
    <NoticeContentListStyle>
      <TableWrapper>
        {itemList.map((item, index) => (
          <NoticeListContentListItem item={item} key={index}></NoticeListContentListItem>
        ))}
      </TableWrapper>
      {isAdmin() && (
        <Controller>
          <CustomButton
            style="default"
            color="primary"
            label="글쓰기"
            size="large"
            onClick={() => navigate("/notice/write")}
          ></CustomButton>
        </Controller>
      )}

      <Pagination
        pageGroup={pageGroup}
        currentPage={currentPage}
        hasPrevPage={hasPrevPage}
        hasNextPage={hasNextPage}
        hasPrevPageGroup={hasPrevPageGroup}
        hasNextPageGroup={hasNextPageGroup}
        onClickHandler={onClickHandler}
      ></Pagination>
    </NoticeContentListStyle>
  );
};

const NoticeContentListStyle = styled.div`
  display: flex;
  flex-direction: column;

  > *:nth-child(n + 2) {
    margin-top: 40px;
  }
`;

const TableWrapper = styled.div`
  display: flex;
  flex-direction: column;
  border-top: 1px solid;
  border-color: var(--black-default);
`;

const Controller = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;
`;

export default NoticeListContentList;
