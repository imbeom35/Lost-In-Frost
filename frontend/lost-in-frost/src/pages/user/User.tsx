import styled from "styled-components";
import { Route, Routes } from "react-router-dom";
import UserLogin from "./UserLogin";
import UserJoin from "./UserJoin";
import UserEmailCode from "./UserEmailCode";
import UserRedirect from "./UserRedirect";

const User = () => {
  return (
    <UserStyle>
      <Wrapper>
        <Routes>
          <Route path="login" element={<UserLogin />} />
          <Route path="join" element={<UserJoin />} />
          <Route path="email-code" element={<UserEmailCode />} />
          <Route path="redirect" element={<UserRedirect />} />
        </Routes>
      </Wrapper>
    </UserStyle>
  );
};

const UserStyle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0;
  padding: 0;
`;

const Wrapper = styled.div`
<<<<<<< HEAD
  margin-top: 100px;
=======
  margin-top: 30px;
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default User;
