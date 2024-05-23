export type grade = "normal" | "epic" | "unique" | "legendary" | "none";

export type state = "red" | "green" | "orange";

export interface CostumeCardProps {
  type: "MYPAGE" | "SHOP" | "PURCHASE";
  costumeSeq: number;
  grade: grade;
  name: string;
  image: string;
  isHave?: boolean;
  coinPrice?: number;
  crystalPrice?: number;
}
