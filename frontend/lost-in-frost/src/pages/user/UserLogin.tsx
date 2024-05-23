import styled from "styled-components";
import CustomInput from "@/component/atoms/input/CustomInput";
import SocialButton from "@/component/atoms/button/SocialButton";
import CustomButton from "@/component/atoms/button/CustomButton";
<<<<<<< HEAD
=======
import MainLogo from "@/asset/logo/MainLogo.png";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
import { useNavigate } from "react-router-dom";
import useLogin from "@/hook/useLogin";
import Spinner from "@/component/molecules/common/Spinner";

const UserLogin = () => {
  const navigate = useNavigate();

  const [values, messages, states, onSubmit, onChange, onKeyboard, setValues] = useLogin();

  return (
    <LoginContainer>
<<<<<<< HEAD
      <NeoStyle onClick={() => navigate("/")}>Lost In Frost</NeoStyle>
=======
      <MainLogoStyle src={MainLogo} onClick={() => navigate("/")}></MainLogoStyle>
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      <ItemWrapper>
        <CustomInput
          placeholder="Email"
          size="large"
          disabled={false}
          width={300}
          name="email"
          value={values.email}
          onChange={onChange}
        />
        <CustomInput
          type="password"
          placeholder="Password"
          size="large"
          disabled={false}
          width={300}
          name="password"
          value={values.password}
          onKeyDown={onKeyboard}
          onChange={onChange}
        />
        <CustomButton
          label="로그인"
          size="medium"
          color="black"
          style="default"
          width={"300px"}
          onClick={() => onSubmit()}
        />
        <Text>
          <TextItem onClick={() => navigate("/user/join")}>회원가입</TextItem>
<<<<<<< HEAD
=======
          {"\u00A0|\u00A0"}
          <TextItem onClick={() => navigate("/")}>아이디 찾기</TextItem>
          {"\u00A0|\u00A0"}
          <TextItem onClick={() => navigate("/")}>비밀번호 찾기</TextItem>
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
        </Text>
      </ItemWrapper>
      <ItemWrapper>
        <SocialButton name="google"></SocialButton>
        <SocialButton name="kakao"></SocialButton>
        <SocialButton name="naver"></SocialButton>
      </ItemWrapper>
    </LoginContainer>
  );
};

const LoginContainer = styled.div`
  width: 300px;
  display: flex;
  align-items: center;
  flex-direction: column;
  box-sizing: border-box;

  > *:nth-child(n + 2) {
    margin-top: 20px;
  }
`;

<<<<<<< HEAD
const NeoStyle = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Neo;
  font-size: 50px;
  font-weight: 500;
  color: var(--primary-dark);
=======
const MainLogoStyle = styled.img`
  width: 200px;
  height: 200px;
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  cursor: pointer;
`;

const ItemWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;

  > *:nth-child(n + 2) {
    margin-top: 5px;
  }
`;

const Text = styled.div`
  display: flex;
  justify-content: left;
  width: 300px;
  font-family: Pretendard;
  font-size: 12px;
  font-weight: 500;
`;

const TextItem = styled.div`
  cursor: pointer;
  &:hover {
    font-weight: bold;
  }
`;

export default UserLogin;
