import styled from "styled-components";
import Pagination from "../common/Pagination";
import usePagination from "@/hook/usePagination";
import RankingContentListItem from "./RankingContentListItem";

const RankingContentList = () => {
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
  ] = usePagination("ranking", 10, 20);

  return (
    <RankingContentListStyle>
      <TableWrapper>
        <Text>* 매 시간 정각에 최신 정보로 갱신됩니다.</Text>
        <TableHeader>
          <TableHeaderAttribute $width={200}>순위</TableHeaderAttribute>
          <TableHeaderAttribute>플레이어</TableHeaderAttribute>
          <TableHeaderAttribute $width={200}>레벨</TableHeaderAttribute>
          <TableHeaderAttribute $width={200}>경험치</TableHeaderAttribute>
          <TableHeaderAttribute $width={200}>게임수</TableHeaderAttribute>
        </TableHeader>
        {itemList.map((item, index) => (
          <RankingContentListItem key={index} item={item} />
        ))}
      </TableWrapper>
      <Pagination
        pageGroup={pageGroup}
        currentPage={currentPage}
        hasPrevPage={hasPrevPage}
        hasNextPage={hasNextPage}
        hasPrevPageGroup={hasPrevPageGroup}
        hasNextPageGroup={hasNextPageGroup}
        onClickHandler={onClickHandler}
      ></Pagination>
    </RankingContentListStyle>
  );
};

const RankingContentListStyle = styled.div`
  display: flex;
  flex-direction: column;

  > *:nth-child(n + 2) {
    margin-top: 40px;
  }
`;

const TableWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const TableHeader = styled.div`
  display: flex;
  flex-direction: row;
  background-color: var(--black-default);
`;

const TableHeaderAttribute = styled.div<{ $width?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : "100%")};
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--white-default);
`;

const Text = styled.div`
  height: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
  color: var(--black-light);
`;

export default RankingContentList;
