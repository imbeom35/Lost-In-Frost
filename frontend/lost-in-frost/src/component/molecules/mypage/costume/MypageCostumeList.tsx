import ProgressBar from "@/component/atoms/graph/ProgressBar";
import usePagination from "@/hook/usePagination";
import { styled } from "styled-components";
import Pagination from "../../common/Pagination";
import CostumeCard from "../../common/CostumeCard";
import { GetUserMyCostumeCount } from "@/apis/apiUser";
import { useEffect, useState } from "react";
import { getGameCostumeCount } from "@/apis/apiGame";

const MypageCostumeList = () => {
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
  ] = usePagination("my-costume", 10, 20);

  const [totalValue, setTotalValue] = useState(100);
  const [currentValue, setCurrentValue] = useState(0);

  const getTotalValue = async (): Promise<number> => {
    try {
      const response = await GetUserMyCostumeCount();

      if (response.data.success) {
        setCurrentValue(response.data.response);
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }

    return 0;
  };

  console.log("Item", itemList);

  const getCurrentValue = async (): Promise<number> => {
    try {
      const response = await getGameCostumeCount();

      if (response.data.success) {
        setTotalValue(response.data.response);
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }

    return 0;
  };

  useEffect(() => {
    getTotalValue();
    getCurrentValue();
  }, []);

  return (
    <CostumeListStyle>
      <ProgressBar
        textName="코스튬"
        textState="보유중"
        totalValue={totalValue}
        currentValue={currentValue}
      ></ProgressBar>
      <Grid>
        {itemList.map((item, index) => (
          <CostumeCard
            key={index}
            type="MYPAGE"
            costumeSeq={item.myCostumeSeq}
            grade={item.costumeGrade}
            name={item.costumeName}
            image={item.costumeImage}
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

  > *:nth-child(n + 2) {
    margin-top: 30px;
  }
`;

const Grid = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: auto;
  gap: 50px;

  > *:hover {
    transition: 0.5s;
    transform: scale(1.05);
  }

  > *:not(:hover) {
    transition: 0.5s;
    transform: scale(1);
  }
`;

export default MypageCostumeList;
