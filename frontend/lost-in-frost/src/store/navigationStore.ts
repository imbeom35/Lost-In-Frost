import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

interface navigationStoreProps {
  currentTab: string;
  setCurrentTab: (by: string) => void;
}

const navigationStore = create<navigationStoreProps>()(
  persist(
    (set) => ({
      currentTab: "",
      setCurrentTab: (by) => set((state) => ({ currentTab: by })),
    }),
    {
      name: "navigation-store",
      storage: createJSONStorage(() => localStorage),
      version: 1.0,
    }
  )
);

export default navigationStore;
