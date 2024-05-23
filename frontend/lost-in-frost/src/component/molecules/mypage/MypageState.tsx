import { styled } from "styled-components";
import { ReactComponent as Profile } from "@/asset/icon/Profile.svg";
import navigationStore from "@/store/navigationStore";
import { S3_URL } from "@/constant/api";
import { costumeImageUrl } from "@/utils/formatter";
import useStateMessage from "@/hook/useStateMessage";
import userStore from "@/store/userStore";

const MypageState = () => {
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

  const [stateMessage, isChange, onChangeHandler, onBlurHandler] = useStateMessage();

  return (
    <MypageStateStyle>
      <Thumbnail src={costumeImageUrl("face", costumeName)} />
      <StateWrapper>
        <Title>{nickname}</Title>
        <Message
          className={`isChange--${isChange}`}
          placeholder="상태메세지"
          value={stateMessage ? stateMessage : ""}
          onChange={onChangeHandler}
          onBlur={onBlurHandler}
        ></Message>
      </StateWrapper>
    </MypageStateStyle>
  );
};

const MypageStateStyle = styled.div`
  display: flex;
  align-items: center;
  justify-content: left;
  flex-direction: row;
  width: 100%;
`;

const Thumbnail = styled.img`
  display: flex;
  margin-right: 15px;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: var(--gray-light);
`;

const StateWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100px;
`;

const Title = styled.div`
  color: var(--black-default);
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
`;

const Message = styled.textarea`
  display: block;
  border: 2px solid;
  border-color: var(--gray-dark);
  border-radius: 5px;
  background-color: var(--gray-light);
  padding: 10px;
  box-sizing: border-box;
  width: 300px;
  height: 60px;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 500;
  resize: none;

  &.isChange--true {
    border-color: var(--black-default);
    background-color: var(--white-default);
  }
`;

export default MypageState;
