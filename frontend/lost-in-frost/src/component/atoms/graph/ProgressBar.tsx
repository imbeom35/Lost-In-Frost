import { parseNumberToPercentage } from "@/utils/formatter";
import styled, { keyframes } from "styled-components";

interface ProgressBarprops {
  textName: string;
  textState: string;
  totalValue: number;
  currentValue: number;
}

const ProgressBar = ({ textName, textState, totalValue, currentValue }: ProgressBarprops) => {
  const percentage = currentValue / totalValue;
  const width = 800;

  return (
    <ProgressBarStyle>
      <TextWrapper $width={800}>
        <Title>{`${textName} ${totalValue}개 중 ${currentValue}개 ${textState}`}</Title>
        <Percentage>{parseNumberToPercentage(percentage)}</Percentage>
      </TextWrapper>
      <BarBox $width={width}>
        <BarBoxFill $width={width * percentage}></BarBoxFill>
      </BarBox>
    </ProgressBarStyle>
  );
};

const ProgressBarStyle = styled.div`
  display: flex;
  flex-direction: column;
`;

const TextWrapper = styled.div<{ $width: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : null)};
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
`;

const Title = styled.div`
  display: flex;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
  color: var(--black-default);
`;

const Percentage = styled.div`
  display: flex;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
  color: var(--gray-dark);
`;

const BarBox = styled.div<{ $width: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : null)};
  height: 40px;
  border-radius: 10px;
  overflow: hidden;
  background-color: var(--gray-default);
  display: flex;
`;

const changeWidth = keyframes<{ $width: number }>`
  from {
    width: 0px;
  }
  to {
    width: ${(props) => (props.$width ? `${props.$width}px` : "0px")};
  }
`;

const BarBoxFill = styled.div<{ $width: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : null)};
  background-color: var(--primary-default);
  display: flex;
  animation: ${changeWidth} 1s ease;
  /* background-image: linear-gradient(to right, red, orange, yellow, green, blue, indigo, purple); */
`;

export default ProgressBar;
