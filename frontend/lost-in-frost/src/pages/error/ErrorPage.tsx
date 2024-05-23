import styled from "styled-components";

const ErrorPage = () => {
  return (
    <ErrorPageStyle>
      <Text>Error Page</Text>
    </ErrorPageStyle>
  );
};

const ErrorPageStyle = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
`;

const Text = styled.div`
  padding: 100px;
  font-family: Pretendard;
  font-size: 80px;
  font-weight: 700;
`;
export default ErrorPage;
