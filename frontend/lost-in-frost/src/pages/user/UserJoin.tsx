import React, { useState, useRef } from "react";
import styled from "styled-components";
import CustomInput from "@/component/atoms/input/CustomInput";
import CustomButton from "@/component/atoms/button/CustomButton";
<<<<<<< HEAD
=======
import MainLogo from "@/asset/logo/MainLogo.png";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
import { useNavigate } from "react-router-dom";
import useJoin from "@/hook/useJoin";
import userTempStore from "@/store/userTempStore";
import { postMailSend } from "@/apis/apiMail";

const UserJoin = () => {
  const navigate = useNavigate();
  const [values, messages, states, onChange] = useJoin();
  const {
    userTempEmail,
    userTempNickname,
    userTempPassword,
    setUserTempEmail,
    setUserTempNickname,
    setUserTempPassword,
  } = userTempStore();

  const onSubmit = async () => {
    for (const [key, value] of Object.entries(states)) {
      console.log(key + " " + value);
      if (value === false) {
        alert("형식이 올바르지 않습니다.");
        return;
      }
    }

    try {
      const response = await postMailSend({
        email: values.email,
        memberNickname: values.nickname,
      });

      if (response.data.success) {
        setUserTempEmail(values.email);
        setUserTempNickname(values.nickname);
        setUserTempPassword(values.password);
        navigate("/user/email-code");
      } else {
        console.log(response);
        // alert(response.data.error.message);
      }
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <SignupContainer>
<<<<<<< HEAD
      <NeoStyle onClick={() => navigate("/")}>Lost In Frost</NeoStyle>
=======
      <MainLogoStyle src={MainLogo} onClick={() => navigate("/")}></MainLogoStyle>
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
      <ItemWrapper>
        <CustomInput
          disabled={false}
          placeholder="이메일"
          size="large"
          width={300}
          name="email"
          value={values.email}
          onChange={onChange}
        />
        <StateMessage $state={states.email}>{messages.email}</StateMessage>
        <CustomInput
          disabled={false}
          placeholder="닉네임"
          size="large"
          width={300}
          name="nickname"
          value={values.nickname}
          onChange={onChange}
        />
        <StateMessage $state={states.nickname}>{messages.nickname}</StateMessage>
        <CustomInput
          type="password"
          disabled={false}
          placeholder="비밀번호"
          size="large"
          width={300}
          name="password"
          value={values.password}
          onChange={onChange}
        />
        <StateMessage $state={states.password}>{messages.password}</StateMessage>
        <CustomInput
          type="password"
          disabled={false}
          placeholder="비밀번호 확인"
          size="large"
          width={300}
          name="passwordCheck"
          value={values.passwordCheck}
          onChange={onChange}
        />

        <StateMessage $state={states.passwordCheck}>{messages.passwordCheck}</StateMessage>
        <CustomButton
          style="default"
          color="black"
          label="이메일 인증하기"
          size="medium"
          width={"300px"}
          onClick={() => onSubmit()}
        />
      </ItemWrapper>
    </SignupContainer>
  );
};

const SignupContainer = styled.div`
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

const StateMessage = styled.div<{ $state?: boolean }>`
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: left;
  font-family: Pretendard;
  font-size: 12px;
  font-weight: 500;
  color: ${(props) => (props.$state ? "var(--system-green)" : "var(--system-red)")};
`;

export default UserJoin;
