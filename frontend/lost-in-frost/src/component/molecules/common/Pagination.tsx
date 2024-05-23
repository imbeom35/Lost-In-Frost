import styled from "styled-components";
import { ReactComponent as ArrowLeft } from "@/asset/icon/Arrow-Left.svg";
import { ReactComponent as ArrowLeftDouble } from "@/asset/icon/Arrow-Left-Double.svg";
import { ReactComponent as ArrowRight } from "@/asset/icon/Arrow-Right.svg";
import { ReactComponent as ArrowRightDouble } from "@/asset/icon/Arrow-Right-Double.svg";

interface PageinationProps {
  pageGroup: number[];
  currentPage: number;
  hasPrevPage: boolean;
  hasNextPage: boolean;
  hasPrevPageGroup: boolean;
  hasNextPageGroup: boolean;
  onClickHandler: (event: React.MouseEvent<HTMLElement | SVGSVGElement>) => void;
}

const Pagination = ({
  pageGroup,
  currentPage,
  hasPrevPage,
  hasNextPage,
  hasPrevPageGroup,
  hasNextPageGroup,
  onClickHandler,
}: PageinationProps) => {
  return (
    <PaginationStyle>
      <Icon id="LeftDouble" onClick={hasPrevPageGroup ? onClickHandler : () => {}} $active={hasPrevPageGroup}>
        <ArrowLeftDouble></ArrowLeftDouble>
      </Icon>
      <Icon id="Left" onClick={hasPrevPage ? onClickHandler : () => {}} $active={hasPrevPage}>
        <ArrowLeft></ArrowLeft>
      </Icon>
      <NumberWrapper>
        {pageGroup?.map((page) =>
          page === currentPage ? (
            <CurrentNumber key={page} id={`${page}`}>
              {page}
            </CurrentNumber>
          ) : (
            <Number key={page} id={`${page}`} onClick={onClickHandler}>
              {page}
            </Number>
          )
        )}
      </NumberWrapper>
      <Icon id="Right" onClick={hasNextPage ? onClickHandler : () => {}} $active={hasNextPage}>
        <ArrowRight></ArrowRight>
      </Icon>
      <Icon id="RightDouble" onClick={hasNextPageGroup ? onClickHandler : () => {}} $active={hasNextPageGroup}>
        <ArrowRightDouble></ArrowRightDouble>
      </Icon>
    </PaginationStyle>
  );
};

const PaginationStyle = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding-left: 10%;
  padding-right: 10%;
`;

const NumberWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const Number = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
  width: 40px;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;
  color: var(--black-default);
  cursor: pointer;

  &:hover {
    background-color: var(--black-default);
    color: var(--white-default);
  }
`;

const CurrentNumber = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
  width: 40px;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;
  color: var(--black-default);
  background-color: var(--black-default);
  color: var(--white-default);
`;

const Icon = styled.div<{ $active: boolean }>`
  height: 40px;
  width: 40px;
  ${(props) => (props.$active ? "cursor: pointer;" : "")}
  fill: var(--gray-dark);

  &:hover {
    ${(props) => (props.$active ? "fill: var(--black-default);" : "")}
  }

  > * {
    height: 40px;
    width: 40px;
    pointer-events: none;
  }
`;

const StyledArrowLeftDouble = styled(ArrowLeftDouble)<{ $active: boolean }>`
  height: 40px;
  width: 40px;
  ${(props) => (props.$active ? "cursor: pointer;" : "")}
  fill: var(--gray-dark);

  &:hover {
    ${(props) => (props.$active ? "fill: var(--black-default);" : "")}
  }
`;

const StyledArrowLeft = styled(ArrowLeft)<{ $active: boolean }>`
  height: 40px;
  width: 40px;
  fill: var(--gray-dark);
  ${(props) => (props.$active ? "cursor: pointer;" : "")}

  &:hover {
    ${(props) => (props.$active ? "fill: var(--black-default);" : "")}
  }
`;

const StyledArrowRight = styled(ArrowRight)<{ $active: boolean }>`
  height: 40px;
  width: 40px;
  fill: var(--gray-dark);
  ${(props) => (props.$active ? "cursor: pointer;" : "")}

  &:hover {
    ${(props) => (props.$active ? "fill: var(--black-default);" : "")}
  }
`;

const StyledArrowRightDouble = styled(ArrowRightDouble)<{ $active: boolean }>`
  height: 40px;
  width: 40px;
  fill: var(--gray-dark);
  ${(props) => (props.$active ? "cursor: pointer;" : "")}

  &:hover {
    ${(props) => (props.$active ? "fill: var(--black-default);" : "")}
  }
`;

export default Pagination;
