import styled from "styled-components";
import RecordSearchContentSelectBox from "./RecordSearchContentSelectBox";

const RecordSearchContent = () => {
  return (
    <RecordSearchStyle>
      <RecordSearchContentSelectBox></RecordSearchContentSelectBox>
    </RecordSearchStyle>
  );
};

const RecordSearchStyle = styled.div`
  width: 100%;
  height: 400px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--black-light);
`;

export default RecordSearchContent;
