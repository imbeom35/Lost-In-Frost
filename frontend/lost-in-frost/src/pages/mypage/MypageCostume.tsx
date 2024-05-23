import MypageCostumeList from "@/component/molecules/mypage/costume/MypageCostumeList";
import styled from "styled-components";

const MypageCostume = () => {
  return (
    <MypageCostumeStyle>
      <MypageCostumeList />
    </MypageCostumeStyle>
  );
};

const MypageCostumeStyle = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

export default MypageCostume;
