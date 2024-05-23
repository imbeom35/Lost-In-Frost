import { ShopTabItem } from "@/type/tab";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ShopDefaultTab: ShopTabItem[] = [{ name: "costume", content: "코스튬", link: "/shop/costume", image: "costume" }];

const useShopPannel = (): [ShopTabItem[], string, (event: React.MouseEvent<HTMLElement>) => void] => {
  const navigate = useNavigate();
  const [tabList, setTabList] = useState<ShopTabItem[]>(ShopDefaultTab);
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

export default useShopPannel;
