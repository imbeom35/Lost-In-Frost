import { calculateBodyMinHeight } from "@/utils/calculate";
import { ReactNode } from "react";
import styled from "styled-components";

interface BodyProps {
  width: number;
  topHeight: number;
  children: ReactNode;
}

const Body = ({ width, topHeight, children }: BodyProps) => {
  const min = calculateBodyMinHeight(topHeight);

  return (
    <BodyStyle $min={min} $width={width}>
      <Content $width={width}>{children}</Content>
    </BodyStyle>
  );
};

const BodyStyle = styled.div<{ $min: number; $width: number }>`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: start;
  min-height: ${(props) => props.$min}px;
  padding-top: 100px;
  padding-bottom: 100px;

  @media (max-width: ${(props) => (props.$width ? `${props.$width}px` : null)}) {
    align-items: flex-start;
  }
`;

const Content = styled.div<{ $width: number }>`
  display: flex;
  flex-direction: column;
  width: ${(props) => (props.$width ? `${props.$width}px` : "auto")};
`;

export default Body;
