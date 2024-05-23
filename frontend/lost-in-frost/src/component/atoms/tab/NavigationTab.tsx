import styled from "styled-components";
import "@/common/Color.css";
import "@/common/Font.css";

interface NavigationTabProps {
  label: string;
  id: string;
  active: boolean;
  width?: number | null;
  height?: number | null;
  onClick?: (event: React.MouseEvent<HTMLElement>) => void;
}

const NavigationTab = ({ label, id, active, width, height, onClick }: NavigationTabProps) => {
  return (
    <NavigationTabStyle id={id} width={width} height={height} className={`active--${active}`} onClick={onClick}>
      <Text>{label}</Text>
    </NavigationTabStyle>
  );
};

const NavigationTabStyle = styled.div<{ width?: number | null; height?: number | null }>`
  display: flex;
  align-items: center;
  justify-content: center;
  ${(props) => props.width && `width: ${props.width}px;`}
  ${(props) => props.height && `height: ${props.height}px;`}
  cursor: pointer;

  > * {
    pointer-events: none;
  }

  &.active--true {
    border-bottom: 5px solid;
    padding-top: 5px;
    border-color: var(--primary-default);
  }
`;

const Text = styled.div`
  font-family: Pretendard;
  font-size: 18px;
  font-weight: 500;
  color: var(--white-default);
`;

export default NavigationTab;
