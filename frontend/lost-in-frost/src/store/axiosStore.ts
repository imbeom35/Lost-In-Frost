import { create } from "zustand";

interface axiosStoreProps {
  requestCnt: number;
  setRequestCnt: (by: number) => void;
  plusCnt: () => void;
  minusCnt: () => void;
}

const axiosStore = create<axiosStoreProps>()((set) => ({
  requestCnt: 0,
  setRequestCnt: (by) => set((state) => ({ requestCnt: by })),
  plusCnt: () => set((state) => ({ requestCnt: state.requestCnt + 1 })),
  minusCnt: () => set((state) => ({ requestCnt: state.requestCnt - 1 })),
}));

export default axiosStore;
