import { getUserMyPageInfo } from "@/apis/apiUser";
import { BASE_URL, END_POINTS } from "@/constant/api";
import axios from "axios";
import React, { useEffect, useState } from "react";

const useStateMessage = (): [
  string,
  boolean,
  (event: React.ChangeEvent<HTMLTextAreaElement>) => void,
  (event: React.FocusEvent<HTMLTextAreaElement>) => void
] => {
  const [stateMessage, setStateMessage] = useState<string>("");
  const [isChange, setIsChange] = useState<boolean>(false);

  useEffect(() => {
    const initComponent = async () => {
      try {
        const response = await getUserMyPageInfo();
        if (response.data.success) {
          setStateMessage(response.data.response.message);
        }
      } catch (err) {
        console.log(err);
      }
    };

    initComponent();
  }, []);

  const onChangeHandler = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const target = event.target as HTMLTextAreaElement;

    if (target) {
      setStateMessage(target.value);
      console.log(stateMessage);
    }

    if (!isChange) {
      setIsChange(!isChange);
    }
  };

  const onBlurHandler = async (event: React.FocusEvent<HTMLTextAreaElement>) => {
    const target = event.target as HTMLTextAreaElement;

    if (target && isChange) {
      if (!stateMessage) {
        setIsChange(!isChange);
        return;
      }

      const response = await axios.put(BASE_URL + END_POINTS.USER_MYPAGE_MESSAGE, stateMessage, {
        headers: {
          "Content-Type": "text/plain",
<<<<<<< HEAD
          Authorization: `Bearer ${localStorage.getItem("token")}`,
=======
          Authorization: `Bearer ${sessionStorage.getItem("token")}`,
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
        },
      });

      if (response.data.success) {
        setIsChange(!isChange);
      }
    }
  };

  return [stateMessage, isChange, onChangeHandler, onBlurHandler];
};

export default useStateMessage;
