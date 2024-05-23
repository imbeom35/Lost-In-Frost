import styled from "styled-components";
import "@/common/Color.css";
import "@/common/Font.css";

interface CustomButtonProps {
  label: string;
  size: "small" | "medium" | "large";
  color: "primary" | "secondary" | "black" | "gray" | "red" | "green" | "orange";
  style: "default" | "border" | "disable";
  width?: string;
  type?: "submit";
  onClick?: () => void;
}

const CustomButton = ({ label, size, color, style, width, onClick }: CustomButtonProps) => {
  return (
    <CustomButtonStyle width={width} className={`size--${size} style--${color}-${style}`} onClick={onClick}>
      <Text className={`size--${size}`}>{label}</Text>
    </CustomButtonStyle>
  );
};

const CustomButtonStyle = styled.div<{ width?: string }>`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  ${(props) => (props.width ? `width: ${props.width};` : "")}
  cursor: pointer;
  box-sizing: border-box;

  /* size */
  &.size--small {
    height: 24px;
    padding: 0px 8px 0px 8px;
  }

  &.size--medium {
    height: 32px;
    padding: 0px 16px 0px 16px;
  }

  &.size--large {
    height: 40px;
    padding: 0px 24px 0px 24px;
  }

  /* style */
  &.style--primary-default {
    background-color: var(--primary-default);
    color: var(--black-default);
    border: none;

    &:hover {
      background-color: var(--primary-dark);
    }
  }

  &.style--secondary-default {
    background-color: var(--secondary-default);
    color: var(--black-default);
    border: none;

    &:hover {
      background-color: var(--secondary-dark);
    }
  }

  &.style--black-default {
    background-color: var(--black-default);
    color: var(--white-default);
    border: none;

    &:hover {
      background-color: var(--black-dark);
    }
  }

  &.style--gray-default {
    background-color: var(--gray-default);
    color: var(--black-default);
    border: none;

    &:hover {
      background-color: var(--gray-dark);
    }
  }

  &.style--red-default {
    background-color: var(--system-red);
    color: var(--black-default);
    border: none;
  }

  &.style--green-default {
    background-color: var(--system-green);
    color: var(--black-default);
    border: none;
  }

  &.style--orange-default {
    background-color: var(--system-orange);
    color: var(--black-default);
    border: none;
  }
`;

const Text = styled.div`
  /* size */
  &.size--small {
    font-family: Pretendard;
    font-size: 10px;
    font-weight: 700;
  }

  &.size--medium {
    font-family: Pretendard;
    font-size: 15px;
    font-weight: 700;
  }

  &.size--large {
    font-family: Pretendard;
    font-size: 20px;
    font-weight: 700;
  }
`;

export default CustomButton;
