import { create } from "zustand";
import { devtools } from "zustand/middleware";

interface UserTempStoreProps {
  userTempEmail: string;
  userTempNickname: string;
  userTempPassword: string;
  setUserTempEmail: (by: string) => void;
  setUserTempNickname: (by: string) => void;
  setUserTempPassword: (by: string) => void;
  reset: () => void;
}

const userTempStore = create<UserTempStoreProps>()((set) => ({
  userTempEmail: "",
  userTempNickname: "",
  userTempPassword: "",
  setUserTempEmail: (by) => set((state) => ({ userTempEmail: by })),
  setUserTempNickname: (by) => set((state) => ({ userTempNickname: by })),
  setUserTempPassword: (by) => set((state) => ({ userTempPassword: by })),
  reset: () => set((state) => ({ userTempEmail: "", userTempNickname: "", userTempPassword: "" })),
}));

export default userTempStore;
