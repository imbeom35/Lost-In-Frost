import { MypageTabItem } from "@/type/tab";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const MypageDefaultTab: MypageTabItem[] = [
  { name: "info", content: "내정보", link: "/mypage/info" },
  { name: "costume", content: "보유 코스튬", link: "/mypage/costume" },
  { name: "payment", content: "결제", link: "/mypage/payment" },
];

const useMypagePannel = (): [MypageTabItem[], string, (event: React.MouseEvent<HTMLElement>) => void] => {
  const navigate = useNavigate();
  const [tabList, setTabList] = useState<MypageTabItem[]>(MypageDefaultTab);
  const [currentTab, setCurrentTab] = useState<string>("");

  useEffect(() => {
    setCurrentTab(tabList[0].name);
    navigate(tabList[0].link);
  }, []);

  const onclickHandler = (event: React.MouseEvent<HTMLElement>) => {
    if (event) {
      const id = (event.target as Element).id;
      tabList.map((tab, index) => {
        if (tab.name === id) {
          setCurrentTab(tab.name);
          navigate(tab.link);
          return;
        }
      });
    }
  };

  return [tabList, currentTab, onclickHandler];
};

export default useMypagePannel;
