import styled from "styled-components";
import CustomButton from "@/component/atoms/button/CustomButton";
import { useNavigate } from "react-router-dom";
import useEmailCode from "@/hook/useEmailCode";
import userTempStore from "@/store/userTempStore";
import { postMailVerify } from "@/apis/apiMail";
import { postUserAuthJoin } from "@/apis/apiUser";
<<<<<<< HEAD
import { useState } from "react";
=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

const UserEmailCode = () => {
  const navigate = useNavigate();
  const [values, inputRefs, onKeyboardHandler, onChangeHandler, setValues] = useEmailCode();
  const {
    userTempEmail,
    userTempNickname,
    userTempPassword,
    setUserTempEmail,
    setUserTempNickname,
    setUserTempPassword,
    reset,
  } = userTempStore();

<<<<<<< HEAD
  const [isButtonDisabled, setIsButtonDisabled] = useState(false);

=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  const onClickButton = async () => {
    let numbers = "";
    for (const [key, value] of Object.entries(values)) {
      numbers += value;
    }

    if (numbers.length !== 6) {
      console.log("num: " + values);
      alert(numbers + "숫자 6자리를 입력해주세요.");
      return;
    }

    try {
      const verifyRes = await postMailVerify({
        email: userTempEmail,
        code: numbers,
      });

      if (verifyRes.data.success) {
<<<<<<< HEAD
        setIsButtonDisabled(false);

=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
        const joinRes = await postUserAuthJoin({
          email: userTempEmail,
          password: userTempPassword,
          nickname: userTempNickname,
        });

        if (joinRes.data.success) {
          alert("회원가입에 성공했습니다.");
          reset();
          navigate("/user/login");
        } else {
          alert(joinRes.data.error.message);
        }
      } else {
        alert(verifyRes.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <EmailCodeStyle>
      <Title>인증 코드 입력</Title>
      <Content>이메일로 발송된 인증코드 6자리를 입력해주세요.</Content>
      <CodeWrapper6>
        <CodeWrapper3>
          <Code
            name="1"
            type="number"
            ref={(el) => {
              if (el) {
                inputRefs.current[1] = el;
              }
            }}
            onKeyDown={onKeyboardHandler}
            onChange={onChangeHandler}
          />
          <Code
            name="2"
            type="number"
            ref={(el) => {
              if (el) {
                inputRefs.current[2] = el;
              }
            }}
            onKeyDown={onKeyboardHandler}
            onChange={onChangeHandler}
          />
          <Code
            name="3"
            type="number"
            ref={(el) => {
              if (el) {
                inputRefs.current[3] = el;
              }
            }}
            onKeyDown={onKeyboardHandler}
            onChange={onChangeHandler}
          />
        </CodeWrapper3>
        <CodeWrapper3>
          <Code
            name="4"
            type="number"
            ref={(el) => {
              if (el) {
                inputRefs.current[4] = el;
              }
            }}
            onKeyDown={onKeyboardHandler}
            onChange={onChangeHandler}
          />
          <Code
            name="5"
            type="number"
            ref={(el) => {
              if (el) {
                inputRefs.current[5] = el;
              }
            }}
            onKeyDown={onKeyboardHandler}
            onChange={onChangeHandler}
          />
          <Code
            name="6"
            type="number"
            ref={(el) => {
              if (el) {
                inputRefs.current[6] = el;
              }
            }}
            onKeyDown={onKeyboardHandler}
            onChange={onChangeHandler}
          />
        </CodeWrapper3>
      </CodeWrapper6>
      <ButtonWrapper>
        <CustomButton
          style="default"
          color="black"
          label="이전"
          size="medium"
          onClick={() => navigate("/user/join")}
        ></CustomButton>

        <CustomButton
<<<<<<< HEAD
          style={isButtonDisabled ? "disable" : "default"}
=======
          style="default"
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
          color="primary"
          label="확인"
          size="medium"
          onClick={() => onClickButton()}
        ></CustomButton>
      </ButtonWrapper>
    </EmailCodeStyle>
  );
};

const EmailCodeStyle = styled.div`
  width: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: left;
  box-sizing: border-box;

  > *:nth-child(n + 2) {
    margin-top: 20px;
  }
`;

const Title = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: start;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
`;

const Content = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: start;
  font-family: Pretendard;
  font-size: 12px;
  font-weight: 500;
`;

const CodeWrapper6 = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const CodeWrapper3 = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;

  > *:nth-child(n + 2) {
    margin-left: 5px;
  }
`;

const Code = styled.input`
  display: flex;
  text-align: center;
  justify-content: center;
  border: 2px solid;
  border-radius: 10px;
  border-color: var(--primary-dark);
  width: 40px;
  height: 50px;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
  &::-webkit-inner-spin-button {
    appearance: none;
    -moz-appearance: none;
    -webkit-appearance: none;
  }
`;

const ButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: end;

  > *:nth-child(n + 2) {
    margin-left: 5px;
  }
`;

export default UserEmailCode;
