import { S3_URL } from "@/constant/api";

export const parseDateAndTime = (dateTimeString: string): { date: string; time: string } => {
  const date = new Date(dateTimeString);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");
  const seconds = String(date.getSeconds()).padStart(2, "0");

  const formattedDate = `${year}-${month}-${day}`;
  const formattedTime = `${hours}:${minutes}:${seconds}`;

  return {
    date: formattedDate,
    time: formattedTime,
  };
};

export const parseNumberToPercentage = (value: number) => {
  if (value >= 0 && value <= 1) {
    const percentage = (value * 100).toFixed(1);
    return `( ${percentage}% )`;
  } else {
    throw new Error("숫자는 0과 1 사이에 있어야 합니다.");
  }
};

export const costumeImageUrl = (type: "body" | "face", name: string) => {
  if (type && name) {
    switch (type) {
      case "body":
        return `${S3_URL}costume/whole-body/${name}.png`;
      case "face":
        return `${S3_URL}costume/face/${name}.png`;
      default:
        return "";
    }
  } else {
    return "";
  }
};

export const crystalShopImageUrl = (name: string) => {
  if (name) {
    return `${S3_URL}crystal-shop/${name}.png`;
  } else {
    return "";
  }
};

export const getCurrentDate = () => {
  const currentDate = new Date();

  const year = currentDate.getFullYear();
  const month = currentDate.getMonth() + 1;
  const day = currentDate.getDate();

  return `${year}-${month}-${day}`;
};

export const calculateHoursAgo = (datetime: string) => {
  const targetTime = new Date(datetime);
  const currentTime = new Date();
  const timeDiff = currentTime.getTime() - targetTime.getTime();
  const hoursDiff = timeDiff / (1000 * 60 * 60);
  const roundedHours = Math.floor(hoursDiff);
  return roundedHours;
};

export const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

export const parseJwt = (token: string) => {
  var base64Url = token.split(".")[1];
  var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  var jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
};

export const isAdmin = () => {
<<<<<<< HEAD
  const token = localStorage.getItem("token");
=======
  const token = sessionStorage.getItem("token");
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

  if (token) {
    if (parseJwt(token).auth === "ROLE_ADMIN") {
      return true;
    }
    return false;
  } else {
    return false;
  }
};

export const secondsToTime = (totalSeconds: number) => {
  const minutes: number = Math.floor(totalSeconds / 60);
  const seconds: number = totalSeconds % 60;

  const formattedMinutes: string = String(minutes).padStart(2, "0");
  const formattedSeconds: string = String(seconds).padStart(2, "0");

  return `${formattedMinutes}:${formattedSeconds}`;
};
