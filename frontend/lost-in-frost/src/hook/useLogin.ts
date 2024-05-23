import { getUserValidateEmail, getUserValidateNickname, postUserAuthLogin } from "@/apis/apiUser";
import { emailRegex } from "@/utils/formatter";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const useLogin = (): [
  Record<string, string>,
  Record<string, string>,
  Record<string, boolean>,
  () => void,
  (event: React.ChangeEvent<HTMLInputElement>) => void,
  (event: React.KeyboardEvent<HTMLInputElement>) => void,
  React.Dispatch<React.SetStateAction<Record<string, string>>>
] => {
  const navigate = useNavigate();
  const initialValues = {
    email: "",
    password: "",
  };

  const initialMessages = {
    email: "",
    password: "",
  };

  const initialStates = {
    email: false,
    password: false,
  };

  const [values, setValues] = useState<Record<string, string>>(initialValues);
  const [messages, setMessages] = useState<Record<string, string>>(initialMessages);
  const [states, setStates] = useState<Record<string, boolean>>(initialStates);

  const onSubmit = async () => {
    if (values.password.length < 6) {
      alert("비밀번호는 6자리 이상이어야 합니다.");
      return;
    }

    try {
      const response = await postUserAuthLogin({
        email: values.email,
        password: values.password,
      });
      if (response.data.success) {
        navigate("/user/redirect?token=" + response.data.response);
      } else {
        alert(response.data.error.message);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
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
        } else {
          setMessages((prev) => ({
            ...prev,
            [event.target.name]: "올바른 이메일입니다.",
          }));
          setStates((prev) => ({
            ...prev,
            [event.target.name]: true,
          }));
        }
        break;
      case "password":
        if (event.target.value.length > 0) {
          setStates((prev) => ({
            ...prev,
            [event.target.name]: true,
          }));
        } else {
          setStates((prev) => ({
            ...prev,
            [event.target.name]: false,
          }));
        }
        break;
    }
  };

  const onKeyboard = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      onSubmit();
    }
  };

  return [values, messages, states, onSubmit, onChange, onKeyboard, setValues];
};

export default useLogin;
