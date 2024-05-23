import RecordListContent from "@/component/molecules/record/list/RecordListContent";
import { useParams } from "react-router-dom";
import styled from "styled-components";

const RecordList = () => {
  const { id } = useParams();

  return <RecordListStyle>{id && <RecordListContent nickname={id} />}</RecordListStyle>;
};

const RecordListStyle = styled.div``;

export default RecordList;
