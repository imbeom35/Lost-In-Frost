import styled from "styled-components";
import { ReactComponent as SearchIcon } from "@/asset/icon/Search.svg";
import { costumeImageUrl } from "@/utils/formatter";
import useRecordSearchSelectBox from "@/hook/useRecordSearchSelectBox";

const RecordSearchContentSelectBox = () => {
  const [userId, value, isActive, itemList, search, onChangeHandler, keboardHandler] = useRecordSearchSelectBox();

  return (
    <RecordSearchSelectBoxStyle>
      <SearchBox>
        <SearchInput
          onChange={onChangeHandler}
          onKeyDown={keboardHandler}
          placeholder="닉네임을 입력해주세요."
          value={value}
          id="input"
        />
        <SearchIconStyle id={"search"} onClick={search} />
      </SearchBox>

      {isActive && (
        <ListItemWrapper $top={60}>
          {itemList.length > 0 && <ListTitle>연관 닉네임</ListTitle>}
          {itemList.map((item, index) => (
            <ListItem key={index} id={item.memberId}>
              <ListItemProfile src={costumeImageUrl("face", item.memberImage)}></ListItemProfile>
              <ListItemTextBox>
                <ListItemNickname>{item.memberNickname}</ListItemNickname>
                <ListItemLevel>Lv {item.memberLevel}</ListItemLevel>
              </ListItemTextBox>
            </ListItem>
          ))}
        </ListItemWrapper>
      )}
    </RecordSearchSelectBoxStyle>
  );
};

const RecordSearchSelectBoxStyle = styled.div`
  position: relative;
`;

const SearchBox = styled.div`
  width: 400px;
  height: 50px;
  background-color: var(--white-default);
  display: flex;
  flex-direction: row;
`;

const SearchInput = styled.input`
  flex-grow: 1;
  outline: none;
  border: none;
  padding: 0px 20px 0px 20px;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
`;

const SearchIconStyle = styled(SearchIcon)`
  height: 50px;
  width: 50px;
  padding: 15px;
  box-sizing: border-box;
  fill: var(--primary-dark);
  cursor: pointer;
`;

const ListItemWrapper = styled.div<{ $top?: number }>`
  position: absolute;
  width: 400px;
  max-height: 400px;
  right: 0px;
  top: ${(props) => (props.$top ? `${props.$top}px` : "0px")};
  display: flex;
  flex-direction: column;
  overflow: hidden;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.3);
`;

const ListTitle = styled.div`
  width: 100%;
  height: 30px;
  padding: 0px 10px 0px 10px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: start;
  font-family: Pretendard;
  font-size: 12px;
  font-weight: 500;
  background-color: var(--primary-light);
`;

const ListItem = styled.div`
  width: 100%;
  height: 60px;
  padding: 10px 10px 10px 10px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  display: flex;
  flex-direction: row;
  cursor: pointer;
  background-color: var(--white-default);

  > *:nth-child(n + 2) {
    margin-left: 5px;
  }

  &:hover {
    background-color: var(--gray-light);
  }

  > * {
    pointer-events: none;
  }
`;

const ListItemProfile = styled.img`
  width: 40px;
  height: 40px;
  border: none;
  border-radius: 50px;
  background-color: var(--gray-default);
`;

const ListItemTextBox = styled.div`
  flex: 1;
  height: 100%;
  display: flex;
  flex-direction: column;
`;

const ListItemLevel = styled.div`
  flex: 1;
  height: 100%;
  display: flex;
  align-items: center;
  font-family: Pretendard;
  font-size: 10px;
  font-weight: 500;
  color: var(--black-light);
`;

const ListItemNickname = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
  font-family: Pretendard;
  font-size: 15px;
  font-weight: 500;
  color: var(--black-default);
`;

export default RecordSearchContentSelectBox;
