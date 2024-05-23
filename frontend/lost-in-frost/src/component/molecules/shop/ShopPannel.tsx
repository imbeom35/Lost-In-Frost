import ShopTab from "@/component/atoms/tab/ShopTab";
import { ShopTabItem } from "@/type/tab";
import { ReactNode } from "react";
import styled from "styled-components";

interface ShopPannelProps {
  tabList: ShopTabItem[];
  currentTab: string;
  onClickHandler: (event: React.MouseEvent<HTMLElement>) => void;
  children?: ReactNode;
}

const ShopPannel = ({ tabList, currentTab, onClickHandler, children }: ShopPannelProps) => {
  return (
    <ShopPannelStyle>
      <Controller>
        {tabList.map((tab, index) => (
          <ShopTab
            key={index}
            id={tab.name}
            isActive={tab.name === currentTab}
            text={tab.content}
            image={tab.image}
            onClickHandler={onClickHandler}
          ></ShopTab>
        ))}
      </Controller>
      {children}
    </ShopPannelStyle>
  );
};

const ShopPannelStyle = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  margin-top: 50px;

  > *:nth-child(n + 2) {
    margin-left: 20px;
  }
`;

const Controller = styled.div`
  width: 200px;
  display: flex;
  flex-direction: column;
  background-color: var(--black-light);
`;

export default ShopPannel;
