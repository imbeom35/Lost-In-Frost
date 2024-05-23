import styled from "styled-components";
import "@/common/Color.css";
import "@/common/Font.css";
import { ReactComponent as Google } from "@/asset/logo/Google.svg";
import { ReactComponent as Kakao } from "@/asset/logo/Kakao.svg";
import { ReactComponent as Naver } from "@/asset/logo/Naver.svg";
import { GOOGLE_AUTH_API_URL } from "@/constant/api";
import { KAKAO_AUTH_API_URL } from "@/constant/api";
import { NAVER_AUTH_API_URL } from "@/constant/api";
import { useNavigate } from "react-router-dom";

interface SocialButtonProps {
  name: "google" | "kakao" | "naver";
}

const SocialButton = ({ name }: SocialButtonProps) => {
  const navigate = useNavigate();

  let icon;
  let text: string;
  let icon_width: string;
  let icon_height: string;
  let url: string;

  icon_width = "20px";
  icon_height = "20px";

  switch (name) {
    case "google":
      icon = <Google width={`${icon_width}`} height={`${icon_height}`} />;
      text = "Google 로그인";
      url = GOOGLE_AUTH_API_URL;
      break;
    case "kakao":
      icon = <Kakao width={`${icon_width}`} height={`${icon_height}`} />;
      text = "Kakao 로그인";
      url = KAKAO_AUTH_API_URL;
      break;
    case "naver":
      icon = <Naver width={`${icon_width}`} height={`${icon_height}`} />;
      text = "Naver 로그인";
      url = NAVER_AUTH_API_URL;
      break;
  }

  const open = (url: string) => {
    window.open(url, "_blank", "noopener, noreferrer");
  };

  return (
    <SocialButtonStyle onClick={() => (location.href = url)} className={`name--${name}`}>
      <IconContainer>{icon}</IconContainer>
      <Text>{text}</Text>
    </SocialButtonStyle>
  );
};

const SocialButtonStyle = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 300px;
  height: 40px;
  border-radius: 5px;
  cursor: pointer;

  /* name */
  &.name--google {
    background-color: var(--primary);
    color: var(--black-default);
    border: 1.5px solid;
    border-color: var(--gray-default);
  }

  &.name--kakao {
    background-color: #fee500;
    color: var(--black-default);
    border: none;
  }

  &.name--naver {
    background-color: #03c75a;
    color: var(--white-default);
    border: none;
  }
`;

const Text = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 600;
`;

const IconContainer = styled.div`
  position: absolute;
  display: flex;
  width: auto;
  height: auto;
  left: 10px;
`;

export default SocialButton;
