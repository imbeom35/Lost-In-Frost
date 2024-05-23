import styled from "styled-components";
import { ReactComponent as ArrowDown } from "@/asset/icon/Arrow-Down.svg";
import { ReactComponent as ArrowUp } from "@/asset/icon/Arrow-Up.svg";
import React from "react";

interface item {
  id: string;
  name: string;
}

interface SelectBoxProps {
  isActive: boolean;
  itemList: item[];
  currentItem: string;
  width?: number;
  height?: number;
  onClickHandler?: (event: React.MouseEvent<HTMLElement>) => void;
}

const SelectBox = ({ isActive, itemList, currentItem, width, height, onClickHandler }: SelectBoxProps) => {
  return (
    <SelectBoxStyle>
      <CurrentItem id="open" onClick={onClickHandler}>
        <Item $width={width}>
          <Text>{itemList.find((item) => item.id === currentItem)?.name}</Text>
          <IconBox>
            {isActive ? <ArrowUp width={12} height={12}></ArrowUp> : <ArrowDown width={12} height={12}></ArrowDown>}
          </IconBox>
        </Item>
      </CurrentItem>
      {isActive && (
        <ListItemWrapper $top={height ? height + 10 : 0}>
          {isActive &&
            itemList.map((item, index) => (
              <Item key={index} $width={width} onClick={onClickHandler} id={item.id}>
                <Text>{item.name}</Text>
                <IconBox></IconBox>
              </Item>
            ))}
        </ListItemWrapper>
      )}
    </SelectBoxStyle>
  );
};

const SelectBoxStyle = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  cursor: pointer;
`;

const CurrentItem = styled.div<{ $height?: number }>`
  position: relative;
  height: ${(props) => (props.$height ? `${props.$height}px` : "auto")};
  border: 1.5px solid;
  border-radius: 5px;
  border-color: var(--gray-dark);
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 3px 0px 3px 0px;

  > * {
    pointer-events: none;
  }
`;

const ListItemWrapper = styled.div<{ $top?: number; $width?: number }>`
  position: absolute;
  width: ${(props) => (props.$width ? `${props.$width}px` : "auto")};
  right: 0px;
  top: ${(props) => (props.$top ? `${props.$top}px` : "0px")};
  display: flex;
  text-align: center;
  justify-content: center;
  flex-direction: column;
  border-radius: 5px;
  border: 1.5px solid;
  border-color: var(--gray-dark);
  box-sizing: border-box;
  overflow: hidden;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3);
`;

const Item = styled.div<{ $height?: number; $width?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : "auto")};
  height: ${(props) => (props.$height ? `${props.$height}px` : "auto")};
  display: flex;
  flex-direction: row;
  align-items: center;
  overflow: hidden;
  background-color: var(--white-default);
  padding: 0px 10px 0px 10px;

  > * {
    pointer-events: none;
  }

  &:hover {
    background-color: var(--gray-light);
  }
`;

const Text = styled.div`
  height: 100%;
  width: 100%;
  display: flex;
  margin-right: 10px;
  font-family: Pretendard;
  font-size: 12px;
  font-weight: 500;
`;

const IconBox = styled.div`
  height: 20px;
  width: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default SelectBox;
