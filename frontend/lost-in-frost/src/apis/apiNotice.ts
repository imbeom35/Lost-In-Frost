import axiosInstance from "@/apis/axiosInstance";
import { END_POINTS } from "@/constant/api";

export interface DeleteNoticeDeleteParams {
  noticeSeq: number;
}
export const deleteNoticeDelete = async ({ noticeSeq }: DeleteNoticeDeleteParams) => {
  return await axiosInstance.delete(END_POINTS.NOTICE_DELETE(noticeSeq));
};

export interface DeleteNoticeCommentDeleteParams {
  noticeCommentSeq: number;
}
export const deleteNoticeCommentDelete = async ({ noticeCommentSeq }: DeleteNoticeCommentDeleteParams) => {
  return await axiosInstance.delete(END_POINTS.NOTICE_COMMENT_DELETE(noticeCommentSeq));
};

export interface GetNoticeListParams {
  page: number;
  size: number;
}
export const getNoticeList = async ({ page, size }: GetNoticeListParams) => {
  return await axiosInstance.get(END_POINTS.NOTICE_LIST, {
    params: {
      page: page,
      size: size,
    },
  });
};

export interface GetNoticeDetailParams {
  noticeSeq: number;
}
export const getNoticeDetail = async ({ noticeSeq }: GetNoticeDetailParams) => {
  return await axiosInstance.get(END_POINTS.NOTICE_DETAIL(noticeSeq));
};

export interface GetNoticeCommentListParams {
  noticeSeq: number;
  page: number;
  size: number;
}
export const getNoticeCommentList = async ({ noticeSeq, page, size }: GetNoticeCommentListParams) => {
  return await axiosInstance.get(END_POINTS.NOTICE_COMMENT_LIST(noticeSeq), {
    params: {
      page: page,
      size: size,
    },
  });
};

export interface PostNoticeWriteParams {
  title: string;
  content: string;
}
export const postNoticeWrite = async ({ title, content }: PostNoticeWriteParams) => {
  return await axiosInstance.post(END_POINTS.NOTICE_WRITE, {
    title: title,
    content: content,
  });
};

// 단일
export interface PostNoticeCommentWriteParams {
  noticeSeq: number;
  content: string;
}
export const postNoticeCommentWrite = async ({ noticeSeq, content }: PostNoticeCommentWriteParams) => {
  return await axiosInstance.post(END_POINTS.NOTICE_COMMENT_WRITE(noticeSeq), content);
};

export interface PutNoticeUpdateParams {
  noticeSeq: number;
  title: string;
  content: string;
}
export const putNoticeUpdate = async ({ noticeSeq, title, content }: PutNoticeUpdateParams) => {
  return await axiosInstance.put(END_POINTS.NOTICE_UPDATE(noticeSeq), {
    title: title,
    content: content,
  });
};

// 단일
export interface PutNoticeCommentParams {
  noticeSeq: number;
  content: string;
}
export const putNoticeComment = async ({ noticeSeq, content }: PutNoticeCommentParams) => {
  return await axiosInstance.put(END_POINTS.NOTICE_COMMENT_UPDATE(noticeSeq), content);
};
