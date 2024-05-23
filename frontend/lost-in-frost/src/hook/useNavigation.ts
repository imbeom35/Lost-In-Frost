<<<<<<< HEAD
=======
import { getUserMyPageInfo } from "@/apis/apiUser";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import navigationStore from "@/store/navigationStore";

interface TabItem {
  name: string;
  content: string;
  link: string;
}

const defaultTab: TabItem[] = [
  { name: "Home", content: "홈", link: "/" },
  { name: "Notice", content: "공지사항", link: "/notice/list" },
  { name: "Ranking", content: "랭킹", link: "/ranking" },
  { name: "Record", content: "전적", link: "/record/search" },
  { name: "Shop", content: "상점", link: "/shop/costume" },
];

const userTab: TabItem[] = [
  { name: "Home", content: "홈", link: "/" },
  { name: "Notice", content: "공지사항", link: "/notice/list" },
  { name: "Ranking", content: "랭킹", link: "/ranking" },
  { name: "Record", content: "전적", link: "/record/search" },
  { name: "Shop", content: "상점", link: "/shop/costume" },
  { name: "Mypage", content: "마이페이지", link: "/mypage/info" },
];

<<<<<<< HEAD
const useNavigation = (): [
  any[],
  (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => void
] => {
=======
const useNavigation = (): [any[], (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => void] => {
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  const { currentTab, setCurrentTab } = navigationStore();

  const [tabList, setTabList] = useState<TabItem[]>(defaultTab);
  const navigate = useNavigate();

  useEffect(() => {
<<<<<<< HEAD
    if (localStorage.getItem("token")) {
=======
    if (sessionStorage.getItem("token")) {
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      setTabList(userTab);
    } else {
      setTabList(defaultTab);
    }
<<<<<<< HEAD
  }, [localStorage.getItem("token")]);
=======
  }, [sessionStorage.getItem("token")]);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

  const onClickHandler = (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => {
    if (event) {
      const id = (event.target as Element).id;
<<<<<<< HEAD
      console.log("id", id);
=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      tabList.map((tab, index) => {
        if (tab.name === id) {
          setCurrentTab(tab.name);
          navigate(tab.link);
          return;
        }
      });
    }
  };

  return [tabList, onClickHandler];
};

export default useNavigation;
