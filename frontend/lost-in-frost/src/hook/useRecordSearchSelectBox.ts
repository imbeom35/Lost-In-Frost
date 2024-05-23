import { GetUserSearchNickname, GetUserSearchNicknameParams } from "@/apis/apiUser";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface item {
  memberId: string;
  memberImage: string;
  memberLevel: number;
  memberNickname: string;
}

const useRecordSearchSelectBox = (): [
  string,
  string,
  boolean,
  item[],
  () => void,
  (event: React.ChangeEvent<HTMLInputElement>) => void,
  (event: React.KeyboardEvent<HTMLInputElement>) => void
] => {
  const [userId, setUserId] = useState<string>("");
  const [value, setValue] = useState<string>("");
  const [isActive, setIsActive] = useState(false);
  const [itemList, setItemList] = useState<item[]>([]);
  const [isFocus, setIsFocus] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const onClickHandler = (event: { target: any }) => {
      const target = event.target as HTMLElement;
      if (target.id === "input") {
        setIsActive(true);
        return;
      }
      if (target.id === "search") {
        search();
        return;
      }
      console.log(itemList.length);
      itemList.map((item, index) => {
        console.log(target.id);
        if (target.id === item.memberId) {
          console.log("item", item);
          navigate(`/record/list/${item.memberNickname}`);
          return;
        }
      });
      setIsActive(false);
    };

    document.addEventListener("mousedown", onClickHandler);
    return () => document.removeEventListener("mousedown", onClickHandler);
  }, [itemList]);

  const search = () => {
    const findObject = itemList.find((item) => item.memberNickname === value);
    if (findObject) {
      navigate(`/record/list/${findObject.memberNickname}`);
    }
  };

  const onChangeHandler = async (event: React.ChangeEvent<HTMLInputElement>) => {
    if (!isActive) {
      setIsActive(!isActive);
    }

    setValue(event.target.value);

    try {
      if (event.target.value.length > 0) {
        const getUserSearchNicknameParams: GetUserSearchNicknameParams = {
          nickname: event.target.value,
        };
        const response = await GetUserSearchNickname(getUserSearchNicknameParams);
        console.log(response);
        if (response.data.success) {
          setItemList(response.data.response);
        } else {
          alert(response.data.error.message);
        }
      } else {
        setItemList([]);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const keboardHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      search();
    }
  };

  return [userId, value, isActive, itemList, search, onChangeHandler, keboardHandler];
};

export default useRecordSearchSelectBox;
