import { getUserValidateEmail, getUserValidateNickname } from "@/apis/apiUser";
import { emailRegex } from "@/utils/formatter";
import React, { useState } from "react";

const useJoin = (): [
  Record<string, string>,
  Record<string, string>,
  Record<string, boolean>,
  (event: React.ChangeEvent<HTMLInputElement>) => void,
  React.Dispatch<React.SetStateAction<Record<string, string>>>
] => {
  const initialValues = {
    email: "",
    nickname: "",
    password: "",
    passwordCheck: "",
  };

  const initialMessages = {
    email: "",
    nickname: "",
    password: "",
    passwordCheck: "",
  };

  const initialStates = {
    email: false,
    nickname: false,
    password: false,
    passwordCheck: false,
  };

  const [values, setValues] = useState<Record<string, string>>(initialValues);
  const [messages, setMessages] = useState<Record<string, string>>(initialMessages);
  const [states, setStates] = useState<Record<string, boolean>>(initialStates);

  const emailValidation = async (email: string) => {
    try {
      const response = await getUserValidateEmail({ email });
      return response.data.success;
    } catch (err) {
      console.log(err);
    }
  };

  const nicknameValidation = async (nickname: string) => {
    try {
      const response = await getUserValidateNickname({ nickname });
      return response.data.success;
    } catch (err) {
      console.log(err);
    }
  };

  const onChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
    setValues((prev) => ({
      ...prev,
      [event.target.name]: event.target.value,
    }));

    switch (event.target.name) {
      case "email":
        if (!emailRegex.test(event.target.value)) {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "이메일 형식이 아닙니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: false,
          }));
        } else if ((await emailValidation(event.target.value)) === false) {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "사용중인 이메일입니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: false,
          }));
        } else {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "사용가능한 이메일입니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: true,
          }));
          console.log("email ok");
        }
        break;
      case "nickname":
        if (event.target.value.length < 2) {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "닉네임은 2자리 이상이어야 합니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: false,
          }));
        } else if ((await nicknameValidation(event.target.value)) === false) {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "사용중인 닉네임입니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: false,
          }));
        } else {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "사용가능한 닉네임입니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: true,
          }));
          console.log("nickname ok");
        }
        break;
      case "password":
        if (event.target.value.length < 6) {
          setMessages((prev) => ({
            ...prev,
            passwordCheck: "비밀번호는 6자리 이상이어야 합니다.",
          }));
          setStates((prev) => ({
            ...prev,
            password: false,
            passwordCheck: false,
          }));
        } else if (event.target.value === values.passwordCheck) {
          setMessages((prev) => ({
            ...prev,
            passwordCheck: "비밀번호가 일치합니다.",
          }));
          setStates((prev) => ({
            ...prev,
            password: true,
            passwordCheck: true,
          }));
        } else {
          setMessages((prev) => ({
            ...prev,
            passwordCheck: "비밀번호가 일치하지 않습니다.",
          }));
          setStates((prev) => ({
            ...prev,
            password: false,
            passwordCheck: false,
          }));
        }
        break;
      case "passwordCheck":
        if (event.target.value.length < 6) {
          setMessages((prev) => ({
            ...prev,
            passwordCheck: "비밀번호는 6자리 이상이어야 합니다.",
          }));
          setStates((prev) => ({
            ...prev,
            password: false,
            passwordCheck: false,
          }));
        } else if (event.target.value === values.password) {
          setMessages((prev) => ({
            ...prev,
            passwordCheck: "비밀번호가 일치합니다.",
          }));
          setStates((prev) => ({
            ...prev,
            password: true,
            passwordCheck: true,
          }));
          console.log("password ok");
        } else {
          setMessages((prev) => ({
            ...prev,
            passwordCheck: "비밀번호가 일치하지 않습니다.",
          }));
          setStates((prev) => ({
            ...prev,
            password: false,
            passwordCheck: false,
          }));
        }
        break;
    }
  };

  return [values, messages, states, onChange, setValues];
};

export default useJoin;
