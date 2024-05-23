<<<<<<< HEAD
import { Route, Routes, useNavigate } from "react-router-dom";
=======
import { Route, Routes } from "react-router-dom";
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
import Home from "@/pages/home/Home";
import Notice from "@/pages/notice/Notice";
import Ranking from "@/pages/ranking/Ranking";
import Record from "@/pages/record/Record";
import Shop from "@/pages/shop/Shop";
import TossSuccess from "./pages/mypage/TossSuccess";
import Mypage from "./pages/mypage/Mypage";
import User from "./pages/user/User";
import ErrorPage from "./pages/error/ErrorPage";
import Spinner from "./component/molecules/common/Spinner";
import axiosInstance from "./apis/axiosInstance";
import { AxiosError, AxiosResponse, InternalAxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import axiosStore from "./store/axiosStore";
<<<<<<< HEAD
import userStore from "./store/userStore";
import { getUserMyPageInfo } from "./apis/apiUser";

const App = () => {
  const navigate = useNavigate();
  const { requestCnt, setRequestCnt, plusCnt, minusCnt } = axiosStore();
  const {
    email,
    nickname,
    level,
    experience,
    crystal,
    coin,
    myCostumeSeq,
    costumeSeq,
    costumeName,
    costumeImage,
    costumeGrade,
    message,
    authProvider,
    gamePlayCount,
    successCount,
    setEmail,
    setNickname,
    setLevel,
    setExperience,
    setCrystal,
    setCoin,
    setMyCostumeSeq,
    setCostumeSeq,
    setCostumeName,
    setCostumeImage,
    setCostumeGrade,
    setMessage,
    setAuthProvider,
    setGamePlayCount,
    setSuccessCount,
    reset,
  } = userStore();

  useEffect(() => {
    const initComponent = async () => {
      axiosInstance.interceptors.request.use(
        (config: InternalAxiosRequestConfig) => {
          const token = localStorage.getItem("token");
          if (token) {
            config.headers.Authorization = `Bearer ${token}`;
          }

          setTimeout(() => {
            plusCnt();
          }, 100);

          return config;
        },
        (error: AxiosError) => {
          minusCnt();

          return Promise.reject(error);
        }
      );

      axiosInstance.interceptors.response.use(
        (response: AxiosResponse) => {
          //const error = response.data.response.error;
          minusCnt();

          return response;
        },
        (error: AxiosError) => {
          minusCnt();

          if (error.response && error.response.status) {
            switch (error.response.status) {
              // case 400:
              //   location.href = "/error";
              //   return new Promise(() => {});
              //case 401:
              // location.href = "/user/logout";
              //return new Promise(() => {});
              // case 403:
              //   location.href = "/error";
              //   return new Promise(() => {});
              // case 404:
              //   location.href = "/error";
              //   return new Promise(() => {});
              default:
                return Promise.reject(error);
            }
          }

          return Promise.reject(error);
        }
      );

      console.log("!!");
      console.log("토큰줘", localStorage.getItem("token"));
      try {
        const response = await getUserMyPageInfo();
        console.log("!!!!!!,", response);
        if (response.data.success) {
          const data = response.data.response;
          setEmail(data.email);
          setNickname(data.nickname);
          setLevel(data.level);
          setExperience(data.experience);
          setCrystal(data.crystal);
          setCoin(data.coin);
          setMyCostumeSeq(data.myCostumeSeq);
          setCostumeSeq(data.costumeSeq);
          setCostumeName(data.costumeName);
          setCostumeImage(data.costumeImage);
          setCostumeGrade(data.costumeGrade);
          setMessage(data.Message);
          setAuthProvider(data.authProvider);
          setGamePlayCount(data.gamePlayCount);
          setSuccessCount(data.successCount);
        }
        navigate("/");
      } catch (err) {
        return;
      }
    };

    initComponent();
=======

const App = () => {
  const { requestCnt, setRequestCnt, plusCnt, minusCnt } = axiosStore();

  useEffect(() => {
    axiosInstance.interceptors.request.use(
      (config: InternalAxiosRequestConfig) => {
        const token = sessionStorage.getItem("token");
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }

        setTimeout(() => {
          plusCnt();
        }, 100);

        return config;
      },
      (error: AxiosError) => {
        minusCnt();

        return Promise.reject(error);
      }
    );

    axiosInstance.interceptors.response.use(
      (response: AxiosResponse) => {
        //const error = response.data.response.error;
        minusCnt();

        return response;
      },
      (error: AxiosError) => {
        minusCnt();

        if (error.response && error.response.status) {
          switch (error.response.status) {
            // case 400:
            //   location.href = "/error";
            //   return new Promise(() => {});
            //case 401:
            // location.href = "/user/logout";
            //return new Promise(() => {});
            // case 403:
            //   location.href = "/error";
            //   return new Promise(() => {});
            // case 404:
            //   location.href = "/error";
            //   return new Promise(() => {});
            default:
              return Promise.reject(error);
          }
        }

        return Promise.reject(error);
      }
    );
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
  }, []);

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/notice/*" element={<Notice />} />
        <Route path="/ranking" element={<Ranking />} />
        <Route path="/record/*" element={<Record />} />
        <Route path="/mypage/*" element={<Mypage />} />
        <Route path="/shop/*" element={<Shop />} />
        <Route path="/user/*" element={<User />} />
        <Route path="/toss-success" element={<TossSuccess />} />
        <Route path="/toss-fail" />
        <Route path="/error" element={<ErrorPage />} />
      </Routes>
      {requestCnt < 0 && <Spinner></Spinner>}
    </div>
  );
};

export default App;
