import { styled } from "styled-components";
import Pagination from "../../common/Pagination";
import usePagination from "@/hook/usePagination";
import PaymentTossItem from "./PaymentContentListItem";
import { useEffect } from "react";
import { searchTypeId } from "@/type/payment";
import PaymentContentListHeader from "./PaymentContentListHeader";

interface PaymentContentListProps {
  currentType: searchTypeId;
}

const PaymentContentList = ({ currentType }: PaymentContentListProps) => {
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
  ] = usePagination("toss", 10, 20);

  console.log("#PaymentContentList - paymentList: ", itemList);

  useEffect(() => {
    setType(currentType);
  }, [currentType]);

  return (
    <PaymentListStyle>
      <TableWrapper>
        <PaymentContentListHeader type={currentType}></PaymentContentListHeader>
        {itemList.map((item, index) => (
          <PaymentTossItem key={index} type={currentType} item={item} />
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
    </PaymentListStyle>
  );
};

const PaymentListStyle = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  > * {
    margin-top: 15px;
  }
`;

const TableWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

export default PaymentContentList;
