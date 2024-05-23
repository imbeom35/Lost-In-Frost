import { GetGameCostumeListParams, getGameCostumeList } from "@/apis/apiGame";
import { GetNoticeListParams, getNoticeList } from "@/apis/apiNotice";
import { GetTossListParams, getTossList, GetPurchaseListParams, getPurchaseList } from "@/apis/apiPayment";
import { GetRankingListParams, getRankingList } from "@/apis/apiRanking";
import { GetMyCostumeListParams, getMyCostumeList } from "@/apis/apiUser";
import { searchTypeId } from "@/type/payment";
import React, { useEffect, useState } from "react";

const usePagination = (
  initType: string,
  groupCnt: number,
  size: number
): [
  searchTypeId | string,
  React.Dispatch<React.SetStateAction<searchTypeId | string>>,
  any[],
  number[],
  number,
  boolean,
  boolean,
  boolean,
  boolean,
  (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => void
] => {
  const [type, setType] = useState<searchTypeId | string>(initType);
  const [itemList, setItemList] = useState<any[]>([]);
  const [pageGroup, setPageGroup] = useState<number[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [hasPrevPage, setHasPrevPage] = useState(true);
  const [hasNextPage, setHasNextPage] = useState(true);
  const [hasPrevPageGroup, setHasPrevPageGroup] = useState(true);
  const [hasNextPageGroup, setHasNextPageGroup] = useState(true);

  const customCeil = (value: number, form: number): number => {
    const remainder = value % form;

    if (remainder === 0) {
      return value;
    }

    return value + (form - remainder);
  };

  const customFloor = (value: number, form: number): number => {
    const ceil = customCeil(value, form);

    return ceil - form + 1;
  };

  useEffect(() => {
    setCurrentPage(1);
  }, [type]);

  useEffect(() => {
    const initComponent = async () => {
      try {
        let response = null;
        switch (type) {
          case "toss":
            const tossParams: GetTossListParams = {
              page: currentPage - 1,
              size: size,
            };
            response = await getTossList(tossParams);
            break;
          case "crystal":
            const crystalParams: GetPurchaseListParams = {
              classification: "crystal",
              page: currentPage - 1,
              size: size,
            };
            response = await getPurchaseList(crystalParams);
            break;
          case "coin":
            const coinParams: GetPurchaseListParams = {
              classification: "coin",
              page: currentPage - 1,
              size: size,
            };
            response = await getPurchaseList(coinParams);
            break;
          case "my-costume":
            const myCostumeListParams: GetMyCostumeListParams = {
              page: currentPage - 1,
              size: size,
            };
            response = await getMyCostumeList(myCostumeListParams);
            break;
          case "shop-costume":
            const gameCostumeListParams: GetGameCostumeListParams = {
              page: currentPage - 1,
              size: size,
            };
            response = await getGameCostumeList(gameCostumeListParams);
            break;
          case "ranking":
            const rankingListParams: GetRankingListParams = {
              page: currentPage - 1,
              size: size,
            };
            response = await getRankingList(rankingListParams);
            break;
          case "notice-list":
            const noticeListParams: GetNoticeListParams = {
              page: currentPage - 1,
              size: size,
            };
            response = await getNoticeList(noticeListParams);
            break;
        }

        console.log("pagination data: ", response);

        if (response && response.data.success) {
          setItemList(response.data.response.content);

          const totalPage = response.data.response.totalPages;
          setHasPrevPage(currentPage > 1);
          setHasNextPage(currentPage < totalPage);
          setHasPrevPageGroup(customFloor(currentPage, groupCnt) > 1);
          setHasNextPageGroup(customCeil(currentPage, groupCnt) < customCeil(totalPage, groupCnt));

          const newPageGroup = <number[]>[];
          if (customCeil(totalPage, groupCnt) === customCeil(currentPage, groupCnt)) {
            for (let i = customFloor(currentPage, groupCnt); i <= totalPage; i++) {
              newPageGroup.push(i);
            }
            setPageGroup(newPageGroup);
          } else {
            for (let i = customFloor(currentPage, groupCnt); i <= customCeil(currentPage, groupCnt); i++) {
              newPageGroup.push(i);
            }
            setPageGroup(newPageGroup);
          }
        }
      } catch (err) {
        console.log(err);
      }
    };
    initComponent();
  }, [type, currentPage]);

  const onClickHandler = (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => {
    if (event) {
      const id = (event.target as Element).id;
      switch (id) {
        case "LeftDouble":
          console.log("click: " + id);
          setCurrentPage(customFloor(currentPage, groupCnt) - 10);
          break;
        case "Left":
          console.log("click: " + id);
          setCurrentPage(currentPage - 1);
          break;
        case "Right":
          console.log("click: " + id);
          setCurrentPage(currentPage + 1);
          break;
        case "RightDouble":
          console.log("click: " + id);
          setCurrentPage(customFloor(currentPage, groupCnt) + 10);
          break;
        default:
          if (!isNaN(Number(id))) {
            setCurrentPage(Number(id));
          }
          break;
      }
    }
  };

  return [
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
  ];
};

export default usePagination;
