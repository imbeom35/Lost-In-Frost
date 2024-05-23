import { styled } from "styled-components";

interface MypageTabProps {
  text: string;
  isActive: boolean;
  id: string;
  onClickHandler: (event: React.MouseEvent<HTMLElement>) => void;
}

const MypageTab = ({ text, isActive, id, onClickHandler }: MypageTabProps) => {
  return (
    <MypageTabStyle id={id} className={`state--${isActive}`} onClick={onClickHandler}>
      {text}
    </MypageTabStyle>
  );
};

const MypageTabStyle = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-default);
  cursor: pointer;

  &.state--true {
    background-color: var(--white-default);
    border-top: 2px solid;
    border-right: 2px solid;
    border-left: 2px solid;
    border-color: var(--black-default);
  }

  &.state--false {
    background-color: var(--gray-default);
    border-bottom: 2px solid;
    border-color: var(--black-default);
  }
`;

export default MypageTab;
