import { GetUserInfoRecord, GetUserInfoRecordParams } from "@/apis/apiUser";
import Pipeline from "@/component/atoms/common/Pipeline";
import { costumeImageUrl } from "@/utils/formatter";
import { useEffect, useState } from "react";
import styled from "styled-components";

interface RecordListContentStateProps {
  nickname: string;
}

interface User {
  gamePlayCount: number;
  level: number;
  memberId: string;
  memberImage: string;
  nickname: string;
  successCount: number;
}

const RecordListContentState = ({ nickname }: RecordListContentStateProps) => {
  const [user, setUser] = useState<User>({
    gamePlayCount: 0,
    level: 0,
    memberId: "",
    memberImage: "",
    nickname: "",
    successCount: 0,
  });

  useEffect(() => {
    const initComponent = async () => {
      try {
        const userInfoRecordParams: GetUserInfoRecordParams = {
          nickname: nickname,
        };
        const response = await GetUserInfoRecord(userInfoRecordParams);
        console.log("??", response);
        setUser(response.data.response);
      } catch (err) {
        console.log(err);
      }
    };
    initComponent();
  }, [nickname]);

  console.log(user.memberImage);

  return (
    <RecordListContentStateStyle>
      {<Profile src={costumeImageUrl("face", user.memberImage)} />}
      <TextWrapper>
        <Nickname>{user.nickname}</Nickname>
        <UserData>
          Lv {user.level}
          <Pipeline color="var(--gray-default)" height={25} thickness={2} width={50}></Pipeline>
          Play {user.gamePlayCount}
          <Pipeline color="var(--gray-default)" height={25} thickness={2} width={50}></Pipeline>
          Clear {user.successCount}
        </UserData>
      </TextWrapper>
    </RecordListContentStateStyle>
  );
};

const RecordListContentStateStyle = styled.div`
  height: 120px;
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: start;

  > *:nth-child(n + 2) {
    margin-left: 20px;
  }
`;

const Profile = styled.img`
  width: 120px;
  height: 120px;
  border: none;
  border-radius: 50%;
  background-color: var(--gray-light);
`;

const TextWrapper = styled.div`
  height: 120px;
  display: flex;
  flex-direction: column;
`;

const Nickname = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: start;
  font-family: Pretendard;
  font-size: 32px;
  font-weight: 700;
  color: var(--black-default);
`;

const UserData = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: start;
  font-family: Pretendard;
  font-size: 24px;
  font-weight: 700;
  color: var(--gray-default);
`;

export default RecordListContentState;
