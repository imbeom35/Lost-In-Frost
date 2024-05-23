import axios, { InternalAxiosRequestConfig, AxiosResponse, AxiosError } from "axios";
import { BASE_URL } from "@/constant/api";

const axiosFileInstance = axios.create({
  baseURL: BASE_URL,
<<<<<<< HEAD
  headers: {
    "Content-type":
      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8",
  },
=======
  headers: { "Content-type": "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8" },
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
});

/*
  request interceptor
 */
axiosFileInstance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
<<<<<<< HEAD
    const token = localStorage.getItem("token");
=======
    const token = sessionStorage.getItem("token");
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

/*
  response interceptor
 */
axiosFileInstance.interceptors.response.use(
  (response: AxiosResponse) => {
    return response;
  },
  (error: AxiosError) => {
    if (error.response && error.response.status) {
      switch (error.response.status) {
        default:
          return Promise.reject(error);
      }
    }

    return Promise.reject(error);
  }
);

export default axiosFileInstance;
