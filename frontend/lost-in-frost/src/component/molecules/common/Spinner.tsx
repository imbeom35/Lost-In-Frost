import AxiosSpinner from "@/component/atoms/spinner/AxiosSpinner";
import { styled } from "styled-components";

const Spinner = () => {
  return (
    <ModalStyle>
      <AxiosSpinner />
    </ModalStyle>
  );
};

const ModalStyle = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background: rgba(0, 0, 0, 0.5); */
  z-index: 10;
`;

export default Spinner;
