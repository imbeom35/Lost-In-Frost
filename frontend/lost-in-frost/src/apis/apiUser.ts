import axiosInstance from "@/apis/axiosInstance";
import { END_POINTS } from "@/constant/api";

export const deleteUserAuthWithdrawal = async () => {
  return await axiosInstance.delete(END_POINTS.USER_AUTH_WITHDRAWAL);
};

export interface PostUserAuthLoginParams {
  email: string;
  password: string;
}
export const postUserAuthLogin = async ({ email, password }: PostUserAuthLoginParams) => {
  return await axiosInstance.post(END_POINTS.USER_AUTH_LOGIN, {
    email: email,
    password: password,
  });
};

export interface PostUserAuthJoinParams {
  email: string;
  password: string;
  nickname: string;
}
export const postUserAuthJoin = async ({ email, password, nickname }: PostUserAuthJoinParams) => {
  return await axiosInstance.post(END_POINTS.USER_AUTH_JOIN, {
    email: email,
    password: password,
    nickname: nickname,
  });
};

export interface GetUserValidateNicknameParams {
  nickname: string;
}
export const getUserValidateNickname = async ({ nickname }: GetUserValidateNicknameParams) => {
  return await axiosInstance.get(END_POINTS.USER_VALIDATE_NICKNAME(nickname));
};

export interface GetUserValidateEmailParams {
  email: string;
}
export const getUserValidateEmail = async ({ email }: GetUserValidateEmailParams) => {
  return await axiosInstance.get(END_POINTS.USER_VALIDATE_EMAIL(email));
};

export const getUserMyPageInfo = async () => {
  return await axiosInstance.get(END_POINTS.USER_MYPAGE_INFO);
};

export interface PutUserMyPageInfoParams {
  nickname: string;
  password: string;
}
export const putUserMyPageInfo = async ({ nickname, password }: PutUserMyPageInfoParams) => {
  return await axiosInstance.put(END_POINTS.USER_MYPAGE_INFO, {
    nickname: nickname,
    password: password,
  });
};

export const getUserMyPageCrystal = async () => {
  return await axiosInstance.get(END_POINTS.USER_MYPAGE_CRYSTAL);
};

export const getUserMyPageCoin = async () => {
  return await axiosInstance.get(END_POINTS.USER_MYPAGE_COIN);
};

export const getUserMyPageAmount = async () => {
  return await axiosInstance.get(END_POINTS.USER_MYPAGE_AMOUNT);
};

export interface GetMyCostumeListParams {
  page: number;
  size: number;
}
export const getMyCostumeList = async ({ page, size }: GetMyCostumeListParams) => {
  return await axiosInstance.get(END_POINTS.USER_MYCOSTUME_LIST, {
    params: {
      page: page,
      size: size,
    },
  });
};

export interface PutUserMyCostumeParams {
  myCostumeSeq: number;
}
export const PutUserMyCostume = async ({ myCostumeSeq }: PutUserMyCostumeParams) => {
  return await axiosInstance.put(END_POINTS.USER_MYCOSTUME(myCostumeSeq));
};

export const GetUserMyCostumeCount = async () => {
  return await axiosInstance.get(END_POINTS.USER_MYCOSTUME_COUNT);
};

export interface GetUserSearchNicknameParams {
  nickname: string;
}
export const GetUserSearchNickname = async ({ nickname }: GetUserSearchNicknameParams) => {
  return await axiosInstance.get(END_POINTS.USER_SEARCH(nickname));
};

export interface GetUserInfoRecordParams {
  nickname: string;
}
export const GetUserInfoRecord = async ({ nickname }: GetUserInfoRecordParams) => {
  return await axiosInstance.get(END_POINTS.USER_INFO_RECORD(nickname));
};

export interface PostUserValidatePasswordParams {
  password: string;
}
export const postUserValidatePassword = async ({ password }: PostUserValidatePasswordParams) => {
  return await axiosInstance.post(END_POINTS.USER_VALIDATE_PASSWORD, password);
};
