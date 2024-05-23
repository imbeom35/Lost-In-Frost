import axiosInstance from "@/apis/axiosInstance";
import { END_POINTS } from "@/constant/api";

export interface GetRankingListParams {
  page: number;
  size: number;
}
export const getRankingList = async ({ page, size }: GetRankingListParams) => {
  return await axiosInstance.get(END_POINTS.RANKING_LIST, {
    params: {
      page: page,
      size: size,
    },
  });
};
