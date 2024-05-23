import { styled } from "styled-components";

interface PageTitleProps {
  text: string;
}

const PageTitle = ({ text }: PageTitleProps) => {
  return <PageTitleStyle>{text}</PageTitleStyle>;
};

const PageTitleStyle = styled.div`
  display: flex;
  align-items: center;
  justify-content: left;
  width: 100%;
  margin-bottom: 50px;
  padding-bottom: 10px;
  border-bottom: 3px solid;
  border-color: var(--black-default);
  font-family: Pretendard;
  /* font-family: Bukeukeu Myeongjo; */
  font-size: 40px;
  font-weight: 700;
  color: var(--black-default);
`;

export default PageTitle;
