import { searchTypeId, searchTypeName } from "@/type/payment";
import React, { useEffect, useRef, useState } from "react";

interface item {
  id: searchTypeId;
  name: searchTypeName;
}

const useSelectBox = (): [
  boolean,
  item[],
  searchTypeId,
  React.Dispatch<React.SetStateAction<item[]>>,
  (event: React.MouseEvent<HTMLElement>) => void
] => {
  const [isActive, setIsActive] = useState(false);
  const [itemList, setItemList] = useState<item[]>([]);
  const [currentItem, setCurrentItem] = useState<searchTypeId>("toss");

  useEffect(() => {
    if (itemList.length > 0) {
      setCurrentItem(itemList[0].id);
    }
  }, [itemList]);

  useEffect(() => {
    const onClickHandler = (event: { target: any }) => {
      if (isActive && event.target.id !== "open" && !itemList.some((item) => item.id === event.target.id)) {
        setIsActive(!isActive);
      }
    };

    document.addEventListener("mousedown", onClickHandler);
    return () => document.removeEventListener("mousedown", onClickHandler);
  }, [isActive]);

  const onClickHandler = (event: React.MouseEvent<HTMLElement>) => {
    const target = event.target as HTMLElement;
    if (target.id === "open") {
      setIsActive(!isActive);
      console.log("is :   " + isActive);
      return;
    } else {
      itemList.map((item, index) => {
        if (target.id === item.id) {
          setCurrentItem(item.id);
          setIsActive(!setIsActive);
          return;
        }
      });
    }
  };

  return [isActive, itemList, currentItem, setItemList, onClickHandler];
};

export default useSelectBox;
