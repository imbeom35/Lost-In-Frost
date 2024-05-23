import { styled } from "styled-components";
import usePagination from "@/hook/usePagination";
import Pagination from "../../common/Pagination";
import CostumeCard from "../../common/CostumeCard";

const ShopCostumeList = () => {
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
  ] = usePagination("shop-costume", 10, 20);

  return (
    <CostumeListStyle>
      <Grid>
        {itemList.map((item, index) => (
          <CostumeCard
            key={index}
            type="SHOP"
            costumeSeq={item.costumeSeq}
            grade={item.costumeGrade}
            name={item.costumeName}
            image={item.costumeImage}
            isHave={item.have}
            coinPrice={item.costumeCoinPrice}
            crystalPrice={item.costumeCrystalPrice}
          />
        ))}
      </Grid>
      <Pagination
        pageGroup={pageGroup}
        currentPage={currentPage}
        hasPrevPage={hasPrevPage}
        hasNextPage={hasNextPage}
        hasPrevPageGroup={hasPrevPageGroup}
        hasNextPageGroup={hasNextPageGroup}
        onClickHandler={onClickHandler}
      ></Pagination>
    </CostumeListStyle>
  );
};

const CostumeListStyle = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  > *:nth-child(n + 2) {
    margin-top: 50px;
  }
`;

const Grid = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: auto;
  gap: 50px;
`;

export default ShopCostumeList;
