import { BASE_URL } from "@/constant/api";

export const logout = () => {
  location.href = BASE_URL + "/auth/logout";
  localStorage.clear();
};

class AutoLogout {
  private timeoutId: NodeJS.Timeout | undefined;

  constructor(private logoutTime: number, private logoutCallback: () => void) {
    this.startTimer();
    this.setupListeners();
  }

  private startTimer() {
    this.timeoutId = setTimeout(() => {
      this.logoutCallback();
    }, this.logoutTime);
  }

  private resetTimer() {
    clearTimeout(this.timeoutId);
    this.startTimer();
  }

  private setupListeners() {
    window.addEventListener("mousemove", () => {
      this.resetTimer();
    });

    window.addEventListener("keydown", () => {
      this.resetTimer();
    });
  }
}
