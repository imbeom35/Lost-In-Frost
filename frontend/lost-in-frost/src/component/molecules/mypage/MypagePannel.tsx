import { ReactNode } from "react";
import { styled } from "styled-components";
import MypageTab from "../../atoms/tab/MypageTab";
import { MypageTabItem } from "@/type/tab";

interface MypagePannelProps {
  tabList: MypageTabItem[];
  currentTab: string;
  onClickHandler: (event: React.MouseEvent<HTMLElement>) => void;
  children?: ReactNode;
}

const MypagePannel = ({ tabList, currentTab, children, onClickHandler }: MypagePannelProps) => {
  return (
    <MypageTabPannelStyle>
      <Controller>
        {tabList.map((tab, index) => (
          <MypageTab
            key={index}
            id={tab.name}
            isActive={tab.name === currentTab}
            text={tab.content}
            onClickHandler={onClickHandler}
          ></MypageTab>
        ))}
      </Controller>
      <Content>{children}</Content>
    </MypageTabPannelStyle>
  );
};

const MypageTabPannelStyle = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 50px;
`;

const Controller = styled.div`
  display: flex;
  flex-direction: row;
`;
const Content = styled.div`
  display: flex;
  flex-direction: column;
  border-right: 2px solid;
  border-bottom: 2px solid;
  border-left: 2px solid;
  border-color: var(--black-default);
  padding: 30px;
`;

export default MypagePannel;
