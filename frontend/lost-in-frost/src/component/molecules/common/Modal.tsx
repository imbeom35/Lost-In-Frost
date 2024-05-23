import { ReactNode, useState } from "react";
import { styled } from "styled-components";
import { ReactComponent as Cancel } from "@/asset/icon/Cancel.svg";

interface ModalProps {
  title?: string;
  width?: number;
  height?: number;
  children?: ReactNode;
  onClickExit?: () => void;
}

const Modal = ({ title, width, height, children, onClickExit }: ModalProps) => {
  return (
    <ModalStyle>
      <ModalContainer width={width} height={height}>
        <ModalHeader>
          <Title>{title}</Title>
          <CancelIcon onClick={onClickExit} />
        </ModalHeader>
        <ModalBody>{children}</ModalBody>
      </ModalContainer>
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
  background: rgba(0, 0, 0, 0.5);
  z-index: 10;
`;

const ModalContainer = styled.div<{ width?: number; height?: number }>`
  display: flex;
  flex-direction: column;
  width: ${(props) => (props.width ? props.width + "px" : "auto")};
  height: ${(props) => (props.height ? props.height + "px" : "auto")};
  border-radius: 5px;
  background-color: var(--white-default);
  overflow: hidden;
`;

const ModalHeader = styled.div`
  height: 50px;
  display: flex;
  align-items: center;
  padding: 0px 10px 0px 10px;
  justify-content: space-between;
  border-color: var(--gray-deep);
`;

const Title = styled.div`
  display: flex;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
`;

const ModalBody = styled.div`
  display: flex;
  flex-direction: column;
`;

const CancelIcon = styled(Cancel)`
  width: 25px;
  height: 25px;
  cursor: pointer;
`;

export default Modal;
