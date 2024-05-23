import styled, { keyframes } from "styled-components";

const AxiosSpinner = () => {
  return <AxiosSpinnerStyle></AxiosSpinnerStyle>;
};

const rotate = keyframes`
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
`;

const AxiosSpinnerStyle = styled.div`
  width: 48px;
  height: 48px;
  border: 5px solid var(--primary-default);
  border-bottom-color: transparent;
  border-radius: 50%;
  display: inline-block;
  box-sizing: border-box;
  animation: ${rotate} 1s linear infinite;
`;

export default AxiosSpinner;
