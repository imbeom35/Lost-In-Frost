import {
  DeleteNoticeCommentDeleteParams,
  GetNoticeCommentListParams,
  deleteNoticeCommentDelete,
  getNoticeCommentList,
} from "@/apis/apiNotice";
import React, { useEffect, useState } from "react";

const useScroll = (
  seq: number,
  size: number
): [
  any[],
  number,
  boolean,
  (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => void,
  (commentSeq: number) => void,
  () => void
] => {
  const [noticeSeq, setNoticeSeq] = useState<number>(seq);
  const [itemList, setItemList] = useState<any[]>([]);
  const [commentCnt, setCommentCnt] = useState(0);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [isEnd, setIsEnd] = useState(true);

  const addNewItem = (newItem: any[]) => {
    for (let i = 0; i < newItem.length; i++) {
      setItemList((prevArray) => [...prevArray, newItem[i]]);
    }
  };

  const initComponent = async () => {
    try {
      let response = null;
      const noticeCommentParams: GetNoticeCommentListParams = {
        noticeSeq: noticeSeq,
        page: 0,
        size: size,
      };
      response = await getNoticeCommentList(noticeCommentParams);

      console.log("scroll data: ", response);

      if (response.data.success) {
        setCurrentPage(1);
        setItemList(response.data.response.content);
        setCommentCnt(response.data.response.totalElements);

        if (response.data.response.totalPages > currentPage + 1) {
          setIsEnd(false);
        }
      }
    } catch (err) {
      console.log(err);
    }
  };

  const reset = () => {
    console.log("reset");
    setItemList([]);
    initComponent();
  };

  useEffect(() => {
    initComponent();
  }, [seq, size]);

  const onClickHandler = async (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => {
    try {
      let response = null;
      const noticeCommentParams: GetNoticeCommentListParams = {
        noticeSeq: noticeSeq,
        page: currentPage,
        size: size,
      };
      response = await getNoticeCommentList(noticeCommentParams);

      if (response.data.success) {
        setCurrentPage(currentPage + 1);
        addNewItem(response.data.response.content);

        if (response.data.response.totalPages <= currentPage + 1) {
          setIsEnd(true);
        }
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const deleteItem = async (commentSeq: number) => {
    try {
      const noticeCommentDeleteParams: DeleteNoticeCommentDeleteParams = {
        noticeCommentSeq: commentSeq,
      };
      const response = await deleteNoticeCommentDelete(noticeCommentDeleteParams);
      if (response.data.success) {
        console.log(itemList);
        const findObject = itemList.find((obj) => obj.commentSeq === commentSeq);
        console.log(findObject);
        setItemList(itemList.filter((obj) => obj.commentSeq !== findObject.commentSeq));
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  return [itemList, commentCnt, isEnd, onClickHandler, deleteItem, reset];
};

export default useScroll;
