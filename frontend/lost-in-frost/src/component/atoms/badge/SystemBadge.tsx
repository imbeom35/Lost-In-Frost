import { state } from "@/type/costume";
import { ReactNode } from "react";
import styled from "styled-components";

interface SystemBadgeProps {
  state: state;
  children?: ReactNode;
}

const SystemBadge = ({ state, children }: SystemBadgeProps) => {
  return <BadgeStyle className={`state--${state}`}>{children}</BadgeStyle>;
};

const BadgeStyle = styled.div`
  height: 20px;
  width: fit-content;
  padding: 0px 12px 0px 12px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;

  &.state--red {
    background-color: var(--system-red);
    color: var(--white-default);
  }

  &.state--green {
    background-color: var(--system-green);
    color: var(--white-default);
  }

  &.state--yellow {
    background-color: var(--system-yellow);
    color: var(--white-default);
  }
`;

export default SystemBadge;
