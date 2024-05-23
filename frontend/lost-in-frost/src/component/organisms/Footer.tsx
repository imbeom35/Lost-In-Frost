import { styled } from "styled-components";

const Footer = () => {
  return (
    <FooterStyle>
      <Center>{`팀명: 겨우내 | 개인정보처리방침`}</Center>
    </FooterStyle>
  );
};

const FooterStyle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 200px;
  background-color: var(--black-default);
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500px;
  color: var(--white-default);

  @media (max-width: 1200px) {
    justify-content: flex-start;
  }
`;

const Center = styled.div`
  display: flex;
  align-items: center;
  justify-content: left;
  width: 1200px;
  min-width: 800;
`;

export default Footer;
