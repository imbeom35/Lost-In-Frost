import { BASE_URL } from "@/constant/api";

export const logout = () => {
  location.href = BASE_URL + "/auth/logout";
  sessionStorage.clear();
  localStorage.clear();
};
