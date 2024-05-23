import {
  GetGameRecordListDetailParams,
  GetGameRecordListParams,
  getGameRecordList,
  getGameRecordListDetail,
} from "@/apis/apiRecord";
import { useEffect, useRef, useState } from "react";
import { useInView } from "react-intersection-observer";

interface RecordDetail {
  seq: number;
  roomId: string;
  memberId: string;
  memberNickname: string;
  memberImage: string;
  finishTime: number;
  score: number;
  startAt: string;
  clear: boolean;
}

interface RecordList {
  clear: boolean;
  finishTime: number;
  memberId: string;
  memberImage: string;
  memberNickname: string;
  roomId: string;
  score: number;
  seq: string;
  startAt: string;
  detail: RecordDetail[];
}

const RecordList = (
  nickname: string,
  size: number
): [RecordList[], boolean[], (node?: Element | null | undefined) => void, (roomId: string) => void] => {
  const [itemList, setItemList] = useState<RecordList[]>([]);
  const [isOpenList, setIsOpenList] = useState<boolean[]>([]);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [ref, inView] = useInView({ triggerOnce: true });

  // useEffect(() => {
  //   const initComponent = async () => {
  //     const recordList = await getRecordList(nickname);

  //     if (recordList.length > 0) {
  //       for (const item of recordList) {
  //         const recordDetail = await getRecordDetail(item.roomId);
  //         if (recordDetail.length > 0) {
  //           item["detail"] = recordDetail;
  //         }
  //       }
  //       // 초기화 생성
  //       setIsOpenList(Array(recordList.length).fill(false));
  //     }
  //     setItemList(recordList);
  //   };
  //   initComponent();
  // }, [nickname]);

  // 무한 스크롤
  useEffect(() => {
    console.log("!!!!sds");
    const scroll = async () => {
      const recordList = await getRecordList(nickname);
      console.log("recordList", recordList);

      if (recordList.length > 0) {
        for (const item of recordList) {
          const recordDetail = await getRecordDetail(item.roomId);
          if (recordDetail.length > 0) {
            item["detail"] = recordDetail;
          }
        }

        // 초기화 생성
        setIsOpenList((prev) => [...prev, ...Array(recordList.length).fill(false)]);
      }
      setItemList((prev) => [...prev, ...recordList]);
    };
    scroll();
  }, [inView]);

  const openHandler = (roomId: string) => {
    const modifyIsOpenList = [...isOpenList];

    for (let i = 0; i < isOpenList.length; i++) {
      console.log(itemList[i].roomId);
      if (itemList[i].roomId === roomId) {
        modifyIsOpenList[i] = !isOpenList[i];
        break;
      }
    }
    setIsOpenList(modifyIsOpenList);
  };

  const getRecordList = async (nickname: string) => {
    try {
      const gameRecordListParams: GetGameRecordListParams = {
        nickname: nickname,
        page: currentPage,
        size: size,
      };
      const response = await getGameRecordList(gameRecordListParams);
      if (response && response.data.success) {
        setCurrentPage(currentPage + 1);
        return response.data.response.content;
      } else {
        //alert(response.data.error.message);
        return [];
      }
    } catch (err) {
      console.log(err);
      return [];
    }
  };

  const getRecordDetail = async (roomId: string) => {
    try {
      const gameRecordListDetailParams: GetGameRecordListDetailParams = {
        roomId: roomId,
      };
      const response = await getGameRecordListDetail(gameRecordListDetailParams);
      if (response && response.data.success) {
        return response.data.response;
      } else {
        //alert(response.data.error.message);
        return [];
      }
    } catch (err) {
      console.log(err);
      return [];
    }
  };

  return [itemList, isOpenList, ref, openHandler];
};

export default RecordList;
