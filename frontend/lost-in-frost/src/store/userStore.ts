import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

interface UserStoreProps {
  email: string;
  nickname: string;
  level: number;
  experience: number;
  crystal: number;
  coin: number;
  myCostumeSeq: number;
  costumeSeq: number;
  costumeName: string;
  costumeImage: string;
  costumeGrade: string;
  message: string;
  authProvider: string;
  gamePlayCount: number;
  successCount: number;
  setEmail: (by: string) => void;
  setNickname: (by: string) => void;
  setLevel: (by: number) => void;
  setExperience: (by: number) => void;
  setCrystal: (by: number) => void;
  setCoin: (by: number) => void;
  setMyCostumeSeq: (by: number) => void;
  setCostumeSeq: (by: number) => void;
  setCostumeName: (by: string) => void;
  setCostumeImage: (by: string) => void;
  setCostumeGrade: (by: string) => void;
  setMessage: (by: string) => void;
  setAuthProvider: (by: string) => void;
  setGamePlayCount: (by: number) => void;
  setSuccessCount: (by: number) => void;
  reset: () => void;
}

const userStore = create<UserStoreProps>()(
  persist(
    (set, get) => ({
      email: "",
      nickname: "",
      level: 0,
      experience: 0,
      crystal: 0,
      coin: 0,
      myCostumeSeq: 0,
      costumeSeq: 0,
      costumeName: "",
      costumeImage: "",
      costumeGrade: "",
      message: "",
      authProvider: "",
      gamePlayCount: 0,
      successCount: 0,
      setEmail: (by) => set((state) => ({ email: by })),
      setNickname: (by) => set((state) => ({ nickname: by })),
      setLevel: (by) => set((state) => ({ level: by })),
      setExperience: (by) => set((state) => ({ experience: by })),
      setCrystal: (by) => set((state) => ({ crystal: by })),
      setCoin: (by) => set((state) => ({ coin: by })),
      setMyCostumeSeq: (by) => set((state) => ({ myCostumeSeq: by })),
      setCostumeSeq: (by) => set((state) => ({ costumeSeq: by })),
      setCostumeName: (by) => set((state) => ({ costumeName: by })),
      setCostumeImage: (by) => set((state) => ({ costumeImage: by })),
      setCostumeGrade: (by) => set((state) => ({ costumeGrade: by })),
      setMessage: (by) => set((state) => ({ message: by })),
      setAuthProvider: (by) => set((state) => ({ authProvider: by })),
      setGamePlayCount: (by) => set((state) => ({ gamePlayCount: by })),
      setSuccessCount: (by) => set((state) => ({ successCount: by })),
      reset: () =>
        set((state) => ({
          email: "",
          nickname: "",
          level: 0,
          experience: 0,
          crystal: 0,
          coin: 0,
          myCostumeSeq: 0,
          costumeSeq: 0,
          costumeName: "",
          costumeImage: "",
          costumeGrade: "",
          message: "",
          authProvider: "",
          gamePlayCount: 0,
          successCount: 0,
        })),
    }),
    {
      name: "user-store",
      storage: createJSONStorage(() => localStorage),
      version: 1.0,
    }
  )
);

export default userStore;
