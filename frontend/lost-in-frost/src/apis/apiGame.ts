import { END_POINTS } from "@/constant/api";
import axiosInstance from "./axiosInstance";

export const getGameFileLauncherDownload = async () => {
  return await axiosInstance.get(END_POINTS.GAME_FILE_LAUNCHER_DOWNLOAD, {
    responseType: "blob",
  });
};

export interface GetGameCostumeListParams {
  page: number;
  size: number;
}
export const getGameCostumeList = async ({ page, size }: GetGameCostumeListParams) => {
  return await axiosInstance.get(END_POINTS.GAME_COSTUME_LIST, {
    params: {
      page: page,
      size: size,
    },
  });
};

export const getGameCostumeCount = async () => {
  return await axiosInstance.get(END_POINTS.GAME_COSTUME_COUNT);
};
