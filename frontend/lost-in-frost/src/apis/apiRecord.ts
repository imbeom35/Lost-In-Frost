import axiosInstance from "@/apis/axiosInstance";
import { END_POINTS } from "@/constant/api";

export interface GetGameRecordListParams {
  nickname: string;
  page: number;
  size: number;
}
export const getGameRecordList = async ({ nickname, page, size }: GetGameRecordListParams) => {
  return await axiosInstance.get(END_POINTS.GAME_RECORD_LIST(nickname), {
    params: {
      page: page,
      size: size,
    },
  });
};

export interface GetGameRecordListDetailParams {
  roomId: string;
}
export const getGameRecordListDetail = async ({ roomId }: GetGameRecordListDetailParams) => {
  return await axiosInstance.get(END_POINTS.GAME_RECORD_LIST_DETAIL(roomId));
};
