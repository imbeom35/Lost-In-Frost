import styled from "styled-components";
import "@/common/Color.css";
import "@/common/Font.css";

interface CustomInputProps {
  type?: string;
  placeholder: string;
  size: "small" | "medium" | "large";
  disabled: boolean;
  width: number;
  name?: string;
  value?: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onKeyDown?: (event: React.KeyboardEvent<HTMLInputElement>) => void;
}

const CustomInput = ({
  type,
  placeholder,
  size,
  disabled,
  width,
  name,
  value,
  onChange,
  onKeyDown,
}: CustomInputProps) => {
  return (
    <CustomInputStyle
      type={type}
      name={name}
      value={value}
      onChange={onChange}
      onKeyDown={onKeyDown}
      placeholder={placeholder}
      disabled={disabled}
      width={`${width}px`}
      className={`size--${size}`}
    />
  );
};

const CustomInputStyle = styled.input`
  display: flex;
  border-radius: 5px;
  border: 1px solid;
  border-color: var(--gray-dark);
  padding: 0;
  width: ${(props) => (props.width === "0px" ? "auto" : props.width)};
  box-sizing: border-box;

  /* size */
  &.size--small {
    height: 16px;
    padding: 0px 4px 0px 4px;
    font-family: Pretendard;
    font-size: 8px;
    font-weight: 500;
  }

  &.size--medium {
    height: 24px;
    padding: 0px 8px 0px 8px;
    font-family: Pretendard;
    font-size: 12px;
    font-weight: 500;
  }

  &.size--large {
    height: 32px;
    padding: 0px 12px 0px 12px;
    font-family: Pretendard;
    font-size: 16px;
    font-weight: 500;
  }
`;

export default CustomInput;
