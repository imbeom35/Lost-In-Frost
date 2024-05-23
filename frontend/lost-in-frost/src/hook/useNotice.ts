import { GetNoticeDetailParams, getNoticeDetail } from "@/apis/apiNotice";
import React, { useEffect, useState } from "react";

const useNotice = (
  seq: number
): [
  string,
  string,
  string,
  string,
  string,
  number,
  number,
  (event: React.MouseEvent<HTMLElement>) => void,
  (event: React.MouseEvent<HTMLElement>) => void
] => {
  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [memberId, setMemberId] = useState<string>("");
  const [memberNickname, setMemberNickname] = useState<string>("");
  const [createDatetime, setCreateDatetime] = useState<string>("");
  const [commentCount, setCommentCount] = useState<number>(0);
  const [viewCount, setViewCount] = useState<number>(0);

  useEffect(() => {
    const initComponent = async () => {
      try {
        const getNoticeDetailParams: GetNoticeDetailParams = {
          noticeSeq: seq,
        };
        const response = await getNoticeDetail(getNoticeDetailParams);
        if (response) {
          if (response.data.success) {
            console.log(response.data.response.response);
            const item = response.data.response.response;
            setTitle(item.title);
            setContent(item.content);
            setMemberId(item.memberId);
            setMemberNickname(item.memberNickname);
            setCreateDatetime(item.createDatetime);
            setCommentCount(item.commentCount);
            setViewCount(item.viewCount);
          } else {
            alert(response.data.error.message);
          }
        }
      } catch (err) {
        console.log(err);
      }
    };
    initComponent();
  }, [seq]);

  const onClickDelete = (event: React.MouseEvent<HTMLElement>) => {};
  const onClickUpdate = (event: React.MouseEvent<HTMLElement>) => {};

  return [
    title,
    content,
    memberId,
    memberNickname,
    createDatetime,
    commentCount,
    viewCount,
    onClickDelete,
    onClickUpdate,
  ];
};

export default useNotice;
