export const PROD = process.env.NODE_ENV === "production";

export const BASE_URL = `BACKEND URI`;
// export const MY_URL = `http://localhost:3000`;
export const MY_URL = `FRONT URI`;

export const S3_URL = `S3 URI`;

export const REDIRECT_URL = MY_URL + `/user/redirect`;
export const TOSS_SUCCESS_URL = MY_URL + "/toss-success";
export const TOSS_FAIL_URL = MY_URL + "/toss-fail";

export const GOOGLE_AUTH_API_URL =
  BASE_URL + `/oauth2/authorize/google?redirect_uri=` + REDIRECT_URL;
export const KAKAO_AUTH_API_URL = BASE_URL + `/oauth2/authorize/kakao?redirect_uri=` + REDIRECT_URL;
export const NAVER_AUTH_API_URL = BASE_URL + `/oauth2/authorize/naver?redirect_uri=` + REDIRECT_URL;

export const END_POINTS = {
  //User
  USER_AUTH_WITHDRAWAL: "/user/withdrawal",
  USER_AUTH_LOGIN: "/auth/login",
  USER_AUTH_JOIN: "/auth/join",
  // USER_LOGOUT: "/auth/logout",
  USER_VALIDATE_NICKNAME: (nickname: string) => `/auth/validate/nickname/${nickname}`,
  USER_VALIDATE_EMAIL: (email: string) => `/auth/validate/email/${email}`,
  USER_VALIDATE_PASSWORD: "/user/validate-password",
  USER_MYPAGE_INFO: "/user/info",
  USER_MYPAGE_CRYSTAL: "/user/crystal",
  USER_MYPAGE_COIN: "/user/coin",
  USER_MYPAGE_AMOUNT: "/user/amount",
  USER_MYCOSTUME_LIST: "/user/my-costume/list",
  USER_MYCOSTUME: (myCostumeSeq: number) => `/user/my-costume/${myCostumeSeq}`,
  USER_MYCOSTUME_COUNT: "/user/my-costume/count",
  USER_MYPAGE_MESSAGE: "/user/message",
  USER_SEARCH: (nickname: string) => `/user/search/${nickname}`,
  USER_INFO_RECORD: (memberId: string) => `/user/info/record/${memberId}`,

  // Mail
  MAIL_VERIFY: "/mail/verify",
  MAIL_SEND: "/mail/send",
  MAIL_RESEND: "/mail/resend",

  // Payment
  TOSS_SUCCESS: "/toss/success",
  TOSS_FAIL: "/toss/fail",
  TOSS_LIST: "/toss/list",
  TOSS_REQUEST: "/toss/request",
  TOSS_CANCEL: "/toss/cancel",
  PURCHASE_LIST: (classification: string) => `/purchase/list/${classification}`,
  PURCHASE: "/purchase",
  PURCHASE_REFUND: "/purchase/refund",
  CRYSTAL_SHOP_LIST: "/crystal-shop/list",

  // game
  GAME_FILE_LAUNCHER_DOWNLOAD: "/game/file/launcher-download",
  GAME_FILE_GAME_DOWNLOAD: "/game/file/game-download",
  GAME_COSTUME_LIST: "/game/costume/list",
  GAME_COSTUME_COUNT: "/game/costume/count",

  // record
  GAME_RECORD_LIST: (nickname: string) => `/game/record/list/${nickname}`,
  GAME_RECORD_LIST_DETAIL: (roomId: string) => `/game/record/list/detail/${roomId}`,

  // ranking
  RANKING_LIST: "/rank/list",

  // notice
  NOTICE_DELETE: (noticeSeq: number) => `/notice/delete/${noticeSeq}`,
  NOTICE_COMMENT_DELETE: (noticeCommentSeq: number) => `/notice/comment/delete/${noticeCommentSeq}`,
  NOTICE_LIST: "/notice/list",
  NOTICE_DETAIL: (noticeSeq: number) => `/notice/detail/${noticeSeq}`,
  NOTICE_COMMENT_LIST: (noticeSeq: number) => `/notice/comment/list/${noticeSeq}`,
  NOTICE_WRITE: "/notice/write",
  NOTICE_COMMENT_WRITE: (noticeSeq: number) => `/notice/comment/write/${noticeSeq}`,
  NOTICE_UPDATE: (noticeSeq: number) => `/notice/update/${noticeSeq}`,
  NOTICE_COMMENT_UPDATE: (noticeCommentSeq: number) => `/notice/comment/update/${noticeCommentSeq}`,
} as const;
