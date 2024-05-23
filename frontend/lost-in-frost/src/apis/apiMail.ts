import axiosInstance from "@/apis/axiosInstance";
import { END_POINTS } from "@/constant/api";

export interface PostMailVerifyParams {
  email: string;
  code: string;
}
export const postMailVerify = async ({ email, code }: PostMailVerifyParams) => {
  return await axiosInstance.post(END_POINTS.MAIL_VERIFY, {
    email: email,
    code: code,
  });
};

export interface PostMailSendParams {
  email: string;
  memberNickname: string;
}
export const postMailSend = async ({ email, memberNickname }: PostMailSendParams) => {
  return await axiosInstance.post(END_POINTS.MAIL_SEND, {
    email: email,
    memberNickname: memberNickname,
  });
};

export interface PostMailResendParams {
  email: string;
  memberNickname: string;
}
export const postMailResend = async ({ email, memberNickname }: PostMailResendParams) => {
  return await axiosInstance.post(END_POINTS.MAIL_RESEND, {
    email: email,
    memberNickname: memberNickname,
  });
};
