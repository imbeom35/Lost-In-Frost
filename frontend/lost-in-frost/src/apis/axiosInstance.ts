import axios, { InternalAxiosRequestConfig, AxiosResponse, AxiosError } from "axios";
import { BASE_URL } from "@/constant/api";

https: const axiosInstance = axios.create({
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
});

<<<<<<< HEAD
axiosInstance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    // setTimeout(() => {
    //   plusCnt();
    // }, 100);

    return config;
  },
  (error: AxiosError) => {
    // minusCnt();

    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response: AxiosResponse) => {
    //const error = response.data.response.error;
    // minusCnt();

    return response;
  },
  (error: AxiosError) => {
    // minusCnt();

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

=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
export default axiosInstance;
