import {
  PostUserValidatePasswordParams,
  PutUserMyPageInfoParams,
  deleteUserAuthWithdrawal,
  postUserValidatePassword,
  putUserMyPageInfo,
} from "@/apis/apiUser";
import CustomButton from "@/component/atoms/button/CustomButton";
import Pipeline from "@/component/atoms/common/Pipeline";
import CustomInput from "@/component/atoms/input/CustomInput";
import useInput from "@/hook/useInput";
import userStore from "@/store/userStore";
<<<<<<< HEAD
import { logout } from "@/utils/user";
=======
import { logout } from "@/utils/logout";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
import { useState } from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";

const MypageInfo = () => {
  const {
    email,
    nickname,
    level,
    experience,
    crystal,
    coin,
    myCostumeSeq,
    costumeSeq,
    costumeName,
    costumeImage,
    costumeGrade,
    message,
    authProvider,
    gamePlayCount,
    successCount,
    setEmail,
    setNickname,
    setLevel,
    setExperience,
    setCrystal,
    setCoin,
    setMyCostumeSeq,
    setCostumeSeq,
    setCostumeName,
    setCostumeImage,
    setCostumeGrade,
    setMessage,
    setAuthProvider,
    setGamePlayCount,
    setSuccessCount,
    reset,
  } = userStore();
  const [isUpdate, setIsUpdate] = useState(false);
  const navigate = useNavigate();
  const [newNickname, onChangeNewNickname, setNewNickname] = useInput(nickname);
  const [oldPassword, onChangeOldPassword, setOldPassword] = useInput("");
  const [newPassword, onChangeNewPassword, setNewPassword] = useInput("");

  const widthdrawal = async () => {
    confirm("확실합니까?");

    try {
      const response = await deleteUserAuthWithdrawal();
      console.log(response);
      if (response.data.success) {
        logout();
        navigate("/");
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const update = async () => {
    if (newNickname.length < 2) {
      alert("닉네임은 2자리 이상이어야 합니다.");
      return;
    }

    if (newPassword.length < 6) {
      alert("비밀번호는 6자리 이상이어야 합니다.");
      return;
    }

    try {
      const userValidatePasswordParams: PostUserValidatePasswordParams = {
        password: oldPassword,
      };
      const passwordRes = await postUserValidatePassword(userValidatePasswordParams);
      console.log(oldPassword);
      if (passwordRes.data.success) {
        const userMyPageInfoParams: PutUserMyPageInfoParams = {
          nickname: newNickname,
          password: newPassword,
        };
        const response = await putUserMyPageInfo(userMyPageInfoParams);
        if (response.data.success) {
          setNickname(newNickname);
          alert("회원정보가 수정되었습니다.");
          updateCancel();
        } else {
          alert(response.data.error.message);
        }
      } else {
        alert(passwordRes.data.error.message);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const updateCancel = () => {
    setIsUpdate(false);
    setNewNickname(nickname);
    setOldPassword("");
    setNewPassword("");
  };

  return (
    <MypageInfoStyle>
      <WrapperItem>
        <ItemBox>
          <Text $width={120}>이메일</Text>
          <Pipeline color="var(--black-default)" height={15} thickness={2} width={80}></Pipeline>
          <Text $width={250}>{email}</Text>
        </ItemBox>
        <ItemBox>
          <Text $width={120}>닉네임</Text>
          <Pipeline color="var(--black-default)" height={15} thickness={2} width={80}></Pipeline>
          {isUpdate ? (
            <CustomInput
              value={newNickname}
              onChange={onChangeNewNickname}
              disabled={false}
              placeholder=""
              size="large"
              width={300}
            ></CustomInput>
          ) : (
            <Text $width={250}>{nickname}</Text>
          )}
        </ItemBox>
        {isUpdate && (
          <ItemBox>
            <Text $width={120}>현재 비밀번호</Text>
            <Pipeline color="var(--black-default)" height={15} thickness={2} width={80}></Pipeline>
            <CustomInput
              value={oldPassword}
              onChange={onChangeOldPassword}
              type="password"
              disabled={false}
              placeholder=""
              size="large"
              width={300}
            ></CustomInput>
          </ItemBox>
        )}
        {isUpdate && (
          <ItemBox>
            <Text $width={120}>변경 비밀번호</Text>
            <Pipeline color="var(--black-default)" height={15} thickness={2} width={80}></Pipeline>
            <CustomInput
              value={newPassword}
              onChange={onChangeNewPassword}
              type="password"
              disabled={false}
              placeholder=""
              size="large"
              width={300}
            ></CustomInput>
          </ItemBox>
        )}

        <Controller>
          {!isUpdate && (
<<<<<<< HEAD
            <CustomButton
              style="default"
              color="black"
              label="회원탈퇴"
              size="large"
              onClick={widthdrawal}
            />
          )}
          {!isUpdate && (
            <CustomButton
              style="default"
              color="primary"
              label="수정"
              size="large"
              onClick={() => setIsUpdate(true)}
            />
          )}
          {isUpdate && (
            <CustomButton
              style="default"
              color="black"
              label="취소"
              size="large"
              onClick={updateCancel}
            />
          )}
          {isUpdate && (
            <CustomButton
              style="default"
              color="primary"
              label="완료"
              size="large"
              onClick={update}
            />
          )}
=======
            <CustomButton style="default" color="black" label="회원탈퇴" size="large" onClick={widthdrawal} />
          )}
          {!isUpdate && (
            <CustomButton style="default" color="primary" label="수정" size="large" onClick={() => setIsUpdate(true)} />
          )}
          {isUpdate && <CustomButton style="default" color="black" label="취소" size="large" onClick={updateCancel} />}
          {isUpdate && <CustomButton style="default" color="primary" label="완료" size="large" onClick={update} />}
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
        </Controller>
      </WrapperItem>
    </MypageInfoStyle>
  );
};

const MypageInfoStyle = styled.div`
  padding: 100px 0px 100px 0px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const WrapperItem = styled.div`
  width: auto;
  display: flex;
  flex-direction: column;
  align-items: center;

  > *:nth-child(n + 2) {
    margin-top: 30px;
  }
`;

const ItemBox = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;
`;

const Text = styled.div<{ $width?: number }>`
  width: ${(props) => (props.$width ? `${props.$width}px` : "auto")};
  font-family: Pretendard;
  font-size: 20px;
  font-weight: 500;
  color: var(--black-default);
`;

const Controller = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;

  > *:nth-child(n + 2) {
    margin-left: 10px;
  }
`;

export default MypageInfo;
