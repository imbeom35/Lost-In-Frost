import { grade } from "@/type/costume";
import { ReactNode } from "react";
import styled from "styled-components";

interface GradeBadgeProps {
  grade: grade;
  children?: ReactNode;
}

const GradeBadge = ({ grade, children }: GradeBadgeProps) => {
  return <BadgeStyle className={`grade--${grade}`}>{children}</BadgeStyle>;
};

const BadgeStyle = styled.div`
  height: 20px;
  width: fit-content;
  padding: 0px 12px 0px 12px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50px;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;

  &.grade--normal {
    background-color: var(--grade-normal);
    /* color: var(--black-default); */
    color: var(--white-default);
  }

  &.grade--epic {
    background-color: var(--grade-epic);
    /* color: var(--black-default); */
    color: var(--white-default);
  }

  &.grade--unique {
    background-color: var(--grade-unique);
    color: var(--black-default);
    /* color: var(--white-default); */
  }

  &.grade--legendary {
    background-color: var(--grade-legendary);
    color: var(--black-default);
    /* color: var(--white-default); */
  }

  &.grade--none {
    background-color: var(--gray-light);
    color: var(--black-default);
    /* color: var(--white-default); */
  }
`;

export default GradeBadge;
