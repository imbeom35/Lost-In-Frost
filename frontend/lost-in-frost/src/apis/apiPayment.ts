import axiosInstance from "@/apis/axiosInstance";
import { END_POINTS } from "@/constant/api";

export interface GetTossSuccessParams {
  orderId: string;
  paymentKey: string;
  amount: string;
}
export const getTossSuccess = async ({ orderId, paymentKey, amount }: GetTossSuccessParams) => {
  return await axiosInstance.get(END_POINTS.TOSS_SUCCESS, {
    params: {
      orderId: orderId,
      paymentKey: paymentKey,
      amount: amount,
    },
  });
};

export interface GetTossFailParams {
  code: string;
  message: string;
  orderId: string;
}
export const getTossFail = async ({ code, message, orderId }: GetTossFailParams) => {
  return await axiosInstance.get(END_POINTS.TOSS_FAIL, {
    params: {
      code: code,
      message: message,
      orderId: orderId,
    },
  });
};

export interface GetTossListParams {
  page: number;
  size: number;
}
export interface GetTossListResponse {
  crystalShopCrystalAmount: number;
  paymentAmount: number;
  paymentApprovedAt: string;
  paymentOrderId: string;
  paymentOrderName: string;
  paymentPaySuccessStatus: boolean;
  paymentPayType: string;
  paymentSeq: number;
}
export const getTossList = async ({ page, size }: GetTossListParams) => {
  return await axiosInstance.get(END_POINTS.TOSS_LIST, {
    params: {
      page: page,
      size: size,
    },
  });
};

export interface PostTossRequestParams {
  payType: string;
  amount: number;
  orderName: string;
  yourSuccessUrl: string;
  yourFailUrl: string;
  crystalShopSeq: number;
}
export const postTossRequest = async ({
  payType,
  amount,
  orderName,
  yourSuccessUrl,
  yourFailUrl,
  crystalShopSeq,
}: PostTossRequestParams) => {
  return await axiosInstance.post(END_POINTS.TOSS_REQUEST, {
    payType: payType,
    amount: amount,
    orderName: orderName,
    yourSuccessUrl: yourSuccessUrl,
    yourFailUrl: yourFailUrl,
    crystalShopSeq: crystalShopSeq,
  });
};

export interface PostTossCancelParams {
  paymentKey: string;
  cancelReason: string;
}
export const postTossCancel = async ({ paymentKey, cancelReason }: PostTossCancelParams) => {
  return await axiosInstance.post(END_POINTS.TOSS_CANCEL, {
    paymentKey: paymentKey,
    cancelReason: cancelReason,
  });
};

export interface GetPurchaseListParams {
  classification: string;
  page: number;
  size: number;
}
export interface GetPurchaseListResponse {
  classification: boolean;
  costumeGrade: string;
  costumeImage: string;
  costumeName: string;
  costumeSeq: number;
  price: number;
  purchaseDatetime: string;
  purchaseHistorySeq: number;
  quantity: number;
  state: number;
}
export const getPurchaseList = async ({ classification, page, size }: GetPurchaseListParams) => {
  return await axiosInstance.get(END_POINTS.PURCHASE_LIST(classification), {
    params: {
      page: page,
      size: size,
    },
  });
};

export interface PostPurchaseParams {
  costumeSeq: string;
  classification: string;
}
export const postPurchase = async ({ costumeSeq, classification }: PostPurchaseParams) => {
  return await axiosInstance.post(END_POINTS.PURCHASE, {
    costumeSeq: costumeSeq,
    classification: classification,
  });
};

export interface GetCrystalShopListParams {
  page: number;
  size: number;
}
export const getCrystalShopList = async ({ page, size }: GetCrystalShopListParams) => {
  return await axiosInstance.get(END_POINTS.CRYSTAL_SHOP_LIST, {
    params: {
      page: page,
      size: size,
    },
  });
};
